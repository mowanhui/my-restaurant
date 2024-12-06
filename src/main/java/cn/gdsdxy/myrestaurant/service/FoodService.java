package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.pojo.SearchFood;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 食品业务接口
 */
public interface FoodService extends IService<Food> {
    void saveFood(Food food);
    void deleteFood(String foodId);
    PageInfo<Food> getFoodPage(SearchFood searchFood);
    List<Food> getFoodListByFoodTypeId(String foodTypeId);
}
