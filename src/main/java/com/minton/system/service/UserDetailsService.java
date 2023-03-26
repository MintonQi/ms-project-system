package com.minton.system.service;

import com.minton.system.model.pojo.User;

public interface UserDetailsService {

    public User getUserDetailsByUsername(String username);


}
