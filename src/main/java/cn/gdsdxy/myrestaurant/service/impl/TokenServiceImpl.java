package cn.gdsdxy.myrestaurant.service.impl;

import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.vo.LoginUserVO;
import cn.gdsdxy.myrestaurant.pojo.RegisterUser;
import cn.gdsdxy.myrestaurant.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.Token;
import cn.gdsdxy.myrestaurant.service.TokenService;
import cn.gdsdxy.myrestaurant.mapper.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * token业务实现类
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token>
    implements TokenService{

    @Autowired
    private UserService userService;

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

    /**
     * 注册用户
     * @param registerUser
     */
    @Override
    public void register(RegisterUser registerUser) {
        //判断用户名是否存在
        User user=userService.getOne(new LambdaQueryWrapper<User>()
               .eq(User::getUsername, registerUser.getUsername())
               .eq(User::getIsDeleted,0));
        if(user!=null){
            throw new RuntimeException("用户名已存在");
        }
        user=new User();
        user.setUsername(registerUser.getUsername());
        user.setRealName(registerUser.getRealName());
        String md5Password = DigestUtils.md5DigestAsHex(registerUser.getPassword().getBytes());
        user.setPassword(md5Password);
        user.setRole("user");
        user.setPhone(registerUser.getPhone());
        user.setIsDeleted(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setImg(registerUser.getImg());
        //保存用户
        userService.save(user);
    }

    @Override
    public LoginUserVO login(String username, String password) {
        //根据用户名和密码查询用户
        User user=userService.getOne(new LambdaQueryWrapper<User>()
               .eq(User::getUsername,username)
               .eq(User::getIsDeleted,0));
        //判断用户是否存在
        if(user==null){
            throw new RuntimeException("用户不存在");
        }
        //判断密码是否正确
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!md5Password.equals(user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        //组装登录用户信息
        LoginUserVO loginUserVO =new LoginUserVO();
        //如果存在token则更新token
        Token token=this.getOne(new LambdaQueryWrapper<Token>()
               .eq(Token::getUserId,user.getId())
               .eq(Token::getIsDeleted,0));
        if(token!=null){
            token.setToken(UUID.randomUUID().toString());
            token.setUpdateTime(new Date());
            this.updateById(token);
            loginUserVO.setToken(token.getToken());
        }else {
            //保存token
            Token tokenEntity=new Token();
            tokenEntity.setToken(UUID.randomUUID().toString());
            tokenEntity.setUserId(user.getId());
            tokenEntity.setCreateTime(new Date());
            tokenEntity.setUpdateTime(new Date());
            tokenEntity.setIsDeleted(0);
            this.save(tokenEntity);
            loginUserVO.setToken(tokenEntity.getToken());
        }

        loginUserVO.setUserId(user.getId());
        loginUserVO.setUsername(user.getUsername());
        loginUserVO.setRealName(user.getRealName());
        loginUserVO.setImg(user.getImg());

        return loginUserVO;
    }

    /**
     * 退出登录
     * @param userId
     */
    @Override
    public void logout(String userId) {
        //根据userId删除token
        Token token=this.getOne(new LambdaQueryWrapper<Token>()
               .eq(Token::getUserId,userId)
               .eq(Token::getIsDeleted,0));
        if(token!=null){
            token.setUpdateTime(new Date());
            this.removeById(token);
        }
    }
}




