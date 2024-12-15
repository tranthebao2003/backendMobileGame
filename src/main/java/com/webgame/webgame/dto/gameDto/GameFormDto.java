package com.webgame.webgame.dto.gameDto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Collection;


// class này phục vụ cho add and update game
@Data
public class GameFormDto {
    private String gameName;
    private String description;

    private BigDecimal price;
    private MultipartFile gameImg; // File ảnh
    private Collection<Long> categoryIds; // Danh sách ID thể loại (checkbox)

}
