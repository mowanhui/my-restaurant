package cn.gdsdxy.myrestaurant.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SearchFoodType extends PageDTO{
    @Schema(title = "搜索关键字", description = "搜索关键字,包括：食品类型名称")
    private String keyword;
}
