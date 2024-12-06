package cn.gdsdxy.myrestaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

/**
 * 用户实体类
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId
    @Schema(title = "用户id",description = "用户id为空时表示新增用户，有值时表示更新用户")
    private String id;

    /**
     * 用户名
     */
    @Schema(title = "用户名",description = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 真实姓名
     */
    @Schema(title = "真实姓名",description = "真实姓名不能为空")
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    /**
     * 密码
     */
    @Schema(title = "密码",description = "默认密码为：123")
    private String password;

    /**
     * 角色：admin-管理员，user-普通用户
     */
    @Schema(title = "角色",description = "角色：admin-管理员，user-普通用户")
    private String role;

    /**
     * 手机号
     */
    @Schema(title = "手机号")
    private String phone;

    /**
     * 是否删除
     */
    @Schema(accessMode = READ_ONLY)
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(accessMode = READ_ONLY)
    private Date createTime;

    /**
     * 删除时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(accessMode = READ_ONLY)
    private Date updateTime;

    /**
     * 头像
     */
    @Schema(title = "头像")
    private String img;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}