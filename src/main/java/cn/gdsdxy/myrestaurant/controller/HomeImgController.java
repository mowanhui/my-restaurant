package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.entity.HomeImg;
import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import cn.gdsdxy.myrestaurant.service.HomeImgService;
import cn.gdsdxy.myrestaurant.util.FwResult;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="HomeImgController",description = "主页轮播图控制器")
@RestController
public class HomeImgController {
    private HomeImgService homeImgService;

    @Operation(summary = "添加图片",description = "添加图片")
    @PostMapping("/addImg")
    public FwResult<Object> addImg(@RequestBody @Validated HomeImg homeImg){
        homeImgService.addImg(homeImg);
        return FwResult.ok();
    }

    @Operation(summary = "删除图片",description = "删除图片")
    @PostMapping("/deleteImg")
    public FwResult<Object> deleteImg(@NotBlank(message = "图片不能为空") @RequestParam("id") String id){
        homeImgService.deleteImg(id);
        return FwResult.ok();
    }

    @Operation(summary = "获取所有轮播图",description = "获取所有轮播图")
    @GetMapping("getAllImg")
    public FwResult<List<HomeImg>> getAllImg(){
        return FwResult.ok(homeImgService.getAllImg());
    }
}
