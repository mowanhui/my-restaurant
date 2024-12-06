package cn.gdsdxy.myrestaurant.service;

import cn.gdsdxy.myrestaurant.entity.HomeImg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 莫万辉
* @description 针对表【t_home_img】的数据库操作Service
* @createDate 2024-12-01 21:52:00
*/
public interface HomeImgService extends IService<HomeImg> {
    void addImg(HomeImg homeImg);
    void deleteImg(String id);
    List<HomeImg> getAllImg();
}
