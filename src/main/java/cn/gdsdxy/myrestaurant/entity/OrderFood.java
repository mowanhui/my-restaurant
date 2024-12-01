package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单食品关联实体类
 * @TableName t_order_food
 */
@TableName(value ="t_order_food")
@Data
public class OrderFood implements Serializable {
    /**
     * 唯一ID
     */
    @TableId
    private String id;

    /**
     * 食品ID
     */
    private String foodId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 单价
     */
    private Double price;

    /**
     * 食品数量
     */
    private Integer foodNum;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}