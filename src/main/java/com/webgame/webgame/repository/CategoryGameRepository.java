package com.webgame.webgame.repository;
import com.webgame.webgame.model.CategoryGame;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryGameRepository extends JpaRepository<CategoryGame, Long> {
    // Phương thức deleteByGameId dùng để xóa tất cả các liên kết giữa một gameId và các thể loại trong bảng CategoryGame.
    @Transactional // Đảm bảo quá trình xóa thực thi trong một giao dịch (transaction). Nếu có lỗi xảy ra, toàn bộ thay đổi sẽ được rollback.
    @Modifying // Thông báo cho JPA rằng đây là một truy vấn Cập nhật (UPDATE) hoặc Xóa (DELETE), không phải truy vấn lấy dữ liệu (SELECT).
    @Query("DELETE FROM CategoryGame cg WHERE cg.id.gameId = :gameId")
    void deleteByGameId(@Param("gameId") Long gameId);
}
