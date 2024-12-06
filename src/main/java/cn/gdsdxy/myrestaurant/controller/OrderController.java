package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.entity.ShoppingCart;
import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.pojo.SearchOrder;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import cn.gdsdxy.myrestaurant.service.OrderService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import cn.gdsdxy.myrestaurant.vo.OrderVO;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="OrderController",description = "订单管理控制器")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "确认下单",description = "确认订单")
    @PostMapping("createOrder")
    public FwResult<Object> createOrder(@NotBlank(message = "用户id不能为空") @RequestParam("userId") String userId){
        orderService.createOrder(userId);
        return FwResult.ok();
    }

    @Operation(summary = "完成订单",description = "完成订单")
    @PostMapping("finishOrder")
    public FwResult<Object> finishOrder(@NotBlank(message = "订单id不能为空") @RequestParam("orderId") String orderId){
        orderService.finishOrder(orderId);
        return FwResult.ok();
    }

    @Operation(summary = "取消订单",description = "取消订单")
    @PostMapping("cancelOrder")
    public FwResult<Object> cancelOrder(@NotBlank(message = "订单id不能为空") @RequestParam("orderId") String orderId){
        orderService.cancelOrder(orderId);
        return FwResult.ok();
    }

    @Operation(summary = "获取订单分页",description = "获取用户分页")
    @GetMapping("getOrderPage")
    public FwResult<PageInfo<OrderVO>> getOrderPage(SearchOrder searchOrder){
        return FwResult.ok(orderService.getOrderPage(searchOrder));
    }

}
