package top.rex.testng.multiThread;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 21:39
 * @Version: 1.0
 */
public class MultiThreadOnXml {
    // 多线程测试(线程数，线程池)(XML方式实现)
    @Test
    public void test1() {
        System.out.printf("Thread ID : %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test2() {
        System.out.printf("Thread ID : %s%n", Thread.currentThread().getId());
    }

    @Test
    public void test3() {
        System.out.printf("Thread ID : %s%n", Thread.currentThread().getId());
    }
}
