package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.Token;
import cn.gdsdxy.myrestaurant.vo.LoginUserVO;
import cn.gdsdxy.myrestaurant.pojo.RegisterUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * token业务接口
 */
public interface TokenService extends IService<Token> {
    boolean isExistToken(String token);
    void register(RegisterUser registerUser);
    LoginUserVO login(String username, String password);
    void logout(String userId);
}
