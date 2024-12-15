package com.webgame.webgame.controller;

import com.webgame.webgame.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @PostMapping("/product/review")
    public String submitReview(@RequestParam("gameId") Long gameId,
                               @RequestParam("score") int score,
                               @RequestParam("comment") String comment,
                               Model model) {
        Long userId = 26L;
        String message =reviewService.saveReview(userId, gameId, score, comment);
        // Xử lý dữ liệu (ví dụ: lưu vào cơ sở dữ liệu, xử lý logic đánh giá...)
        System.out.println("Game ID: " + gameId);
        System.out.println("Score: " + score);
        System.out.println("Comment: " + comment);

        // Để hiển thị thông báo hoặc trả về trang khác
        model.addAttribute("message", message);

        // Trả về tên view (Ví dụ: "reviewSuccess" sẽ tương ứng với một file reviewSuccess.html)
        return "redirect:/game/" + gameId;
    }
}
