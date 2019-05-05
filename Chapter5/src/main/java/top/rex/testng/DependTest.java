package top.rex.testng;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 21:07
 * @Version: 1.0
 */
public class DependTest {
    @Test
    public void test1(){
        System.out.println("TEST1() RUN");
        throw new RuntimeException();
    }

    // 依赖测试,前置条件
    // 若失败,则忽略不执行
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("TEST2() RUN");
    }
}
