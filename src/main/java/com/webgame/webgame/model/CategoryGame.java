package com.webgame.webgame.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryGame {

    @EmbeddedId
    private CategoryGameId id; // Composite key


    @ToString.Exclude
    @ManyToOne
    @MapsId("gameId") // Tham chiếu đến gameId trong CategoryGameId
    @JoinColumn(name = "game_id")
    private Game game;


    @ToString.Exclude
    @ManyToOne
    @MapsId("categoryId") // Tham chiếu đến categoryId trong CategoryGameId
    @JoinColumn(name = "category_id")
    private Category category;

    public CategoryGame(Game game, Category category) {
        this.id = new CategoryGameId(game.getGameId(), category.getCategoryId());
        this.game = game;
        this.category = category;
    }
}