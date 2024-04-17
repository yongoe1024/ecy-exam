package com.yongoe.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.yongoe.exam"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  exam 启动成功  牛皮！（压声）   \n");
    }

}
