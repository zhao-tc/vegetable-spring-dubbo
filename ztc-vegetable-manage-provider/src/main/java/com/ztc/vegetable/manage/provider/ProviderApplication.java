package com.ztc.vegetable.manage.provider;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ztc
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.ztc.vegetable.manage.db.mapper","com.ztc.vegetable.manage.provider.*.dao"})
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}

