package top.rex.testng.multiThread;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 21:39
 * @Version: 1.0
 */
public class MultiThreadOnAnnotation {
    // 多线程测试(线程数，线程池)(注解方式实现)
    @Test(invocationCount = 5,threadPoolSize = 3)
    public void test(){
        System.out.println("1");
        System.out.printf("Thread ID : %s%n",Thread.currentThread().getId());
    }
}
