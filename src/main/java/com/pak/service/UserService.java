package com.pak.service;

import com.pak.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * Created by lujun.chen on 2017/3/13.
 */
@Service
public interface UserService {
    UserDto findUserById(Map<String,Object> params);
    void addUser(UserDto dto);
}
