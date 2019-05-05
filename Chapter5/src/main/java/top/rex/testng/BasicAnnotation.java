package top.rex.testng;

import org.testng.annotations.*;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/4/28 23:18
 * @Version: 1.0
 */
public class BasicAnnotation {
    // 可以包含多个Class的Suite的运行
    @BeforeSuite
    public void beforeSuite() {
        System.out.printf("Thread ID : %s%n", Thread.currentThread().getId());
        System.out.println("BeforeSuite");
    }

    // 类运行前
    @BeforeClass
    public void beforeClass() {
        System.out.printf("Thread ID : %s%n", Thread.currentThread().getId());
        System.out.println("BeforeClass Running");
    }

    // 每个方法运行前都运行
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod Running");
    }

    // 最基础的注解，用于方法标记为测试的一部分
    @Test
    public void testCase1() {
        System.out.println("Test1 Running");
    }

    @Test
    public void testCase2() {
        System.out.println("Test2 Running");
    }

    // 每个方法运行后都运行
    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod Running");
    }

    // 类运行后
    @AfterClass
    public void afterClass() {
        System.out.println("AfterClass Running");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }
}
