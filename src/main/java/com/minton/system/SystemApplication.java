package com.minton.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.minton.system", "com.minton.logging", "com.minton.common"})
@MapperScan(basePackages = {"com.minton.system.*", "com.minton.logging.*"})
//@Import(Swagger2Configuration.class)
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
        System.out.println("hello!");
    }
}
