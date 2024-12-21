//package com.webgame.webgame.service.userLogin;
//
//import com.webgame.webgame.model.User;
//import com.webgame.webgame.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
////tìm kiếm nguười dùng dựa trên email
////trả về đối tượng customuserlogindetail
//
//@Service
//public class CustomUserLoginDetailService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("not found");
//        }
//        return new CustomUserLoginDetail(user);
//    }
//
//}
