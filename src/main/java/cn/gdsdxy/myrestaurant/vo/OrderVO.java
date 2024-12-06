package cn.gdsdxy.myrestaurant.vo;

import cn.gdsdxy.myrestaurant.entity.OrderFood;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单信息
 */
@Data
public class OrderVO {
    /**
     * 订单Id
     */
    private String id;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 下单人ID
     */
    private String userId;

    /**
     * 下单人用户名
     */
    private String userName;

    /**
     * 下单人电话
     */
    private String phone;


    /**
     * 下单人真实姓名
     */
    private String realName;

    /**
     * 订单状态：0-取消订单，1-确认订单，2-完成订单
     */
    private Integer orderState;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 订单菜品列表
     */
    private List<OrderFood> orderFoodList;
}
