package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * token实体类
 * @TableName t_token
 */
@TableName(value ="t_token")
@Data
public class Token implements Serializable {
    /**
     * 唯一ID
     */
    @TableId
    private String id;

    /**
     * token值
     */
    private String token;

    /**
     * 用户ID
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
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}