package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.vo.LoginUserVO;
import cn.gdsdxy.myrestaurant.pojo.RegisterUser;
import cn.gdsdxy.myrestaurant.service.TokenService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mowanhui
 * @version 1.0
 * @date 2024/11/30 13:23
 */
@Tag(name="LoginController",description = "登录控制器")
@RestController
public class LoginController {
    @Autowired
    private TokenService tokenService;

    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    @Operation(summary = "登录")
    @PostMapping("login")
    public FwResult<LoginUserVO> login(@RequestParam("username") String username,
                                       @RequestParam("password") String password){
        return FwResult.ok(tokenService.login(username, password));
    }

    @Operation(summary = "注册")
    @PostMapping("register")
    public FwResult<LoginUserVO> register(@RequestBody @Validated RegisterUser registerUser){
        tokenService.register(registerUser);
        return FwResult.ok();
    }

    @Operation(summary = "退出登录")
    @PostMapping("logout")
    public FwResult<Object> logout(@NotBlank(message = "用户id不能为空") @RequestParam("userId")String userId){
        tokenService.logout(userId);
        return FwResult.ok();
    }
}
