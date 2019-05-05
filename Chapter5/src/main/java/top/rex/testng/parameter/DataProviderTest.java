package top.rex.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 21:18
 * @Version: 1.0
 */
public class DataProviderTest {
    // 参数化测试 DataProvider方式传参
    @Test(dataProvider = "data")
    public void testDataProvider(String name, int age) {
        System.out.println("name = " + name + ",age = " + age);
    }

    @DataProvider(name = "data")
    public Object[][] providerData() {
        return new Object[][]{
                {"REX", 21},
                {"CHEN", 22},
                {"CKB", 23}
        };
    }

    // 根据方法传参
    @Test(dataProvider = "methodData")
    public void test1(String name, int age) {
        System.out.println("test1(),name = " + name + ",age = " + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name, int age) {
        System.out.println("test2(),name = " + name + ",age = " + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] methodDataTest(Method method) {
        Object[][] result = null;
        if (method.getName().equals("test1")) {
            result = new Object[][]{
                    {"REX", 21},
                    {"CHEN", 22}
            };
        } else if (method.getName().equals("test2")) {
            result = new Object[][]{
                    {"REX", 25},
                    {"CHEN", 26}
            };
        }
        return result;
    }
}
