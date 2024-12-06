package cn.gdsdxy.myrestaurant.service.impl;

import cn.gdsdxy.myrestaurant.entity.*;
import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.pojo.SearchFood;
import cn.gdsdxy.myrestaurant.pojo.SearchFood;
import cn.gdsdxy.myrestaurant.service.FoodTypeRelationService;
import cn.gdsdxy.myrestaurant.service.FoodTypeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gdsdxy.myrestaurant.entity.Food;
import cn.gdsdxy.myrestaurant.service.FoodService;
import cn.gdsdxy.myrestaurant.mapper.FoodMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 食品业务实现类
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food>
    implements FoodService{
    @Autowired
    private FoodTypeService foodTypeService;
    @Autowired
    private FoodTypeRelationService foodTypeRelationService;
    @Override
    public void saveFood(Food food) {
        // 保存食品信息
        if(StringUtils.isBlank(food.getId())){
            food.setCreateTime(new Date());
            food.setUpdateTime(new Date());
            this.save(food);
            //保存食品类型
            if (StringUtils.isNotBlank(food.getFoodTypeId())){
                String[] foodTypes = food.getFoodTypeId().split(",");
                LambdaQueryWrapper<FoodType> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(FoodType::getIsDeleted,0)
                        .in(FoodType::getId, Arrays.asList(foodTypes));
                // 查询食品类型
                List<FoodType> foodTypesList=foodTypeService.list(queryWrapper);
                List<FoodTypeRelation> foodTypeRelations=new ArrayList<>();
                for (FoodType foodType : foodTypesList) {
                    FoodTypeRelation foodTypeRelation=new FoodTypeRelation();
                    foodTypeRelation.setFoodId(food.getId());
                    foodTypeRelation.setFoodTypeId(foodType.getId());
                    foodTypeRelation.setCreateTime(new Date());
                }
                // 保存食品类型关联数据
                foodTypeRelationService.saveBatch(foodTypeRelations);
            }
        }else {
            food.setUpdateTime(new Date());
            this.updateById(food);

            if (StringUtils.isNotBlank(food.getFoodTypeId())){
                String[] foodTypes = food.getFoodTypeId().split(",");
                // 删除原有食品类型关联数据
                LambdaQueryWrapper<FoodTypeRelation> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(FoodTypeRelation::getFoodId,food.getId())
                        .eq(FoodTypeRelation::getIsDeleted,0);
                foodTypeRelationService.remove(queryWrapper);
                // 保存新食品类型关联数据
                List<FoodTypeRelation> foodTypeRelations=new ArrayList<>();
                for (String foodType : foodTypes) {
                    FoodTypeRelation foodTypeRelation=new FoodTypeRelation();
                    foodTypeRelation.setFoodId(food.getId());
                    foodTypeRelation.setFoodTypeId(foodType);
                    foodTypeRelation.setCreateTime(new Date());
                    foodTypeRelations.add(foodTypeRelation);
                }
                foodTypeRelationService.saveBatch(foodTypeRelations);
            }
        }
    }

    @Override
    public void deleteFood(String foodId) {
        this.removeById(foodId);
    }

    @Override
    public PageInfo<Food> getFoodPage(SearchFood searchFood) {
        PageHelper.startPage(searchFood.getPageNum(), searchFood.getPageSize());
        List<Food> foodList=this.baseMapper.getList(searchFood);
        // 调用分页方法
        PageInfo<Food> pageInfo = new PageInfo<>(foodList);
        return pageInfo;
    }

    /**
     * 根据食品类型ID查询食品列表
     * @param foodTypeId
     * @return
     */
    @Override
    public List<Food> getFoodListByFoodTypeId(String foodTypeId) {
        //如果食品类型ID为空，则返回空列表
        if (StringUtils.isBlank(foodTypeId)){
            return new ArrayList<>();
        }
        //查询食品列表
        LambdaQueryWrapper<FoodTypeRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FoodTypeRelation::getIsDeleted,0)
                .eq(FoodTypeRelation::getFoodTypeId,foodTypeId);
        List<FoodTypeRelation> foodTypeRelationList=foodTypeRelationService.list(queryWrapper);
        if(foodTypeRelationList!=null){
            List<String> foodIdList=new ArrayList<>();
            for (FoodTypeRelation foodTypeRelation : foodTypeRelationList) {
                foodIdList.add(foodTypeRelation.getFoodId());
            }
            LambdaQueryWrapper<Food> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Food::getIsDeleted,0)
                   .in(Food::getId,foodIdList);
            List<Food> foodList=this.list(queryWrapper1);
            return foodList;
        }
        return new ArrayList<>();
    }
}




