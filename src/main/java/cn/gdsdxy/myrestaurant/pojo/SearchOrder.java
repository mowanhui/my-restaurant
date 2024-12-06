package cn.gdsdxy.myrestaurant.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 搜索订单参数
 */
@Data
public class SearchOrder extends PageDTO{
    @Schema(title = "搜索关键字", description = "搜索关键字,包括：用户名、真实姓名、手机号")
    private String keyword;
    @Schema(title = "订单状态：0-取消订单，1-确认订单，2-完成订单")
    private String orderState;
    @Schema(title = "用户ID")
    private String userId;
}
