package top.rex.code.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:05
 * @Version: 1.0
 */
public class TestConfig {
    public static String userLoginUrl;
    public static String userUpdateUrl;
    public static String userAddUrl;
    public static String userGetListUrl;
    public static String userGetDetailUrl;
    // DefaultHttpClient & CookieStore
    public static DefaultHttpClient defaultHttpClient;
    public static CookieStore cookieStore;
}
