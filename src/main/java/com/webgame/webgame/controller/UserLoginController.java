package com.webgame.webgame.controller;

import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.UserRepository;
import com.webgame.webgame.service.userLogin.UserLoginService;
import com.webgame.webgame.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserLoginController {



    @Autowired
    private UserLoginService userLoginService;

    @GetMapping("/register_login")
    public String Register(@ModelAttribute("user") UserLoginDto userLoginDto) {
        return "login/login";
    }

    @PostMapping("/register_login")
    public String saveRegisterUser(@ModelAttribute("user") UserLoginDto userLoginDto, Model model) {
        if (userLoginDto.getRole() == null) {
            userLoginDto.setRole("user");
        }
        userLoginService.save(userLoginDto);
        model.addAttribute("message","Tạo tài khoản thành công !");
        return "login/login";
    }
    @GetMapping("/login")
    public String login(){
        return "login/login";
    }

    @GetMapping("user")
    public String userPage(){
        return "home/home";
    }

//    @GetMapping("admin")
//    public String adminPage(){
//        return "admin";
//    }

    @RequestMapping("forgot-password")
    public String forgotPassword() {
        return "login/confirmEmail";
    }

    //lấy token
//    @RequestMapping("/users")
//    public Principal users(Principal users) {
//        return users;
//    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/login-email")
    public String loginEmail(OAuth2AuthenticationToken token, Model model,@ModelAttribute("user") UserLoginDto userLoginDto) {


        String name = token.getPrincipal().getAttribute("name");
        String email = token.getPrincipal().getAttribute("email");
        String phone = token.getPrincipal().getAttribute("phone");

        User user = userRepository.findByEmail(email);

        if (user == null) {
            if (phone == null) {
                model.addAttribute("name",name);
                model.addAttribute("email",email);
                return "login/loginEmail";
            } else {
                UserLoginDto newUser = new UserLoginDto(
                        email,"GOOGLE","user",name,phone);
                userLoginService.save(newUser);

            }

        }
        Boolean checkGG = true;
        model.addAttribute("checkGG",checkGG);
        model.addAttribute("message","Đăng nhập bằng Google thành công !");
        model.addAttribute("password","GOOGLE");
        model.addAttribute("username",email);
        return "login/login";
    }
    @RequestMapping("/save-phone")
    public String savePhone(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone, Model model,@ModelAttribute("user") UserLoginDto userLoginDto) {
        UserLoginDto newUser = new UserLoginDto(
                email,"GOOGLE","user",name,phone);
        userLoginService.save(newUser);
        Boolean checkGG = true;
        model.addAttribute("checkGG",checkGG);
        model.addAttribute("message","Đăng nhập bằng Google thành công !");
        model.addAttribute("password","GOOGLE");
        model.addAttribute("username",email);
        return "login/login";
    }

}
