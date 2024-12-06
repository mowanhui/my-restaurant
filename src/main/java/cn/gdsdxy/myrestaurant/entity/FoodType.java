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
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

/**
 * 食品类型实体类
 * @TableName t_food_type
 */
@TableName(value ="t_food_type")
@Data
public class FoodType implements Serializable {
    /**
     * 唯一ID
     */
    @TableId
    @Schema(title = "食品类型ID", description = "如果食品类型ID为空，则新增，否则为更新")
    private String id;

    /**
     * 食品类型名称
     */
    @Schema(title = "食品类型名称", description = "食品类型名称不能为空")
    @NotBlank(message = "食品类型名称不能为空")
    private String name;

    /**
     * 排序
     */
    @Schema(title = "排序", description = "排序不能为空")
    @NotNull(message = "排序不能为空")
    private Integer sort;

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