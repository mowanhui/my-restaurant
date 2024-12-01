package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.service.FoodService;
import cn.gdsdxy.myrestaurant.mapper.FoodMapper;
import org.springframework.stereotype.Service;

/**
 * 食品业务实现类
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food>
    implements FoodService{

}




