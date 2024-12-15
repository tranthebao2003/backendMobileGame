package com.webgame.webgame.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import com.webgame.webgame.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


 @Query("SELECT u FROM User u WHERE u.userId = :userId")
 User findUserById(@Param("userId") Long userId);

    User findByRole(String role);
    User findByEmail(String email);

    List<User> findAll();

    List<User> findListByRole(String role);

}
