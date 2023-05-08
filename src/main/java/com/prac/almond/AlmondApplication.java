package com.prac.almond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*@EnableAspectJAutoProxy*/
@SpringBootApplication
public class AlmondApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlmondApplication.class, args);

    }

}
