package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.Order;
import cn.gdsdxy.myrestaurant.service.OrderService;
import cn.gdsdxy.myrestaurant.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * 订单业务实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




