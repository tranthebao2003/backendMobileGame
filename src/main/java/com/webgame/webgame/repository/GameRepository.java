package com.webgame.webgame.repository;



import com.webgame.webgame.model.Category;
import com.webgame.webgame.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    // phương thức này jpa nó có sẵn
    // tìm kiếm all game chứa gameName mà user truyền vào
    // ko phân biệt hoa thường
    List<Game> findGamesByGameNameContainingIgnoreCase(String searchInput);
    List<Game> findByCategoryGames_Category(Category category);

    Game findGameByGameId(Long gameId);


    @Query("SELECT g FROM Game g WHERE g.gameId = :gameId")
    Game findGameById(@Param("gameId") Long gameId);


    // ở đây là mình tương tác trực tiếp với database ko phải thực thể vì nativeQuery = true
    @Query(value = """
    SELECT g.game_img, g.game_name, g.description, g.game_id, g.price,COUNT(DISTINCT ag.account_game_id) AS quantity,
           GROUP_CONCAT(DISTINCT c.category_name SEPARATOR ', ') AS category_list
    FROM game g
    LEFT JOIN account_game ag ON g.game_id = ag.game_id
    LEFT JOIN category_game cg ON g.game_id = cg.game_id
    LEFT JOIN category c ON cg.category_id = c.category_id
    GROUP BY g.game_img, g.game_name, g.description, g.game_id, g.price
   """, nativeQuery = true)
    Page<Object[]> findGamesAndQuantityCategory(Pageable pageable);

    // phục vụ cho update game, tương tác với entity
    // tìm những category liên kết với categoryGame với đk gameId trong categoryGame = gameId được truyền vào
    // :gameId giống với param bên trong @Param("gameId")
    @Query("""
        SELECT cg.category
        FROM CategoryGame cg
        WHERE cg.game.gameId = :gameId
        """)
    List<Category> findCategoriesByGameId(@Param("gameId") Long gameId);

}
