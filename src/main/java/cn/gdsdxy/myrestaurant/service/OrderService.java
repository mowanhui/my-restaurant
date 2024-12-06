package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.Order;
import cn.gdsdxy.myrestaurant.entity.OrderFood;
import cn.gdsdxy.myrestaurant.pojo.PageDTO;
import cn.gdsdxy.myrestaurant.pojo.SearchOrder;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import cn.gdsdxy.myrestaurant.vo.OrderVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 订单业务接口
 */
public interface OrderService extends IService<Order> {
    void createOrder(String userId);
    void finishOrder(String orderId);
    void cancelOrder(String orderId);
    PageInfo<OrderVO> getOrderPage(SearchOrder searchOrder);
}
