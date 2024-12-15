package com.webgame.webgame.service.user;

import com.webgame.webgame.dto.UserLoginDto;
import com.webgame.webgame.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    User getUserByEmail(String email);

}
