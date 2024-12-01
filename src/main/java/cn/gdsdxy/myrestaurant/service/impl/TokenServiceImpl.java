package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.Token;
import cn.gdsdxy.myrestaurant.service.TokenService;
import cn.gdsdxy.myrestaurant.mapper.TokenMapper;
import org.springframework.stereotype.Service;

/**
 * token业务实现类
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token>
    implements TokenService{

    /**
     * 判断是否存在token
     * @param token
     * @return
     */
    @Override
    public boolean isExistToken(String token) {
        //组装查询条件,获取未删除的token
        LambdaQueryWrapper<Token> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Token::getToken,token)
                .eq(Token::getIsDeleted,0);
        //获取是否存在这个token
        boolean isExist=this.exists(lambdaQueryWrapper);
        return isExist;
    }

    @Override
    public void register() {
        
    }
}




