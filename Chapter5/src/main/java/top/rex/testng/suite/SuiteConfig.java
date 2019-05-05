package top.rex.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/4/28 23:29
 * @Version: 1.0
 */
public class SuiteConfig {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuit Running");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite Running");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("BeforeTest Running");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("AfterTest Running");
    }
}
