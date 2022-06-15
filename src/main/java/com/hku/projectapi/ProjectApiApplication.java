package com.hku.projectapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hku.projectapi.Mapper")
public class ProjectApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApiApplication.class, args);
    }

}
