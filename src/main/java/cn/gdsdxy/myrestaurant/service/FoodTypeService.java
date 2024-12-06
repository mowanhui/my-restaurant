package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.FoodType;
import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.pojo.SearchFoodType;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 食品类型业务接口
 */
public interface FoodTypeService extends IService<FoodType> {
    void saveFoodType(FoodType foodType);
    void deleteFoodType(String foodTypeId);
    PageInfo<FoodType> getFoodTypePage(SearchFoodType searchFoodType);
    List<FoodType> getFoodTypeList(SearchFoodType searchFoodType);
}
