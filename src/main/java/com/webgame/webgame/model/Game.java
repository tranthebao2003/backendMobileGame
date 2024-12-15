package com.webgame.webgame.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "game_name", nullable = false)
    private String gameName;

    private String gameImg;
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer quantity = 0;

    @CreationTimestamp
    private Date createDate;

    @ToString.Exclude
    @JsonIgnore
   //Điều này nói cho JPA biết rằng accountGames liên kết với Game qua khóa ngoại gameId.
    @OneToMany(mappedBy = "game",cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Collection<AccountGame> accountGames;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "game", fetch = FetchType.LAZY)
    private Collection<Review> reviews;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "game", fetch = FetchType.LAZY)
    private Collection<CartGame> cartGames;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "game", fetch = FetchType.LAZY)
    private Collection<ImageGame> imageGames;


    // đối với cột có qua hệ nhìu nhìu với cột khác thì
    // thêm dòng @ToString.Exclude bỏ ToString đi
    // tránh vòng lặp vô hạn
    // ở đây game N-N category bảng trung gian là categoryGame
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "game", fetch = FetchType.EAGER)
    private Collection<CategoryGame> categoryGames;
}
