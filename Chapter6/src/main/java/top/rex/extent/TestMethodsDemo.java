package top.rex.extent;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 22:39
 * @Version: 1.0
 */
public class TestMethodsDemo {
    @Test
    public void test1() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void test2() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void test3() {
        Assert.assertEquals("aaa", "aaa");
    }

    @Test
    public void logDemo() {
        Reporter.log("日志测试");
        throw new RuntimeException("运行时异常");
    }
}
