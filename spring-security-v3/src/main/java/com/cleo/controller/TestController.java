package com.cleo.controller;

import com.cleo.security.AuthenticationService;
import com.cleo.security.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @className: com.cleo.controller-> TestController
 * @description:
 * @author: cleo
 * @createDate: 2021-10-28 17:10
 * @version: 1.0
 */
@Controller
@RequestMapping("/user")
public class TestController {

    @Autowired
    AuthenticationService authenticationService;


    @RequestMapping("/login")
    @ResponseBody
    public LoginUser login(String name,String password){
        Authentication authenticate = authenticationService.authenticate(name, password);
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        return loginUser;
    }
    @RequestMapping("/logOut")
    @ResponseBody
    public String logOut(String token){
        return "logOut";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test";
    }


    @RequestMapping("/testRule")
    @ResponseBody
    public String testRule(){
        return "testRule";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(){
        return "add";
    }
}
