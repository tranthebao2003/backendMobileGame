package com.webgame.webgame.repository;

import com.webgame.webgame.model.AccountGame;
import com.webgame.webgame.model.CartGame;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CartGameRepository extends JpaRepository<CartGame, Long> {

//    lấy danh sách game trong giỏ hàng dựa trên iduser
    @Query("SELECT c FROM CartGame c WHERE c.user.userId = :userId")
    List<CartGame> findGamesInCartByUserId(@Param("userId") Long userId);

    // Truy vấn tính tổng giá các game trong giỏ hàng của user
    @Query("SELECT SUM(c.game.price) FROM CartGame c WHERE c.user.userId = :userId")
    BigDecimal calculateTotalPriceCartByUserId(@Param("userId") Long userId);


    @Modifying
    @Transactional
    @Query("DELETE FROM CartGame c WHERE c.game.gameId = :gameId AND c.user.userId = :userId")
    void deleteGameIncart(@Param("gameId") Long gameId, @Param("userId") Long userId);


//    Xóa hết game trong một giỏ hàng dựa vào userID (tại vì khi mua xong thì xóa giỏ hàng đi)
    @Modifying
    @Transactional
    @Query("DELETE FROM CartGame c WHERE c.user.userId = :userId")
    void deleteAllGamesInCartByUserId(@Param("userId") Long userId);


//   Đếm số lượng game trong một giỏ hàng
    @Query("SELECT COUNT(c) FROM CartGame c WHERE c.user.userId = :userId")
    long countGamesByUserIdInCart(Long userId);


//    Kiểm tra xem game đó đã tồn tại trong cartgame chưa
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM CartGame c WHERE c.game.gameId = :gameId AND c.user.userId = :userId")
    boolean existsByGameAndUser(@Param("gameId") Long gameId, @Param("userId") Long userId);
}
