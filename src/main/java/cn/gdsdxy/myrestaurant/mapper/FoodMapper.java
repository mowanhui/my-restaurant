package cn.gdsdxy.myrestaurant.mapper;

import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.pojo.SearchFood;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 食品映射类
 */
public interface FoodMapper extends BaseMapper<Food> {
    List<Food> getList(@Param("search") SearchFood searchFood);
}




