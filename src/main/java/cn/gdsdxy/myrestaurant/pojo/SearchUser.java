package cn.gdsdxy.myrestaurant.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SearchUser extends PageDTO{
    @Schema(title = "搜索关键字", description = "搜索关键字,包括：用户名、真实姓名、手机号")
    private String keyword;
    @Schema(title = "角色", description = "角色,包括：admin-管理员、user-普通用户")
    private String role;
}
