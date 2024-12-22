package com.webgame.webgame.controller;

import com.webgame.webgame.dto.ItemOrderList;
import com.webgame.webgame.dto.OrderDetailsDto;
import com.webgame.webgame.dto.UserLoginDto;
import com.webgame.webgame.model.AccountGame;
import com.webgame.webgame.model.Orders;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.OrderRepository;
import com.webgame.webgame.repository.UserRepository;
import com.webgame.webgame.service.user.UserService;
import com.webgame.webgame.service.user.UserServiceImp;
import com.webgame.webgame.service.userLogin.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("/userInfo")
public class UserController {
    @Autowired
    UserService userService;

//    @GetMapping("/user_info")
//    public String getUser( Model model) {
//        Long userId = 25L;
//        User user = userService.getUserById(userId);
//        model.addAttribute("userInfo", user);
//        return "user";
//    }

    @GetMapping("/user_info")
    public String getUser(Model model) {
        // Lấy đối tượng User hiện tại từ SecurityContextHolder
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername(); // Giả sử email là username

        // Lấy thông tin chi tiết của người dùng từ cơ sở dữ liệu bằng email
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
        }

        // Đưa thông tin người dùng vào model
        model.addAttribute("userInfo", user);
        return "user"; // Tên template Thymeleaf
    }
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/ordersApi/{emailUser}")
    public ResponseEntity<List<ItemOrderList>> getUserOrders(@PathVariable String emailUser) {

        // Tìm user từ email
        User user = userRepository.findByEmail(emailUser);

        // Lấy danh sách đơn hàng của người dùng
        List<Orders> orders = orderRepository.findByUser(user);

        // Lấy thông tin mỗi order
        List<ItemOrderList> itemOrderLists = new ArrayList<>();
        for (Orders order : orders) {
            for (AccountGame accountGame : order.getAccountGames()) {
                // Tạo dto để trả về thông tin game, tài khoản, mật khẩu và giá tiền
                ItemOrderList details = new ItemOrderList();
                details.setGamename(accountGame.getGame().getGameName());
                details.setAccount(accountGame.getUsername());
                details.setPass(accountGame.getPassword());
                details.setPrice(accountGame.getGame().getPrice().toString()); // Lấy giá từ Game
                itemOrderLists.add(details);
            }
        }


        return ResponseEntity.ok(itemOrderLists);
    }

}
