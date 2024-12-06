package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.entity.FoodType;
import cn.gdsdxy.myrestaurant.entity.FoodTypeRelation;
import cn.gdsdxy.myrestaurant.pojo.SearchFood;
import cn.gdsdxy.myrestaurant.service.FoodService;
import cn.gdsdxy.myrestaurant.service.FoodTypeRelationService;
import cn.gdsdxy.myrestaurant.service.FoodTypeService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="FoodController",description = "食品管理控制器")
@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;


    @Transactional(rollbackFor = Exception.class)// 事务注解
    @Operation(summary = "保存食品信息",description = "新增或修改食品信息")
    @PostMapping("saveFood")
    public FwResult<Object> saveFood(@RequestBody @Validated Food food){
        foodService.saveFood(food);
        return FwResult.ok();
    }

    @Operation(summary = "删除食品",description = "删除食品")
    @PostMapping("deleteFood")
    public FwResult<Object> deleteFood(@RequestParam("foodId") @NotBlank(message = "食品ID不能为空") String foodId){
        foodService.deleteFood(foodId);
        return FwResult.ok();
    }


    @Operation(summary = "获取食品分页",description = "获取食品分页")
    @GetMapping("getFoodPage")
    public FwResult<PageInfo<Food>> getFoodPage(SearchFood searchFood){
        return FwResult.ok(foodService.getFoodPage(searchFood));
    }

    @Operation(summary = "根据食品类型id获取食品列表",description = "根据食品类型id获取食品列表")
    @GetMapping("getFoodListByFoodTypeId")
    public FwResult<List<Food>> getFoodListByFoodTypeId(@RequestParam("foodTypeId") @NotBlank(message = "食品类型ID不能为空") String foodTypeId){
        return FwResult.ok(foodService.getFoodListByFoodTypeId(foodTypeId));
    }
}
