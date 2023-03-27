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

import static com.minton.common.ret.RetResult.retInfo;

@SpringBootTest
class SystemApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OperationLogMapper operationLogMapper;

    @Autowired
    ExceptionLogMapper exceptionLogMapper;
    @Autowired
    UserDetailsServiceImp userDetailsServiceImp;
    @Autowired
    UserController userController;

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





}
