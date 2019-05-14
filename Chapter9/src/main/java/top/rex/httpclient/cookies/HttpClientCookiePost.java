package top.rex.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/8 23:42
 * @Version: 1.0
 */
public class HttpClientCookiePost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String result;
        String uri = bundle.getString("uri.getCookies");
        HttpGet get = new HttpGet(this.url + uri);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);
        // 获取Cookies信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for (Cookie cookie : cookieList) {
            System.out.print(cookie.getName() + "=");
            System.out.println(cookie.getValue());
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostWithCookies() throws IOException {
        String uri = bundle.getString("uri.postCookies");
        // 声明Client对象，用于方法执行
        DefaultHttpClient client = new DefaultHttpClient();

        // 声明POST方法
        HttpPost post = new HttpPost(this.url + uri);

        // 添加参数(JSON)
        JSONObject param = new JSONObject();
        param.put("name", "rex");
        param.put("age", "18");

        // 设置请求头信息 Header
        post.setHeader("Content-Type", "application/json");

        // 将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "UTF-8");
        post.setEntity(entity);

        // 声明对象进行响应结果的存储
        String result;

        // 设置Cookies信息
        client.setCookieStore(this.store);

        // 执行POST方法
        HttpResponse response = client.execute(post);

        // 获取响应结果
        result = EntityUtils.toString(response.getEntity(), "UTF-8");
        System.out.println(result);

        // 处理结果、判断是否符合预期
        // 将返回的响应字符串转换为JSON
        JSONObject resultJson = new JSONObject(result);
        // 具体判断返回结果
        String expect = (String) resultJson.get("message");
        Object code = resultJson.get("top.rex.code");
        Assert.assertEquals("SUCCESS", expect);
        Assert.assertEquals(200, code);
    }
}
