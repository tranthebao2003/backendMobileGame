package com.webgame.webgame.service.userLogin;

import com.webgame.webgame.model.User;
import com.webgame.webgame.repository.UserRepository;
import com.webgame.webgame.dto.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public void save(UserLoginDto userLoginDto) {
        User user = new User(
                userLoginDto.getEmail(),
                passwordEncoder.encode(userLoginDto.getPassword()),
                userLoginDto.getRole(),
                userLoginDto.getUsername(),
                userLoginDto.getPhone()
        );
        userRepository.save(user);
    }
}
