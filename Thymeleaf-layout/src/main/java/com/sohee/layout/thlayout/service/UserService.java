package com.sohee.layout.thlayout.service;

import com.sohee.layout.thlayout.model.User;
import com.sohee.layout.thlayout.model.Role;
import com.sohee.layout.thlayout.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true); // 인증 활성화

        Role role = new Role();
        role.setRoleid(1l); // long
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
