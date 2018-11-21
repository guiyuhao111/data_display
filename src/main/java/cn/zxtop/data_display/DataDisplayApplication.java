package cn.zxtop.data_display;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"cn.zxtop"})
@SpringBootApplication
@MapperScan("cn.zxtop.data_display.dao")
public class DataDisplayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataDisplayApplication.class, args);
    }
}
