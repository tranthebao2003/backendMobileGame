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
//@Table(
//        uniqueConstraints = {@UniqueConstraint(columnNames = "username")}
//)
public class AccountGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_game_id")
    private Long accountGameId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

//    status = 0: chua ban, 1 la da ban
    private boolean status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
