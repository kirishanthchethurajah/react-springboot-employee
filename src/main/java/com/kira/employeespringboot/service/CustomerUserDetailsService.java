package com.kira.employeespringboot.service;

import com.kira.employeespringboot.model.User;
import com.kira.employeespringboot.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user= userRepository.findByUserName(userName);
        CustomerUserDetails userDetails = null;
        if(user!=null){
            userDetails = new CustomerUserDetails();
            userDetails.setUser(user);
        }else{
            throw new UsernameNotFoundException("User Not exist in the database:"+ userName);
        }
        return userDetails;
    }
}
