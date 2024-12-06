package cn.gdsdxy.myrestaurant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan({"cn.gdsdxy.myrestaurant.**.mapper"})//扫描mapper包
@SpringBootApplication
public class MyRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRestaurantApplication.class, args);
    }

}
