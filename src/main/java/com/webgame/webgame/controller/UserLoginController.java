package com.webgame.webgame.controller;

import com.webgame.webgame.dto.EmailRequest;
import com.webgame.webgame.dto.UserRequest;
import com.webgame.webgame.dto.UserResponse;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.UserRepository;
import com.webgame.webgame.service.userLogin.UserLoginService;
import com.webgame.webgame.dto.UserLoginDto;
import org.eclipse.angus.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;


    @PostMapping("/registerApi")
    public ResponseEntity<UserResponse> saveRegisterUser(@RequestBody UserResponse userResponse) {
        UserLoginDto userLoginDto = new UserLoginDto();
        UserResponse response = new UserResponse();
        response.setUsername(userResponse.getUsername());
        response.setPassword(userResponse.getPassword());
        response.setEmail(userResponse.getEmail());
        response.setPhone(userResponse.getPhone());

        User user = userRepository.findByEmail(userResponse.getEmail());
        User user1 = userRepository.findByPhone(userResponse.getPhone());
        if (user == null && user1 == null) {
            //tạo dto
            userLoginDto.setRole("user");
            userLoginDto.setEmail(userResponse.getEmail());
            userLoginDto.setPhone(userResponse.getPhone());
            userLoginDto.setPassword(userResponse.getPassword());
            userLoginDto.setUsername(userResponse.getUsername());
            //save
            userLoginService.save(userLoginDto);

            response.setCheckLogin(true);
        } else {
            response.setCheckLogin(false);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/loginApi")
    public ResponseEntity<UserResponse> getUserInfo(@RequestBody UserRequest request) {
        // Tạo một phản hồi giả lập dựa trên thông tin nhận được
        UserResponse response = new UserResponse();
        String email = request.getEmail();

        User user = userRepository.findByEmail(email);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (isPasswordMatch && user != null) {
            response.setUsername(user.getFullName());
            response.setEmail(user.getEmail());
            response.setPassword(user.getPassword());
            response.setPhone(user.getPhone());
            response.setCheckLogin(true);
        } else response.setCheckLogin(false);
        return ResponseEntity.ok(response);
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder để mã hóa mật khẩu mới

    @PostMapping("/forgotPassword")
    public ResponseEntity<Boolean> sendEmail(@RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail(); // Lấy email từ đối tượng
        System.out.println("Received email: " + email);

        Boolean check = false;
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(check);
        }

        // Tạo mật khẩu mới
        String newPassword = generateRandomPassword();
        String encryptedPassword = passwordEncoder.encode(newPassword); // Mã hóa mật khẩu mới

        // Cập nhật mật khẩu mới vào cơ sở dữ liệu
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        // Gửi mật khẩu mới chưa mã hóa tới email người dùng
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ntdl19062003@gmail.com");
        message.setTo(email);  // Gửi email tới người dùng
        message.setSubject("BDLVGaming-FORGOT PASSWORD");
        message.setText("Mật khẩu mới: " + newPassword + "\nĐăng nhập vào tài khoản bằng mật khẩu vừa được cấp! \nXin cảm ơn!");

        mailSender.send(message);
        check = true;
        return ResponseEntity.ok(check);
    }


    private String generateRandomPassword() {
        return new Random()
                .ints(6, 0, 10)  // Tạo một luồng số nguyên ngẫu nhiên (6 số, giá trị từ 0 đến 9)
                .mapToObj(String::valueOf)  // Chuyển từng số thành chuỗi
                .reduce("", String::concat);  // Nối các chuỗi lại thành một chuỗi duy nhất
    }



}
