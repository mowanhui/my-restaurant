package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.entity.FoodType;
import cn.gdsdxy.myrestaurant.pojo.SearchFoodType;
import cn.gdsdxy.myrestaurant.service.FoodTypeService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name="FoodTypeController",description = "食品类型管理控制器")
@RestController
public class FoodTypeController {
    @Autowired
    private FoodTypeService foodTypeService;


    @Operation(summary = "保存食品类型信息",description = "新增或修改食品类型信息")
    @PostMapping("saveFoodType")
    public FwResult<Object> saveFoodType(@RequestBody @Validated FoodType foodType){
        foodTypeService.saveFoodType(foodType);
        return FwResult.ok();
    }

    @Operation(summary = "删除食品类型",description = "删除食品类型")
    @PostMapping("deleteFoodType")
    public FwResult<Object> deleteFoodType(@RequestParam("foodTypeId") @NotBlank(message = "食品类型ID不能为空") String foodTypeId){
        foodTypeService.deleteFoodType(foodTypeId);
        return FwResult.ok();
    }


    @Operation(summary = "获取食品类型分页",description = "获取食品类型分页")
    @GetMapping("getFoodTypePage")
    public FwResult<Object> getFoodTypePage(SearchFoodType searchFoodType){
        return FwResult.ok(foodTypeService.getFoodTypePage(searchFoodType));
    }

    @Operation(summary = "获取食品类型列表",description = "获取食品类型分页")
    @GetMapping("getFoodTypeList")
    public FwResult<Object> getFoodTypeList(SearchFoodType searchFoodType){
        return FwResult.ok(foodTypeService.getFoodTypeList(searchFoodType));
    }
}
