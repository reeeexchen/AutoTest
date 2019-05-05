package top.rex.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/4/29 23:43
 * @Version: 1.0
 */
public class GourpsOnMethod {
    @Test(groups = "server")
    public void test1(){
        System.out.println("SERVER TEST111111");
    }

    @Test(groups = "server")
    public void test2(){
        System.out.println("SERVER TEST222222");
    }

    @Test(groups = "client")
    public void test3(){
        System.out.println("CLIENT TEST333333");
    }
    @Test(groups = "client")
    public void test4(){
        System.out.println("CLIENT TEST444444");
    }

    @BeforeGroups(groups = "server")
    public void beforeGroupsOnServer(){
        System.out.println("BEFORE GROUPS-SERVER");
    }

    @AfterGroups(groups = "server")
    public void afterGroupsOnServer(){
        System.out.println("AFTER GROUPS-SERVER");
    }
}
