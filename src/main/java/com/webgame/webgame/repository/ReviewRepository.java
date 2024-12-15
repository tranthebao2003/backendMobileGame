package com.webgame.webgame.repository;

import com.webgame.webgame.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.game.gameId = :gameId ORDER BY r.createDate DESC")
    List<Review> findReviewsByGameId(@Param("gameId") Long gameId);
}

/// / Basic CRUD Operations
//T save(T entity); // Lưu hoặc cập nhật một thực thể vào cơ sở dữ liệu.
//Optional<T> findById(ID id); // Tìm một thực thể theo ID.
//boolean existsById(ID id); // Kiểm tra một thực thể có tồn tại theo ID hay không.
//List<T> findAll(); // Trả về danh sách tất cả các thực thể.
//List<T> findAllById(Iterable<ID> ids); // Tìm các thực thể theo danh sách ID.
//void deleteById(ID id); // Xóa thực thể theo ID.
//void delete(T entity); // Xóa một thực thể cụ thể.
//void deleteAllById(Iterable<? extends ID> ids); // Xóa nhiều thực thể theo danh sách ID.
//void deleteAll(Iterable<? extends T> entities); // Xóa nhiều thực thể cụ thể.
//void deleteAll(); // Xóa tất cả các thực thể.
//
//// Paging and Sorting
//List<T> findAll(Sort sort); // Trả về danh sách tất cả các thực thể với sắp xếp.
//Page<T> findAll(Pageable pageable); // Trả về danh sách thực thể được phân trang.
//
//// Count
//long count(); // Đếm số lượng thực thể trong cơ sở dữ liệu.
//
//// Custom Query Creation Based on Method Name
//List<T> findByAttributeName(Type attributeValue);
//// Tự động tìm kiếm theo tên thuộc tính (AttributeName là placeholder cho tên thuộc tính cụ thể).
//
//List<T> findByAttributeNameAndAnotherAttributeName(Type attributeValue, Type anotherValue);
//// Kết hợp tìm kiếm nhiều thuộc tính với "And".
//
//List<T> findByAttributeNameOrAnotherAttributeName(Type attributeValue, Type anotherValue);
//// Kết hợp tìm kiếm nhiều thuộc tính với "Or".
//
//List<T> findByAttributeNameOrderByAnotherAttributeNameAsc(Type attributeValue);
//// Tìm kiếm và sắp xếp theo thứ tự tăng dần.
//
//List<T> findByAttributeNameOrderByAnotherAttributeNameDesc(Type attributeValue);
//// Tìm kiếm và sắp xếp theo thứ tự giảm dần.
//
//// Custom Query Keywords
//List<T> findByAttributeNameContaining(Type attributeValue);
//// Tìm kiếm các thực thể mà giá trị thuộc tính chứa giá trị cụ thể.
//
//List<T> findByAttributeNameLike(String pattern);
//// Tìm kiếm với mẫu ký tự (ví dụ: %pattern%).
//
//List<T> findByAttributeNameStartingWith(String prefix);
//// Tìm kiếm các thực thể mà giá trị thuộc tính bắt đầu bằng prefix.
//
//List<T> findByAttributeNameEndingWith(String suffix);
//// Tìm kiếm các thực thể mà giá trị thuộc tính kết thúc bằng suffix.
//
//List<T> findByAttributeNameIn(Collection<Type> values);
//// Tìm kiếm các thực thể có giá trị thuộc tính nằm trong danh sách giá trị.
//
//List<T> findByAttributeNameNotIn(Collection<Type> values);
//// Tìm kiếm các thực thể có giá trị thuộc tính không nằm trong danh sách giá trị.
//
//List<T> findByAttributeNameIsNull();
//// Tìm các thực thể có giá trị thuộc tính bằng null.
//
//List<T> findByAttributeNameIsNotNull();
//// Tìm các thực thể có giá trị thuộc tính không bằng null.
//
//List<T> findByAttributeNameTrue();
//// Tìm các thực thể có giá trị thuộc tính là true (boolean).
//
//List<T> findByAttributeNameFalse();
//// Tìm các thực thể có giá trị thuộc tính là false (boolean).
//
//List<T> findByAttributeNameGreaterThan(Type value);
//// Tìm các thực thể có giá trị thuộc tính lớn hơn giá trị cụ thể.
//
//List<T> findByAttributeNameLessThan(Type value);
//// Tìm các thực thể có giá trị thuộc tính nhỏ hơn giá trị cụ thể.
//
//List<T> findByAttributeNameBetween(Type startValue, Type endValue);
//// Tìm các thực thể có giá trị thuộc tính nằm trong khoảng giá trị.
//
//// Examples with "OrderBy"
//List<T> findByAttributeNameOrderByAttributeNameAsc(Type value);
//// Tìm kiếm và sắp xếp theo thứ tự tăng dần của thuộc tính.
//
//List<T> findByAttributeNameOrderByAttributeNameDesc(Type value);
//// Tìm kiếm và sắp xếp theo thứ tự giảm dần của thuộc tính.
