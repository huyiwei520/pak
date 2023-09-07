package com.pak.service;

import com.pak.dto.UserDto;
import com.pak.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huyiwei on 2018/10/24.
 */
@Component("userDataService")
public class UserDataService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String,Object> params = new HashMap<>();
        params.put("userName",username);
        UserDto userDto = userDao.findUserById(params);
        return userDto;
    }
}
