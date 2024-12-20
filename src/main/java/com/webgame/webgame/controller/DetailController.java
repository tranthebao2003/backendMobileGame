package com.webgame.webgame.controller;
import com.webgame.webgame.model.Review;
import com.webgame.webgame.sevice.detailGame.DetailGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/detailGame")
public class DetailController {

    @Autowired
    private DetailGameService detailGameService;

    @GetMapping("/game/{gameId}")
    public Map<String, Object> getGameDetails(@PathVariable Long gameId) {
        double getAverageScore = detailGameService.getAverageScore(gameId);
        // Lấy danh sách các review của game theo gameId
        int getTotalReviews = detailGameService.getTotalReviews(gameId);
        long totalGameInStock = detailGameService.countAccountGamesByStatusAndGameId(false, gameId);

        Map<String, Object> totalReviewAverageScore = new HashMap<>();

        totalReviewAverageScore.put("averageScore", getAverageScore);
        totalReviewAverageScore.put("totalReview", getTotalReviews);
        totalReviewAverageScore.put("totalGameInStock", totalGameInStock);

        return totalReviewAverageScore;
    }

    // xem review phải có login nên chưa làm được
    @GetMapping("/review/{gameId}")
    public List<Review> getReviewByGameId(@PathVariable("gameId") Long gameId) {
        // Lấy danh sách review của game
        return detailGameService.getReviewsByGameId(gameId);
    }
}
