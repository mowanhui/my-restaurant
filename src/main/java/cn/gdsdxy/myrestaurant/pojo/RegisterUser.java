package cn.gdsdxy.myrestaurant.pojo;

import lombok.Data;

/**
 * 注册用户信息
 */
@Data
public class RegisterUser {
    private String username;
    private String realName;
    private String password;
    private String phone;
}
