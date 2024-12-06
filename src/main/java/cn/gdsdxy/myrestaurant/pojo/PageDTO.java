package cn.gdsdxy.myrestaurant.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PageDTO {
    @Schema(description = "页码")
    private int pageNum;
    @Schema(description = "每页数量")
    private int pageSize;

    public int getPageNum() {
        return pageNum==0?1:pageNum;
    }

    public int getPageSize() {
        return pageSize==0?10:pageSize;
    }
}
