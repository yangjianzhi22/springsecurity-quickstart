package com.yang.springsecurity;

import com.yang.springsecurity.entity.User;
import com.yang.springsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringsecurityApplicationTests {

	@Resource
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		User user = userMapper.queryByUsername("237502631@qq.com");
		System.out.println(user);
	}

}
