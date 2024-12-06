package cn.gdsdxy.myrestaurant.service.impl;

import cn.gdsdxy.myrestaurant.entity.User;
import cn.gdsdxy.myrestaurant.pojo.SearchFoodType;
import cn.gdsdxy.myrestaurant.pojo.SearchUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.FoodType;
import cn.gdsdxy.myrestaurant.service.FoodTypeService;
import cn.gdsdxy.myrestaurant.mapper.FoodTypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * 食品类型业务实现类
 */
@Service
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeMapper, FoodType>
        implements FoodTypeService {

    @Override
    public void saveFoodType(FoodType foodType) {
        // 判断食品类型id不为空，则为更新食品类型信息
        if (StringUtils.isNotBlank(foodType.getId())) {
            foodType.setUpdateTime(new Date());
            //更新食品类型信息
            this.updateById(foodType);
        } else {
            foodType.setCreateTime(new Date());
            foodType.setUpdateTime(new Date());
            // 保存食品类型信息
            this.save(foodType);
        }
    }

    @Override
    public void deleteFoodType(String foodTypeId) {
        this.removeById(foodTypeId);
    }

    @Override
    public PageInfo<FoodType> getFoodTypePage(SearchFoodType searchFoodType) {
        PageHelper.startPage(searchFoodType.getPageNum(), searchFoodType.getPageSize());
        LambdaQueryWrapper<FoodType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(searchFoodType.getKeyword()), FoodType::getName, searchFoodType.getKeyword())
                .eq(FoodType::getIsDeleted, 0)
                .orderByAsc(FoodType::getSort);
        // 调用分页方法
        PageInfo<FoodType> pageInfo = new PageInfo<>(this.list(queryWrapper));
        return pageInfo;
    }

    /**
     * 获取食品类型列表
     * @param searchFoodType
     * @return
     */
    @Override
    public List<FoodType> getFoodTypeList(SearchFoodType searchFoodType) {
        LambdaQueryWrapper<FoodType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(searchFoodType.getKeyword()), FoodType::getName, searchFoodType.getKeyword())
                .eq(FoodType::getIsDeleted, 0)
                .orderByAsc(FoodType::getSort);
        return this.list(queryWrapper);
    }
}




