package top.rex.testng.groups;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 20:55
 * @Version: 1.0
 */
@Test(groups = "teacher")
public class GroupsOnClass3 {
    public void teacher1() {
        System.out.println("GroupsOnClass3 Teacher1()");
    }

    public void teacher2() {
        System.out.println("GroupsOnClass3 Teacher2()");
    }
}
