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

/**
 * 食品实体类
 * @TableName t_food
 */
@TableName(value ="t_food")
@Data
public class Food implements Serializable {
    /**
     * 唯一ID
     */
    @TableId
    @Schema(title = "食品ID", description = "如果食品ID为空，则新增，否则为更新")
    private String id;

    /**
     * 名称
     */
    @Schema(title = "食品名称", description = "食品名称")
    @NotBlank(message = "食品名称不能为空")
    private String name;

    /**
     * 价格
     */
    @Schema(title = "食品价格", description = "食品价格")
    @NotNull(message = "食品价格不能为空")
    private Double price;

    /**
     * 是否是新品
     */
    @Schema(title = "是否是新品", description = "是否是新品")
    @NotNull(message = "是否是新品不能为空")
    private Integer isNew;

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

    /**
     * 照片
     */
    @Schema(title = "食品照片", description = "食品照片")
    private String img;

    @TableField(exist = false)
    @Schema(title = "食品类型Id", description = "多个使用英文逗号隔开")
    private String foodTypeId;

    @TableField(exist = false)
    @Schema(title = "食品类型", description = "该字段仅用于前端展示，无实际作用，可忽略")
    private String foodTypeName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}