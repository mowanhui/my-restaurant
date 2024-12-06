package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.entity.ShoppingCart;
import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.service.ShoppingCartService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import cn.gdsdxy.myrestaurant.vo.ShoppingCartVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ShoppingCartController", description = "购物车控制器")
@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Operation(summary = "加入购物车",description = "加入购物车,数量添加也用这个接口")
    @PostMapping("saveFoodToCart")
    public FwResult<Object> saveFoodToCart(@RequestBody @Validated ShoppingCart shoppingCart){
        shoppingCartService.saveFoodToCart(shoppingCart);
        return FwResult.ok();
    }

    @Operation(summary = "移除购物车中的食物",description = "移除购物车中的食物")
    @PostMapping("delFoodToCart")
    public FwResult<Object> delFoodToCart(@NotBlank(message = "shoppingCartFoodId不能为空") @RequestParam("shoppingCartFoodId") String shoppingCartFoodId){
        shoppingCartService.delFoodToCart(shoppingCartFoodId);
        return FwResult.ok();
    }

    @Operation(summary = "获取购物车信息",description = "获取购物车信息")
    @GetMapping("getFoodOfCartList")
    public FwResult<ShoppingCartVO> getFoodOfCartList(@RequestParam("userId") @NotBlank(message = "userId不能为空") String userId){
        return FwResult.ok(shoppingCartService.getFoodOfCartList(userId));
    }
}
