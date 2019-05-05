package top.rex.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 21:12
 * @Version: 1.0
 */
public class ParameterTest {
    // 参数化测试
    // 配置化传参(parameterTest.xml)
    @Test
    @Parameters({"name","age"})
    public void parameterTest1(String name, int age) {
        System.out.println("name = " + name + ",age = " + age);
    }
}
