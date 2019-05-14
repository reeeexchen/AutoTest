package top.rex.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/8 23:14
 * @Version: 1.0
 */
public class HttpClientCookiesGet {
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
    public void testGetWithCookies() throws IOException {
        String result;
        String uri = bundle.getString("uri.getWithCookies");
        HttpGet get = new HttpGet(this.url + uri);
        DefaultHttpClient client = new DefaultHttpClient();
        // 设置Cookies信息
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(get);
        // 获取响应状态码
        int status = response.getStatusLine().getStatusCode();
        System.out.println("status=" + status);
        if (status == 200) {
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result);
        }
    }
}
