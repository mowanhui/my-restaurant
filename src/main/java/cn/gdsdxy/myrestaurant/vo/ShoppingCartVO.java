package cn.gdsdxy.myrestaurant.vo;

import cn.gdsdxy.myrestaurant.entity.Food;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartVO {
    @Schema(description = "购物车总价")
    private double totalPrice;
    @Schema(description = "购物车总数量")
    private int totalCount;
    @Schema(description = "购物车列表")
    private List<ShoppingCartFoodVO> foodList;
}
