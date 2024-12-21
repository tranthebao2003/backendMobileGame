package com.webgame.webgame.controller;

import com.webgame.webgame.dto.AccountInfo;
import com.webgame.webgame.dto.gameDto.OrderInfo;
import com.webgame.webgame.model.AccountGame;
import com.webgame.webgame.model.Game;
import com.webgame.webgame.model.Orders;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.AccountGameRepository;
import com.webgame.webgame.repository.GameRepository;
import com.webgame.webgame.repository.OrdersRepository;
import com.webgame.webgame.repository.UserRepository;
import com.webgame.webgame.service.game.GameService;
import com.webgame.webgame.service.thanhtoan.BuyService;
import com.webgame.webgame.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class BuyController {

    @Autowired
    private BuyService buyService;

    @Autowired
    private AccountGameRepository accountGameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @PostMapping("/accountGameApi")
    public ResponseEntity<List<AccountInfo>> accountGameApi(@RequestBody OrderInfo orderInfo) {

        Orders order = new Orders();
        List<AccountInfo> accountInfoList = new ArrayList<>();  // Tạo danh sách AccountInfo

        User user = userRepository.findByEmail(orderInfo.getUserEmail());
        order.setUser(user);
        order.setPayAt(new Date());
        order.setSumPrice(BigDecimal.valueOf(orderInfo.getSumprice()));
        ordersRepository.save(order);

        for (Long gameId : orderInfo.getGameId()) {
            AccountGame accountGame = accountGameRepository.findFirstByGame_GameIdAndOrderIsNull(gameId);

            if (accountGame != null) { // Kiểm tra nếu accountGame không phải là null
                AccountInfo accountInfo = new AccountInfo(); // Tạo mới đối tượng AccountInfo cho mỗi game
                accountInfo.setAccount(accountGame.getUsername());
                accountInfo.setPassword(accountGame.getPassword());
                accountInfoList.add(accountInfo);

                Game game = gameRepository.findGameByGameId(gameId);

                accountGame.setStatus(true);
                accountGame.setOrder(order);
                accountGameRepository.save(accountGame);
            }
        }



        return ResponseEntity.ok(accountInfoList);
    }


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
