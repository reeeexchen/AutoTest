package top.rex.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/8 22:58
 * @Version: 1.0
 */
public class HttpClientDemo {
    @Test
    public void test1() throws IOException {
        // 存放结果
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);

    }
}
