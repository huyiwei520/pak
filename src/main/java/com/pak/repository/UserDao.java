package com.pak.repository;

import com.pak.dto.UserDto;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    UserDto findUserById(Map<String,Object> params);
    void addUser(UserDto dto);
    List<UserDto> findUserList(Map<String,Object> params);

}