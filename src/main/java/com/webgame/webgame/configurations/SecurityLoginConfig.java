package com.webgame.webgame.configurations;

//import com.webgame.webgame.service.userLogin.CustomAuthenticationFailureHandler;
import com.webgame.webgame.service.userLogin.CustomSuccessLoginHandler;
import com.webgame.webgame.service.userLogin.CustomUserLoginDetailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityLoginConfig {
    @Autowired
    CustomSuccessLoginHandler customSuccessHandler;

    @Autowired
    CustomUserLoginDetailService customUserLoginDetailService;

//    @Autowired
//    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(c -> c.disable())
//                .authorizeHttpRequests(requests -> requests.requestMatchers("/admin")
//                        .hasAuthority("admin").requestMatchers("/home").permitAll()
//                        .requestMatchers("/register_login", "/css/**","/img/**","/", "/cart","/user_info","/user", "/get_user_info","send-email","forgot-password","login-email").permitAll()
//                        .anyRequest().authenticated())
                .authorizeHttpRequests(requests -> requests
                        .anyRequest()
                        .permitAll())
                .formLogin(form -> form.loginPage("/register_login").loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler).permitAll())
                .oauth2Login(oauth2login->{
                    oauth2login.successHandler(new AuthenticationSuccessHandler() {
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                            response.sendRedirect("/login-email");
                        }
                    });
                });


        return http.build();
    }
    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserLoginDetailService).passwordEncoder(passwordEncoder());
    }
}

