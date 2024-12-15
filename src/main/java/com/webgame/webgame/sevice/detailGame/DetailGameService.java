package com.webgame.webgame.sevice.detailGame;

import com.webgame.webgame.model.AccountGame;
import com.webgame.webgame.model.Game;
import com.webgame.webgame.model.Review;
import com.webgame.webgame.model.ImageGame;
import com.webgame.webgame.repository.AccountGameRepository;
import com.webgame.webgame.repository.GameRepository;
import com.webgame.webgame.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DetailGameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AccountGameRepository accountGameRepository;


    // Tìm game theo ID
    public Game findGameById(Long gameId) {
        return gameRepository.findGameById(gameId);
    }


    // Lấy danh sách review của một game theo ID
    public List<Review> getReviewsByGameId(Long gameId) {
        return reviewRepository.findReviewsByGameId(gameId);
    }

    // Trả về trung bình điểm đánh giá của game
    public double getAverageScore(Long gameId) {
        List<Review> reviews = getReviewsByGameId(gameId);

        // Tính tổng điểm đánh giá
        double totalScore = 0;
        for (Review review : reviews) {
            totalScore += review.getScore();
        }

        // Tính trung bình điểm và làm tròn 1 chữ số thập phân
        return reviews.size() > 0 ? Math.round((totalScore / reviews.size()) * 10.0) / 10.0 : 0.0;
    }


    // Trả về tổng số lượt đánh giá của game
    public int getTotalReviews(Long gameId) {
        List<Review> reviews = getReviewsByGameId(gameId);
        return reviews.size(); // Trả về số lượng review
    }

    // Lấy các lượt đánh giá có điểm cụ thể (dựa trên điểm đánh giá)
    public List<Review> getReviewsByScore(Long gameId, int score) {
        List<Review> reviews = getReviewsByGameId(gameId);

        // Lọc các đánh giá có điểm bằng score
        List<Review> filteredReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getScore() == score) {
                filteredReviews.add(review);
            }
        }
        return filteredReviews;
    }

    // Đếm số lượng tài khoản game theo trạng thái và gameId
    public Long countAccountGamesByStatusAndGameId(boolean status, Long gameId) {
        // Lấy danh sách tài khoản game có trạng thái được chỉ định
        List<AccountGame> accounts = accountGameRepository.findAccountGamesByStatus(status);

        // Đếm số lượng tài khoản thuộc gameId
        long count = 0;
        for (AccountGame account : accounts) {
            if (account.getGame().getGameId().equals(gameId)) {
                count++;
            }
        }
        return count;
    }
}
