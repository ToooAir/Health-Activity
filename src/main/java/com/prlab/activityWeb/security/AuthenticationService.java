//package com.prlab.activityWeb.security;
//
//import com.prlab.activityWeb.model.User;
//import com.prlab.activityWeb.model.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class AuthenticationService implements AuthenticationProvider {
//
//    @Autowired
//    private UserRepository userRepository;
//
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User user = userRepository.findByUsername(username);
//        if (user != null) {
//            if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
//                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//}
