package cn.gdsdxy.myrestaurant.service.impl;

import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.service.UserService;
import cn.gdsdxy.myrestaurant.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/**
 * 用户业务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public void saveUser(User user) {
        // 判断用户id不为空，则为更新用户信息
        if(StringUtils.isNotBlank(user.getId())){
            user.setUpdateTime(new Date());
            //更新用户信息
            this.updateById(user);
        }else {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            String password = user.getPassword();
            if(StringUtils.isBlank(password)){
               //如果密码为空，则设置默认密码为：123
                password = "123";
            }
            // 对密码进行加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(md5Password);
            // 保存用户信息
            this.save(user);
        }
    }

    @Override
    public void deleteUser(String userId) {
        this.removeById(userId);
    }

    @Override
    public PageInfo<User> getUserPage(SearchUser searchUser) {
        PageHelper.startPage(searchUser.getPageNum(), searchUser.getPageSize());
        // 构造查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(searchUser.getKeyword()), User::getUsername, searchUser.getKeyword())
                .like(StringUtils.isNotBlank(searchUser.getKeyword()), User::getRealName, searchUser.getKeyword())
                .like(StringUtils.isNotBlank(searchUser.getKeyword()), User::getPhone, searchUser.getKeyword())
                .eq(StringUtils.isNotBlank(searchUser.getRole()), User::getRole, searchUser.getRole())
                .eq(User::getIsDeleted, 0);
        // 调用分页插件进行分页
        return new PageInfo<>(this.list(queryWrapper));
    }
}




