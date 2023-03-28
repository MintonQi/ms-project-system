package com.minton.system.controller;


import com.minton.common.ret.RetInfo;
import com.minton.common.ret.RetResult;
import com.minton.logging.OperLog;
import com.minton.system.model.pojo.User;
import com.minton.system.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test/users")
public class UserController {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsServiceImpl){
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @GetMapping("/{username}")
    @OperLog(operationDesc = "查询user")
    public RetInfo getUserDetailsByUsername(@PathVariable String username) {

//        User user = userDetailsServiceImpl.loadUserByUsername(username);

        return RetResult.retSuccess();
    }



}
