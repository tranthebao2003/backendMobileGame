package com.webgame.webgame.controller;

import com.webgame.webgame.model.Game;
import com.webgame.webgame.model.Review;
import com.webgame.webgame.repository.CartGameRepository;
import com.webgame.webgame.service.cart.CartGameService;
import com.webgame.webgame.sevice.detailGame.DetailGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    private DetailGameService detailGameService;
    @Autowired
    private CartGameService cartGameService;

    @Autowired
    CartGameRepository cartGameRepository;

    @GetMapping("/game/{gameId}")
    public String getGameDetails(@PathVariable Long gameId, Model model) {

        // Tìm game theo ID
        Game game = detailGameService.findGameById(gameId);

        if (game == null) {
            // Nếu không tìm thấy game, chuyển hướng đến trang lỗi
            return "error/404";
        }

        // Lấy danh sách review của game
        List<Review> reviews = detailGameService.getReviewsByGameId(gameId);

        double getAverageScore = detailGameService.getAverageScore(gameId);
        // Lấy danh sách các review của game theo gameId
        int getTotalReviews = detailGameService.getTotalReviews(gameId);



        long soluonggamechuaban = detailGameService.countAccountGamesByStatusAndGameId(false, gameId);

        // Thêm thông tin vào model để gửi tới view
        model.addAttribute("game", game);
        model.addAttribute("reviews", reviews);
        model.addAttribute("getAverageScore", getAverageScore);
        model.addAttribute("getTotalReviews", getTotalReviews);
        model.addAttribute("soluonggamechuaban", soluonggamechuaban);


        // Trả về view (file HTML nằm trong thư mục templates)
        return "chitietsanpham/chitietsanpham"; // Tương ứng với file templates/gameDetail.html
    }


    @GetMapping("/game/add/{gameId}")
    public String addGame(@PathVariable Long gameId, Model model) {
        Long userId=26L;
        if (!cartGameRepository.existsByGameAndUser(gameId, userId)) {
            model.addAttribute("message","Game đã được thêm vào giỏ hàng trước đó rồi");
            cartGameService.saveCartGame(gameId, userId);
        }
        return  "redirect:/cart";
    }

}
