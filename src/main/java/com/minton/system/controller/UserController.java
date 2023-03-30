package com.minton.system.controller;


import com.minton.common.ret.RetInfo;
import com.minton.common.ret.RetResult;
import com.minton.common.util.RedisUtil;
import com.minton.logging.OperLog;
import com.minton.system.model.pojo.User;
import com.minton.system.service.UserDetailsServiceImpl;
import com.minton.system.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test/users")
public class UserController {

    private UserDetailsServiceImpl userDetailsServiceImpl;

//    @Autowired
//    public UserController(UserDetailsServiceImpl userDetailsServiceImpl){
//        this.userDetailsServiceImpl = userDetailsServiceImpl;
//    }

//    @GetMapping("/{username}")
//    @OperLog(operationDesc = "查询user")
//    public RetInfo getUserDetailsByUsername(@PathVariable String username) {
//
////        User user = userDetailsServiceImpl.loadUserByUsername(username);
//
//        return RetResult.retSuccess();
//    }



    @GetMapping("/info")
    public UserDetails getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    @PostMapping("/logout")
    public RetInfo logout(HttpServletRequest request) {
        String token = JWTUtil.getToken(request);
        // 从redis中删除token
        RedisUtil.del(token);
        return RetResult.retSuccess("登出成功");
    }


}
