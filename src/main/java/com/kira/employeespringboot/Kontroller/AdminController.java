package com.kira.employeespringboot.Kontroller;

import com.kira.employeespringboot.model.User;
import com.kira.employeespringboot.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public AdminController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/admin/add")
    public String addUserByAdmin(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmedPassword(passwordEncoder.encode(user.getConfirmedPassword()));
        userRepository.save(user);
        return "user added Successfully";

    }
}
