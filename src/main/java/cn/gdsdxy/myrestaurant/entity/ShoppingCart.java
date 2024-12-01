package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 购物车实体类
 * @TableName t_shopping_cart
 */
@TableName(value ="t_shopping_cart")
@Data
public class ShoppingCart implements Serializable {
    /**
     * 购物车ID
     */
    @TableId
    private String id;

    /**
     * 总额
     */
    private Double amount;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 删除时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}