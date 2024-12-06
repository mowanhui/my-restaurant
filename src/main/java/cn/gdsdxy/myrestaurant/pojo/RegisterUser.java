package cn.gdsdxy.myrestaurant.pojo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 注册用户信息
 */
@Data
public class RegisterUser {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String phone;
    private String img;
}
