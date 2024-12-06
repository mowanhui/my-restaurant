package cn.gdsdxy.myrestaurant.vo;

import lombok.Data;

/**
 * 登录用户
 */
@Data
public class LoginUserVO {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户名
     */
    private String username;
    /**
     * token值
     */
    private String token;

    private String img;
}
