package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.entity.ShoppingCart;
import cn.gdsdxy.myrestaurant.vo.ShoppingCartVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 购物车业务接口
 */
public interface ShoppingCartService extends IService<ShoppingCart> {
    void saveFoodToCart(ShoppingCart cartFood);
    void delFoodToCart(String shoppingCartFoodId);
    ShoppingCartVO getFoodOfCartList(String userId);

}
