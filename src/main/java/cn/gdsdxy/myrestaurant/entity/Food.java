package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private Double price;

    /**
     * 是否是新品
     */
    private Integer isNew;

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

    /**
     * 照片
     */
    private byte[] img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}