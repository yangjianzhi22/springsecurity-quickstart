package com.yang.springsecurity.controller;

import com.yang.springsecurity.entity.User;
import com.yang.springsecurity.mapper.UserMapper;
import com.yang.springsecurity.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/get")
    public Object getUser(@RequestParam String username){
        return userMapper.queryByUsername(username);
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        // 认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        try{
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // 认证失败
            if(Objects.isNull(authentication)) {
                throw new RuntimeException("用户名或密码错误");
            }
            // 将用户信息存SecurityContextHolder，授权也在这一步实现
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 认证成功
            return (LoginUser) authentication.getPrincipal();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}