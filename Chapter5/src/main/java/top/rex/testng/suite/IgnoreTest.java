package top.rex.testng.suite;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/4/28 23:39
 * @Version: 1.0
 */
public class IgnoreTest {
    @Test
    public void ignore1(){
        System.out.println("1执行");
    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.println("2执行");
    }

    @Test(enabled = true)
    public void ignore3(){
        System.out.println("3执行");
    }
}
