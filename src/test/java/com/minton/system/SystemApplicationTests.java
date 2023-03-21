package com.minton.system;

import com.minton.system.dao.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.minton.common.ret.RetResult.retInfo;

@SpringBootTest
class SystemApplicationTests {

    @Autowired
    UserMapper userMapper;


    @Test
    void contextLoads() {
    }

    @Test
    void testSelect(){
        System.out.println(userMapper.testSelect());
    }

    @Test
    void testRet(){
        String data = "data is here";
        System.out.println(retInfo(233, "masaji", data));
    }

}
