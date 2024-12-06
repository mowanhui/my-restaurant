package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_home_img
 */
@TableName(value ="t_home_img")
public class HomeImg implements Serializable {
    /**
     * 唯一id
     */
    @TableId
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String id;

    /**
     * 图片
     */
    @Schema(title = "图片")
    @NotBlank(message = "图片不能为空")
    private String img;

    /**
     * 排序
     */
    @Schema(title = "排序")
    @NotNull(message = "排序不能为空")
    private Integer sort;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date createTime;

    /**
     * 是否删除
     */
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    public String getId() {
        return id;
    }

    /**
     * 唯一id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 图片
     */
    public String getImg() {
        return img;
    }

    /**
     * 图片
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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