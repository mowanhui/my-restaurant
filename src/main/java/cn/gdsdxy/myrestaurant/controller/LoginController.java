package cn.gdsdxy.myrestaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mowanhui
 * @version 1.0
 * @date 2024/11/30 13:23
 */
@RestController
public class LoginController {

    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public String login(@RequestParam("username") String username,@RequestParam("username") String password){

    }
}
