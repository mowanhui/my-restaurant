package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.ShoppingCart;
import cn.gdsdxy.myrestaurant.service.ShoppingCartService;
import cn.gdsdxy.myrestaurant.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
 * 购物车业务实现类
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




