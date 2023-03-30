package com.minton.system;

import com.minton.logging.dao.ExceptionLogMapper;
import com.minton.logging.dao.OperationLogMapper;
import com.minton.system.controller.UserController;
import com.minton.system.model.dao.UserMapper;
import com.minton.system.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static com.minton.common.ret.RetResult.retInfo;

@SpringBootTest
class SystemApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Autowired
    private ExceptionLogMapper exceptionLogMapper;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private UserController userController;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelect(){
        System.out.println(userDetailsServiceImpl.loadUserByUsername("admin").toString());
    }

    @Test
    void testRet(){
        String data = "data is here";
        System.out.println(retInfo(233, "masaji", data));
    }


    @Test
    void testRedis(){
//        redisTemplate.opsForValue().set("t3", "000");
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);
        System.out.println(redisTemplate.delete(keys));
        System.out.println(redisTemplate.keys("*"));
        System.out.println(redisTemplate.opsForValue().get("t3"));
    }

    @Test
    void testUserDetailsServiceImpl(){
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("test");
        System.out.println(userDetails.toString());
    }





}
