package com.minton.system.service;

import com.minton.system.model.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

    public UserDetails loadUserByUsername(String username);


}
