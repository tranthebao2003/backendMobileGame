package com.webgame.webgame.controller;

import com.webgame.webgame.dto.UserLoginDto;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder để mã hóa mật khẩu mới

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String email, Model model,@ModelAttribute("user") UserLoginDto userLoginDto) {
        // Kiểm tra email có tồn tại trong cơ sở dữ liệu không
        User user = userRepository.findByEmail(email);

        while (user == null) {
            model.addAttribute("error", "Email chưa đăng kí hoặc không tồn tại !");
            return "login/confirmEmail"; // Quay lại trang xác nhận email với thông báo lỗi
        }

        // Tạo mật khẩu mới
        String newPassword = generateRandomPassword();
        String encryptedPassword = passwordEncoder.encode(newPassword); // Mã hóa mật khẩu mới

        // Cập nhật mật khẩu mới vào cơ sở dữ liệu
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        // Gửi mật khẩu mới chưa mã hóa tới email người dùng
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("ntdl19062003@gmail.com");
            message.setTo(email);  // Gửi email tới người dùng
            message.setSubject("BDLVGaming-FORGOT PASSWORD");
            message.setText("Mật khẩu mới: " + newPassword + "\nĐăng nhập vào tài khoản bằng mật khẩu vừa được cấp! \nXin cảm ơn!");


            mailSender.send(message);

            model.addAttribute("message","Kiểm tra Email \n để nhận password mới !");
            // Trả về login.html nếu gửi mail thành công
            return "login/login"; // Tên file login.html
        } catch (MailException e) {
            model.addAttribute("error", "Error sending email: " + e.getMessage());
            return "login/forgot-password"; // Quay lại trang xác nhận email với thông báo lỗi
        }
    }

    private String generateRandomPassword() {
        return new Random()
                .ints(6, 0, 10)  // Tạo một luồng số nguyên ngẫu nhiên (6 số, giá trị từ 0 đến 9)
                .mapToObj(String::valueOf)  // Chuyển từng số thành chuỗi
                .reduce("", String::concat);  // Nối các chuỗi lại thành một chuỗi duy nhất
    }
}
