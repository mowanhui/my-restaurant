package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_food_type_relation
 */
@TableName(value ="t_food_type_relation")
public class FoodTypeRelation implements Serializable {
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
     * 食品类型ID
     */
    private String foodTypeId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    public String getId() {
        return id;
    }

    /**
     * 唯一ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 食品ID
     */
    public String getFoodId() {
        return foodId;
    }

    /**
     * 食品ID
     */
    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    /**
     * 食品类型ID
     */
    public String getFoodTypeId() {
        return foodTypeId;
    }

    /**
     * 食品类型ID
     */
    public void setFoodTypeId(String foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 是否删除
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 是否删除
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}