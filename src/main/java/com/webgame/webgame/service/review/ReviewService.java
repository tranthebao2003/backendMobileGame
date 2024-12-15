package com.webgame.webgame.service.review;

import com.webgame.webgame.model.Game;
import com.webgame.webgame.model.Review;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.GameRepository;
import com.webgame.webgame.repository.ReviewRepository;
import com.webgame.webgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;

    public String saveReview(Long userId,Long gameId, int score, String content) {
        User user = userRepository.findUserById(userId);
        Game game= gameRepository.findGameById(gameId);
        Review review = new Review();
        review.setUser(user);
        review.setGame(game);
        review.setScore(score);
        review.setComment(content);
        review.setCreateDate(new Date());

        reviewRepository.save(review);
        return "Lưu thành công";
    }
}
