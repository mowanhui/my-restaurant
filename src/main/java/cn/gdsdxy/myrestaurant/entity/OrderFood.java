package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}