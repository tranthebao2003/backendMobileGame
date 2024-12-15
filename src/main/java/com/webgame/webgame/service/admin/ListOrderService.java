package com.webgame.webgame.service.admin;

import com.webgame.webgame.model.Orders;
import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListOrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getOrderByUserId(Long userId) {
        return orderRepository.findByUser_UserId(userId);
    }
}
