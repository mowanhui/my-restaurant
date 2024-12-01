package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.OrderFood;
import cn.gdsdxy.myrestaurant.service.OrderFoodService;
import cn.gdsdxy.myrestaurant.mapper.OrderFoodMapper;
import org.springframework.stereotype.Service;

/**
 * 订单食品业务实现类
 */
@Service
public class OrderFoodServiceImpl extends ServiceImpl<OrderFoodMapper, OrderFood>
    implements OrderFoodService{

}




