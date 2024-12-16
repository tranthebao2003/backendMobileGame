package com.webgame.webgame.controller;
import com.webgame.webgame.service.thanhtoan.BuyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BuyController {

    @Autowired
    private BuyService buyService;

    @GetMapping("/xacnhandonhang")
    public String xacnhanbuyincart(@RequestParam(value = "selectedGames", required = false) List<Long> selectedGames, Model model, HttpSession session){
        Long userId = 26L;
        session.setAttribute("selectedGames", selectedGames);
        System.out.println(selectedGames);
//        [1, 3]

        Map<String, Object> result = buyService.xacNhanDonHang(selectedGames);
        model.addAttribute("result", result);
        return "thanhtoan/pay";
    }

    @GetMapping("/thanhtoan")
    public String buyNow( Model model, HttpSession session){
        Long userId = 26L;
        List<Long> selectedGames = (List<Long>) session.getAttribute("selectedGames");
        System.out.println("cai nay o cai thanh toan" +selectedGames);
        String result = buyService.buyInCart(userId, selectedGames);
        model.addAttribute("message", result);
        return "redirect:/cart";
    }

    @GetMapping("/huythanhtoan")
    public String cancelPayment(HttpSession session) {
        // Remove selectedGames from session
        session.removeAttribute("selectedGames");
        return "redirect:/cart";
    }
}
