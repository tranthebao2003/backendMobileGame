package com.webgame.webgame.controller;

import com.webgame.webgame.dto.ReviewRequest;
import com.webgame.webgame.model.Game;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.GameRepository;
import com.webgame.webgame.repository.UserRepository;
import com.webgame.webgame.service.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    GameRepository gameRepository;
    @PostMapping("/addReview")
    public ResponseEntity<Boolean> addReview(@RequestBody ReviewRequest reviewRequest) {
        User user = userRepository.findByEmail(reviewRequest.getUserEmail());
        Game game = gameRepository.findGameByGameName(reviewRequest.getGameName());
        Long gameId = game.getGameId();
        reviewService.saveReview(user.getUserId(),gameId, reviewRequest.getScore(), reviewRequest.getComment());

        return ResponseEntity.ok(true) ;
    }
}
