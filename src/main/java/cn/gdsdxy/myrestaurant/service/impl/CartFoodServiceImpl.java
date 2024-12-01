package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.CartFood;
import cn.gdsdxy.myrestaurant.service.CartFoodService;
import cn.gdsdxy.myrestaurant.mapper.CartFoodMapper;
import org.springframework.stereotype.Service;

/**
 * 购物车食品业务实现类
 */
@Service
public class CartFoodServiceImpl extends ServiceImpl<CartFoodMapper, CartFood>
    implements CartFoodService{

}




