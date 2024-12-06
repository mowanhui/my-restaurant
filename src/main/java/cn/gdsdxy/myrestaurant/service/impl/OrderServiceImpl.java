package cn.gdsdxy.myrestaurant.service.impl;

import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.entity.OrderFood;
import cn.gdsdxy.myrestaurant.entity.ShoppingCart;
import cn.gdsdxy.myrestaurant.pojo.SearchOrder;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import cn.gdsdxy.myrestaurant.service.FoodService;
import cn.gdsdxy.myrestaurant.service.OrderFoodService;
import cn.gdsdxy.myrestaurant.service.ShoppingCartService;
import cn.gdsdxy.myrestaurant.vo.OrderVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.Order;
import cn.gdsdxy.myrestaurant.service.OrderService;
import cn.gdsdxy.myrestaurant.mapper.OrderMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单业务实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderFoodService orderFoodService;

    /**
     * 创建订单（确认订单）
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(String userId) {
        //获取购物车的食品数据
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId)
                .eq(ShoppingCart::getIsDeleted, 0);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(queryWrapper);
        //删除购物车数据
        shoppingCartService.remove(queryWrapper);

        //生成订单id
        String orderId = String.valueOf(System.currentTimeMillis());
        //保存订单食品数据
        List<OrderFood> orderFoodList = new ArrayList<>();
        double amount = 0;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            Food food = foodService.getById(shoppingCart.getFoodId());
            OrderFood orderFood = new OrderFood();
            orderFood.setFoodId(shoppingCart.getFoodId());
            orderFood.setFoodNum(shoppingCart.getFoodNum());
            orderFood.setOrderId(orderId);
            orderFood.setPrice(food.getPrice());
            orderFood.setCreateTime(new Date());
            orderFood.setUpdateTime(new Date());
            orderFoodList.add(orderFood);
            amount += food.getPrice() * shoppingCart.getFoodNum();
        }
       //创建订单
        Order order = new Order();
        order.setId(orderId);
        order.setAmount(amount);
        order.setUserId(userId);
        //确认下单
        order.setOrderState(1);
        order.setIsDeleted(0);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        this.save(order);

        //保存订单食品数据
        orderFoodService.saveBatch(orderFoodList);
    }

    /**
     * 完成订单
     * @param orderId
     */
    @Override
    public void finishOrder(String orderId) {
        //更新订单状态
        Order order = this.getById(orderId);
        order.setOrderState(2);
        order.setUpdateTime(new Date());
        this.updateById(order);
    }

    /**
     * 取消订单
     * @param orderId
     */
    @Override
    public void cancelOrder(String orderId) {
        //更新订单状态
        Order order = this.getById(orderId);
        order.setOrderState(0);
        order.setUpdateTime(new Date());
        this.updateById(order);
    }

    @Override
    public PageInfo<OrderVO> getOrderPage(SearchOrder searchOrder) {
        PageHelper.startPage(searchOrder.getPageNum(), searchOrder.getPageSize());
        //分页查询订单数据
        List<OrderVO> orderVOList = this.baseMapper.getOrderList(searchOrder);
        //判断单位是否为空
        if(orderVOList!=null&&!orderVOList.isEmpty()){
            PageInfo<OrderVO> pageInfo = new PageInfo<>(orderVOList);
            for (OrderVO orderVO : pageInfo.getList()) {
                //获取订单食品数据
                List<OrderFood> orderFoodList = orderFoodService.list(new LambdaQueryWrapper<OrderFood>()
                       .eq(OrderFood::getOrderId, orderVO.getId())
                       .eq(OrderFood::getIsDeleted, 0));
                orderVO.setOrderFoodList(orderFoodList);
            }
            return pageInfo;
        }
        return new PageInfo<>();
    }
}




