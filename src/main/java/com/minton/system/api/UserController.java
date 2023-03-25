package com.minton.system.api;


import com.minton.common.ret.RetInfo;
import com.minton.common.ret.RetResult;
import com.minton.logging.OperLog;
import com.minton.system.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户相关操作")
@RestController("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/test")
    @OperLog(operationDesc = "测试查询admin")
    public RetInfo testSelectUser() {

        String s = userService.testSelect();

        return RetResult.retSuccess(s);
    }

}