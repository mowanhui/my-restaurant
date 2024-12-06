package cn.gdsdxy.myrestaurant.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class ShoppingCartFoodVO {
    @Schema(title = "购物车食品ID", description = "购物车食品ID")
    private String shoppingCartFoodId;
    /**
     * 唯一ID
     */
    @Schema(title = "食品ID", description = "食品ID")
    private String foodId;

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
}
