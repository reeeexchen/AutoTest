package top.rex.testng;

import org.testng.annotations.Test;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/5 22:16
 * @Version: 1.0
 */
public class TimeOutTest {
    // 期望3000毫秒
    @Test(timeOut = 3000)
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeOut = 2000)
    public void testFailed() throws InterruptedException {
        Thread.sleep(3000);
    }
}
