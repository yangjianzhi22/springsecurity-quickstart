package com.yang.springsecurity.security;

import com.yang.springsecurity.entity.User;
import com.yang.springsecurity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDetails2Service implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名从数据库获取用户信息
        User user = userMapper.queryByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 因为数据库是明文，所以这里需加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new LoginUser(user);
    }
}