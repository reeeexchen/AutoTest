package top.rex.testng.groups;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 20:55
 * @Version: 1.0
 */
@Test(groups = "stu")
public class GroupsOnClass2 {
    public void stu1() {
        System.out.println("GroupsOnClass2 Stu1()");
    }

    public void stu2() {
        System.out.println("GroupsOnClass2 Stu2()");
    }
}
