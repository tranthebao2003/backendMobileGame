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
public class CartGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key auto-generated
    @Column(name = "cart_game_id")
    private Long cartGameId;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
