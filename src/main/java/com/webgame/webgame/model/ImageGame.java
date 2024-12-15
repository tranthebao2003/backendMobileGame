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
public class ImageGame {
    @Id
    @Column(name = "img_game_id")
    private Long imgGameId;
    private String imgGameLink;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}