package top.rex.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 0:10
 * @Version: 1.0
 */
@EnableScheduling
@SpringBootApplication
public class Chapter13Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter13Application.class, args);
    }
}
