package cn.gdsdxy.myrestaurant.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SearchFood extends PageDTO{
    @Schema(title = "搜索关键字", description = "搜索关键字,包括：食品类型名称")
    private String keyword;
    @Schema(title = "食品类型id", description = "食品类型id")
    private String foodTypeId;
}
