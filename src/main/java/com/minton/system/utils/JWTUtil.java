package com.minton.system.utils;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;


public class JWTUtil {

    public static String generateToken(UserDetails userDetails){
        Date start = new Date();
        // 3 hours' valid time
        Date end = new Date(System.currentTimeMillis() + 3*60*60*1000);
        return JWT.create()
                .withAudience(userDetails.getUsername())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(userDetails.getPassword()));
    }

    /**
     * get userId from specific token
     * 从token获取userId
     */
    public static String getIdFromToken(String token){
        List<String> list = JWT.decode(token).getAudience();
        return list.get(0);
    }


    /**
     * get request
     * 获取request
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * get token from cookies
     * 从cookies获取token
     */
    public static String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie c : cookies){
            if(c.getName() == "token"){
                return c.getValue();
            }
        }
        return null;
    }
}
