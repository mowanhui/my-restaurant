package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.pojo.RegisterUser;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import cn.gdsdxy.myrestaurant.service.UserService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import cn.gdsdxy.myrestaurant.vo.LoginUserVO;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name="UserController",description = "用户管理控制器")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "保存用户信息",description = "新增或修改用户信息")
    @PostMapping("saveUser")
    public FwResult<Object> saveUser(@RequestBody @Validated User user){
        userService.saveUser(user);
        return FwResult.ok();
    }

    @Operation(summary = "删除用户",description = "删除用户")
    @PostMapping("deleteUser")
    public FwResult<Object> deleteUser(@RequestParam("userId") @NotBlank(message = "用户ID不能为空") String userId){
        userService.deleteUser(userId);
        return FwResult.ok();
    }


    @Operation(summary = "获取用户分页",description = "获取用户分页")
    @GetMapping("getUserPage")
    public FwResult<PageInfo<User>> getUserPage(SearchUser searchUser){
        return FwResult.ok(userService.getUserPage(searchUser));
    }
}
