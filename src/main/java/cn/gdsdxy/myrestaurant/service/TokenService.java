package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.Token;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * token业务接口
 */
public interface TokenService extends IService<Token> {
    boolean isExistToken(String token);
    void register()
}
