package com.webgame.webgame.dto.gameDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSaleDto {
    private Long gameId;
    private String gameName;
    private BigDecimal price;
    private String gameImg;
    private Long totalGame;
}
