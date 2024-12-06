package cn.gdsdxy.myrestaurant.mapper;

import cn.gdsdxy.myrestaurant.entity.Order;
import cn.gdsdxy.myrestaurant.pojo.SearchOrder;
import cn.gdsdxy.myrestaurant.vo.OrderVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 19714
* @description 针对表【t_order】的数据库操作Mapper
* @createDate 2024-11-30 13:13:45
* @Entity cn.gdsdxy.myrestaurant.entity.Order
*/
public interface OrderMapper extends BaseMapper<Order> {
    List<OrderVO> getOrderList(@Param("search") SearchOrder searchOrder);
}




