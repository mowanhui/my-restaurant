package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
    private String id;

    /**
     * 食品类型名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;

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