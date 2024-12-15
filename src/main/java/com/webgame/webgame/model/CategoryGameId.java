package com.webgame.webgame.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


// CategoryGameId là khóa chính tổng hợp (composite primary key)
// dùng để ánh xạ hai trường categoryId và gameId từ bảng CategoryGame.
@Data
@Embeddable // Chỉ định rằng lớp này có thể được sử dụng làm khóa chính tổng hợp trong một thực thể khác
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // Tự động tạo phương thức equals() và hashCode() để so sánh các đối tượng khóa chính.
public class CategoryGameId implements Serializable {
    // categoryId và gameId: Là hai thành phần của khóa chính, đại diện cho mối quan hệ giữa Category và Game.
    private Long categoryId; // Trường khóa chính category_id
    private Long gameId; // Trường khóa chính game_id
}
