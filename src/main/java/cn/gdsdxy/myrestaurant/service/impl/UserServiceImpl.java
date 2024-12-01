package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.service.UserService;
import cn.gdsdxy.myrestaurant.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




