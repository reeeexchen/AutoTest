package top.rex.testng;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 21:02
 * @Version: 1.0
 */
public class ExpectedException {
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("失败的异常测试");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("成功的异常测试");
        throw new RuntimeException();
    }
}
