package com.yang.springsecurity.mapper;

import com.yang.springsecurity.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User queryByUsername(@Param("username") String username);
}