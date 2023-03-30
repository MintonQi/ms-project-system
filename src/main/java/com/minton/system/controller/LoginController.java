package com.minton.system.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.minton.common.ret.RetInfo;
import com.minton.common.ret.RetResult;
import com.minton.common.util.RSAUtil;
import com.minton.common.util.RedisUtil;
import com.minton.logging.OperLog;
import com.minton.system.service.UserDetailsServiceImpl;
import com.minton.system.utils.JWTUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    private DefaultKaptcha defaultKaptcha;

    private RedisTemplate redisTemplate;


    @Autowired
    public LoginController(UserDetailsServiceImpl userDetailsServiceImpl, DefaultKaptcha defaultKaptcha, RedisTemplate redisTemplate) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.defaultKaptcha = defaultKaptcha;
        this.redisTemplate = redisTemplate;
    }

    @OperLog(operationDesc = "生成验证码")
    @GetMapping("/{username}/kaptcha")
    public void createKeptcha(@PathVariable String username,  HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        System.out.println("验证码已生成-->"+text);

        //可以使用UUID
        String key = Constants.KAPTCHA_SESSION_KEY + ":" + username;
        redisTemplate.opsForValue().set(key, text, 60, TimeUnit.SECONDS);


        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);

        out.flush();
        out.close();
    }

    @OperLog(operationDesc = "登录授权")
    @PostMapping("/login")
    public RetInfo login(String username, String password, String code, HttpServletResponse response){
        // check Captcha
        String redisKey = Constants.KAPTCHA_SESSION_KEY + ":" + username;
        String redisCode = (String) redisTemplate.opsForValue().get(redisKey);
        if(redisCode == null || !code.equalsIgnoreCase(redisCode)){
            return RetResult.retError("验证码错误");
        }
        redisTemplate.delete(redisKey);

        //解密密码
//        String password = RSAUtil.decrypt(password, privateKey);

        //获取用户信息和权限，验证密码正确性
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
        if(!userDetails.getPassword().equals(password)){
            return RetResult.retError("密码错误");
        }
        //保存信息到SecurityContextHolder中
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //为用户创建token令牌，
        String jwtToken = JWTUtil.generateToken(userDetails);
        response.addCookie(new Cookie("token", jwtToken));
        System.out.println("cookie:"+jwtToken);

        // 同时结合redis保存用户在线状态
        RedisUtil.set(username, jwtToken);

        // 并根据用户名判断是否当前账号已经在线，已在线需要剔除原本的token。
        // redis set方法不是自动覆盖了吗。。。

        return RetResult.retSuccess(userDetails);
    }

}












