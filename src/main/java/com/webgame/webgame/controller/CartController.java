package com.webgame.webgame.controller;

import com.webgame.webgame.model.CartGame;
import com.webgame.webgame.service.cart.CartGameService;
import com.webgame.webgame.service.thanhtoan.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartGameService cartGameService;
    @Autowired
    BuyService buyService;
    @GetMapping("/cart")
    public String viewCart(Model model) {

        Long userId=26L;
        List<CartGame> cartGames = cartGameService.getCartGamesByUserId(userId);
//        cartGameService.saveCartGame(10L,userId);
//        cartGameService.deleteCartGame(10L,userId);

        Long soluonggame= cartGameService.countGameByUserIdInCart(userId);
        BigDecimal tongtien=cartGameService.calculateTotalPrice(userId);

        model.addAttribute("cartGames", cartGames);
        model.addAttribute("soluonggame",soluonggame);
        model.addAttribute("tongtien", tongtien);

        return "giohang/cart";
//        return "thanhtoan/pay";
//        return "giohang/test";
    }

    @GetMapping("/cart/delete")
    public String deleteGameinCart(@RequestParam("gameId") Long gameId, Model model ) {
        Long userId=26L;
        cartGameService.deleteCartGame(gameId,userId);
        return "redirect:/cart";
    }

}
