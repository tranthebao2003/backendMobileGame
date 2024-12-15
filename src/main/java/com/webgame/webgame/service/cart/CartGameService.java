package com.webgame.webgame.service.cart;

import com.webgame.webgame.model.AccountGame;
import com.webgame.webgame.model.CartGame;
import com.webgame.webgame.model.Game;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.AccountGameRepository;
import com.webgame.webgame.repository.CartGameRepository;
import com.webgame.webgame.repository.GameRepository;
import com.webgame.webgame.repository.UserRepository;
import com.webgame.webgame.service.game.GameService;
import com.webgame.webgame.service.user.UserService;
import com.webgame.webgame.sevice.detailGame.DetailGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartGameService {
    @Autowired
    private CartGameRepository cartGameRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private AccountGameRepository accountGameRepository;


//lấy danh sách game có trong giỏ hàng theo id của user
    public List<CartGame> getCartGamesByUserId(Long userId) {
        List<CartGame> cartGames = cartGameRepository.findGamesInCartByUserId(userId);
//        vòng for này để tìm ra cái game trong giỏ hàng mà đã bán hết==> xóa đi
        for (CartGame cartGame : cartGames) {
               if (accountGameRepository.countGamechuaban(cartGame.getGame().getGameId())==0){
                   deleteCartGame(cartGame.getGame().getGameId(), userId);
               }
        }
                return cartGames;
    }
//
    public long countGameByUserIdInCart (Long userId){
        return cartGameRepository.countGamesByUserIdInCart(userId);
    }

    public BigDecimal calculateTotalPrice(Long userId) {
        return cartGameRepository.calculateTotalPriceCartByUserId(userId);
    }


    // Lưu CartGame vào cơ sở dữ liệu (chỉ cần gameId và userId)
    public void saveCartGame(Long gameId, Long userId) {
        Game game = gameRepository.findGameById(gameId);
        User user = userRepository.findUserById(userId);
        CartGame cartGame = new CartGame();
        cartGame.setGame(game);  // Lưu gameId
        cartGame.setUser(user);  // Lưu userId
        cartGameRepository.save(cartGame);
    }

//    Xóa cái game trong giỏ hàng
    public void  deleteCartGame(Long gameId, Long userId) {
        cartGameRepository.deleteGameIncart(gameId, userId);
    }
}


