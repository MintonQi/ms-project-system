package com.minton.system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class springSecurityConfig {


//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(req->{
            req.requestMatchers( "/test","/login/**","/test/users/**","/kaptcha",
                            "/swagger-ui.html", "/swagger-ui/**","/v3/api-docs/**",
                            "/static/**")
                    .permitAll();

            req.anyRequest().authenticated();
        });
        //指定登录path
//        http.formLogin().loginPage("/page/login.html").loginProcessingUrl("/doLogin")
//                .usernameParameter("uname").passwordParameter("pwd")
//                .defaultSuccessUrl("/test");
        //禁用csrf跨站请求伪造攻击防护
        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        return http.build();
    }


}
