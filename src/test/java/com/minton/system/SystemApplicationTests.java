package com.minton.system;

import com.minton.logging.dao.ExceptionLogMapper;
import com.minton.logging.dao.OperationLogMapper;
import com.minton.logging.entity.ExceptionLog;
import com.minton.system.controller.UserController;
import com.minton.system.model.dao.UserMapper;
import com.minton.system.service.UserDetailsServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

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
    private UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    private UserController userController;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelect(){
        System.out.println(userDetailsServiceImp.getUserDetailsByUsername("admin").toString());
    }

    @Test
    void testRet(){
        String data = "data is here";
        System.out.println(retInfo(233, "masaji", data));
    }


    @Test
    void testRedis(){
        redisTemplate.opsForValue().set("t3", "000");

        System.out.println(redisTemplate.opsForValue().get("t3"));
    }



}
