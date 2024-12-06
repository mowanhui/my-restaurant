package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物车实体类
 * @TableName t_shopping_cart
 */
@Data
@TableName(value ="t_shopping_cart")
public class ShoppingCart implements Serializable {
    /**
     * 唯一ID
     */
    @TableId
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    /**
     * 食品ID
     */
    @Schema(title = "食品ID", description = "食品ID")
    @NotBlank(message = "食品ID不能为空")
    private String foodId;

    /**
     * 食品数量
     */
    @Schema(title = "食品数量", description = "食品数量")
    @NotNull(message = "食品数量不能为空")
    private Integer foodNum;

    /**
     * 用户id
     */
    @Schema(title = "用户id", description = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    /**
     * 是否删除
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}