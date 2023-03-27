package com.minton.system.controller;

import com.minton.common.ret.RetInfo;
import com.minton.common.ret.RetResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {


    @GetMapping("/test")
    public RetInfo testAccess(){
        return RetResult.retSuccess();
    }

}
