package com.webgame.webgame.repository;

import com.webgame.webgame.model.Orders;
import com.webgame.webgame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    List<Orders> findByUser(User user);

    List<Orders> findByUser_UserId(Long userId);


    @Override
    List<Orders> findAll();
}
