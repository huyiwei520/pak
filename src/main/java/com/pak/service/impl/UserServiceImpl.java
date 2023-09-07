package com.pak.service.impl;

import com.pak.dto.UserDto;
import com.pak.service.UserService;
import com.pak.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lujun.chen on 2017/3/13.
 */
@Component
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public UserDto findUserById(Map<String,Object> params) {
    return userDao.findUserById(params);
  }

  @Override
  public void addUser(UserDto dto) {
    userDao.addUser(dto);
  }
}
