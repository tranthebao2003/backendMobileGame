package com.webgame.webgame.service.admin;

import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersByRoleId(String role){
        return userRepository.findListByRole(role);
    }
//
//    public User getUserById(long id) {
//        // Trả về user theo id, nếu không tìm thấy trả về null
//        return userRepository.findUserById(id);
//    }


}
