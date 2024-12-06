package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * 用户业务接口
 */
public interface UserService extends IService<User> {
    void saveUser(User user);
    void deleteUser(String userId);
    PageInfo<User> getUserPage(SearchUser searchUser);
}
