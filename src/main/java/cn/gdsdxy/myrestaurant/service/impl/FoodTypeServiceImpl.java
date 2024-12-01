package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.FoodType;
import cn.gdsdxy.myrestaurant.service.FoodTypeService;
import cn.gdsdxy.myrestaurant.mapper.FoodTypeMapper;
import org.springframework.stereotype.Service;

/**
 * 食品类型业务实现类
 */
@Service
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeMapper, FoodType>
    implements FoodTypeService{

}




