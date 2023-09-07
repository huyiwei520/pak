package com.pak.controller;

import com.pak.common.ResultModel;
import com.pak.dto.UserDto;
import com.pak.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lujun.chen on 2017/3/13.
 */
@Controller
public class UserController {

  @RequestMapping("/index")
  public String hello() {
    return "index";

  }

  @RequestMapping("/login")
  public String login(){
    return "login";
  }

  @RequestMapping("/")
  public String index() {
    return "index";

  }

  @RequestMapping("/historylist")
  public String historylist() {
    return "historylist";

  }

  @RequestMapping("/403")
  public String error(){
    return "403";
  }

  @PreAuthorize("hasRole('user')")
  @RequestMapping(value = "/admin",method = RequestMethod.POST)
  public String toAdmin(){
    return "helloAdmin";
  }
}
