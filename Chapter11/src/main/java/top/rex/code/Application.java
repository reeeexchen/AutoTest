package top.rex.code;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 0:10
 * @Version: 1.0
 */
@SpringBootApplication
@EnableScheduling
public class Application {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context = SpringApplication.run(Application.class, args);
    }

    @PreDestroy
    public void close() {
        Application.context.close();
    }
}
