package cn.gdsdxy.myrestaurant.controller;

import cn.gdsdxy.myrestaurant.util.FwResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

@Tag(name = "FileController", description = "文件控制器")
@RestController
public class FileController {

    @Operation(summary = "上传图片",description = "访问图片的链接：http://localhost:8080/images/{img}")
    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FwResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // 获取文件名以及后缀名
        String fileName = file.getOriginalFilename();
        // 重新生成文件名（根据具体情况生成对应文件名）
        fileName = UUID.randomUUID() + "_" + fileName;//fileName=d684f389-5247-41ed-a2fe-2224912b087e_1.jpg
        // 获取文件上传文件的永久存储路径
        String dirPath= System.getProperty("user.dir") + "/src/main/resources/static/images/";
        File filePath = new File(dirPath);

        // 获取文件上传文件的临时(编译)存储路径
        String targetDirPath= ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/static/images";
        File targetFilePath = new File(targetDirPath);
        if (!filePath.exists()) { //判断文件夹是否存在
            filePath.mkdirs();  //创建文件夹
        }
        if (!targetFilePath.exists()) { //判断文件夹是否存在
            targetFilePath.mkdirs();  //创建文件夹
        }
        try {
            File currentFile = new File(dirPath + fileName);
            file.transferTo(currentFile);
            FileCopyUtils.copy(currentFile,new File(targetDirPath, fileName));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传文件失败");
        }
        // 将上传的文件名传递给前端页面
        return FwResult.ok(fileName);
    }
}
