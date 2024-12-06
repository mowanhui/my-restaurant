package cn.gdsdxy.myrestaurant.service.impl;

import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.entity.ShoppingCart;
import cn.gdsdxy.myrestaurant.mapper.ShoppingCartMapper;
import cn.gdsdxy.myrestaurant.service.FoodService;
import cn.gdsdxy.myrestaurant.vo.ShoppingCartFoodVO;
import cn.gdsdxy.myrestaurant.vo.ShoppingCartVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 购物车业务实现类
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements ShoppingCartService {
    @Autowired
    private FoodService foodService;

    /**
     * 加入购物车
     *
     * @param cartFood
     */
    @Override
    public void saveFoodToCart(ShoppingCart cartFood) {
        //查询是否存在相同的食品
        ShoppingCart existCart = this.getOne(Wrappers.<ShoppingCart>lambdaQuery()
               .eq(ShoppingCart::getFoodId, cartFood.getFoodId())
               .eq(ShoppingCart::getUserId, cartFood.getUserId())
               .eq(ShoppingCart::getIsDeleted, 0));
        if (existCart!= null) {
            //如果存在相同的食品，则更新数量
            if(cartFood.getFoodNum()==0){
                //如果数量为0，则删除该条记录
                this.removeById(existCart.getId());
            }else{
                //如果数量不为0，则更新数量
                existCart.setFoodNum(cartFood.getFoodNum());
                existCart.setUpdateTime(new Date());
                this.updateById(existCart);
            }

        }else {
            //如果不存在相同的食品，则加入购物车
            if(cartFood.getFoodNum()==0){
                //如果数量为0，则不加入购物车
                return;
            }
            cartFood.setCreateTime(new Date());
            cartFood.setUpdateTime(new Date());
            this.save(cartFood);
        }
    }


    /**
     * 移除购物车中的商品
     * @param shoppingCartFoodId
     */
    @Override
    public void delFoodToCart(String shoppingCartFoodId) {
        this.removeById(shoppingCartFoodId);
    }


    /**
     * 获取购物车中的商品列表
     * @param userId
     * @return
     */
    @Override
    public ShoppingCartVO getFoodOfCartList(String userId) {
        ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
        shoppingCartVO.setTotalPrice(0);
        shoppingCartVO.setTotalCount(0);
        shoppingCartVO.setFoodList(new ArrayList<>());

        // 获取购物车中的商品id
        List<ShoppingCart> cartList = this.list(Wrappers.<ShoppingCart>lambdaQuery()
                .eq(ShoppingCart::getUserId, userId)
                .eq(ShoppingCart::getIsDeleted, 0)
                .orderByDesc(ShoppingCart::getCreateTime));
        List<String> foodIdList = new ArrayList<>();
        for (ShoppingCart cart : cartList) {
            Food food = foodService.getById(cart.getFoodId());
            foodIdList.add(food.getId());
        }

        //如果购物车为空，则返回空列表
        if (foodIdList.isEmpty()) {
            return shoppingCartVO;
        }

        // 根据商品id获取食品列表
        LambdaQueryWrapper<Food> queryWrapper = Wrappers.<Food>lambdaQuery()
                .in(Food::getId, foodIdList)
                .eq(Food::getIsDeleted, 0);
        List<Food> foodList = foodService.list(queryWrapper);

        if (!foodList.isEmpty()) {
            List<ShoppingCartFoodVO> shoppingCartFoodVOList = new ArrayList<>();
            // 计算总价和总数量
            for (ShoppingCart cart : cartList) {
                for (Food food : foodList) {
                    if (cart.getFoodId().equals(food.getId())) {
                        // 计算单价
                        double price = food.getPrice() * cart.getFoodNum();
                        // 计算总价
                        shoppingCartVO.setTotalPrice(shoppingCartVO.getTotalPrice() + price);
                        // 计算总数量
                        shoppingCartVO.setTotalCount(shoppingCartVO.getTotalCount() + cart.getFoodNum());
                        ShoppingCartFoodVO shoppingCartFoodVO = new ShoppingCartFoodVO();
                        shoppingCartFoodVO.setShoppingCartFoodId(cart.getId());
                        shoppingCartFoodVO.setFoodId(food.getId());
                        shoppingCartFoodVO.setName(food.getName());
                        shoppingCartFoodVO.setPrice(food.getPrice());
                        shoppingCartFoodVO.setIsNew(food.getIsNew());
                        shoppingCartFoodVO.setCreateTime(cart.getCreateTime());
                        shoppingCartFoodVO.setUpdateTime(cart.getUpdateTime());
                        shoppingCartFoodVO.setImg(food.getImg());
                        shoppingCartVO.getFoodList().add(shoppingCartFoodVO);
                    }

                }
            }
        }
        return shoppingCartVO;
    }
}



