package cn.gdsdxy.myrestaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.HomeImg;
import cn.gdsdxy.myrestaurant.service.HomeImgService;
import cn.gdsdxy.myrestaurant.mapper.HomeImgMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 莫万辉
 * @description 针对表【t_home_img】的数据库操作Service实现
 * @createDate 2024-12-01 21:52:00
 */
@Service
public class HomeImgServiceImpl extends ServiceImpl<HomeImgMapper, HomeImg>
        implements HomeImgService {

    /**
     * 添加图片
     *
     * @param homeImg
     */
    @Override
    public void addImg(HomeImg homeImg) {
        homeImg.setId(null);
        homeImg.setCreateTime(new Date());
        this.save(homeImg);
    }

    /**
     * 删除图片
     *
     * @param id
     */
    @Override
    public void deleteImg(String id) {
        this.removeById(id);
    }

    @Override
    public List<HomeImg> getAllImg() {
        LambdaQueryWrapper<HomeImg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HomeImg::getIsDeleted, 0)
                .orderByDesc(HomeImg::getSort)
                .orderByDesc(HomeImg::getCreateTime);
        List<HomeImg> homeImgs = this.list(queryWrapper);
        return homeImgs;
    }
}




