package top.rex.code.cases;

import com.mongodb.util.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import top.rex.code.config.TestConfig;
import top.rex.code.model.CaseLogin;
import top.rex.code.model.InterfaceName;
import top.rex.code.utils.ConfigFileUtil;
import top.rex.code.utils.DatabaseUtil;

import java.io.IOException;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:14
 * @Version: 1.0
 */
public class LoginTest {
    @BeforeTest(groups = "loginTrue", description = "测试准备，获取HttpClient等")
    public void beforeTest() {
        TestConfig.userAddUrl = ConfigFileUtil.getUrl(InterfaceName.ADD_USER);
        TestConfig.userGetDetailUrl = ConfigFileUtil.getUrl(InterfaceName.GET_USER_INFO);
        TestConfig.userGetListUrl = ConfigFileUtil.getUrl(InterfaceName.GET_USER_LIST);
        TestConfig.userLoginUrl = ConfigFileUtil.getUrl(InterfaceName.LOGIN);
        TestConfig.userUpdateUrl = ConfigFileUtil.getUrl(InterfaceName.UPDATE_USER_INFO);

        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }

    private String getResult(CaseLogin caseLogin) {
        HttpPost post = new HttpPost(TestConfig.userLoginUrl);
        JSONObject param = new JSONObject();
        param.put("username",caseLogin.getUsername());
        param.put("password",caseLogin.getPassword());
        // 设置头信息
        post.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"UTF-8");
        post.setEntity(entity);
        // 返回结果
        String result = null;
        try {
            HttpResponse response = TestConfig.defaultHttpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
            // 设置Cookies赋值
            TestConfig.cookieStore = TestConfig.defaultHttpClient.getCookieStore();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;

    }

    @Test(groups = "loginTrue", description = "用户登录成功接口测试")
    public void loginTrue() {
        SqlSession session = DatabaseUtil.getSqlSession();
        CaseLogin caseLogin = session.selectOne("caseUserLogin", 1);
        session.close();
        System.out.println(caseLogin);
        System.out.println(TestConfig.userLoginUrl);
        // 登录请求
        String result = getResult(caseLogin);
        // 验证登录结果
        Assert.assertEquals(caseLogin.getExpected(), result);
    }


    @Test(groups = "loginFalse", description = "用户登录失败接口测试")
    public void loginFalse() {
        SqlSession session = DatabaseUtil.getSqlSession();
        CaseLogin caseLogin = session.selectOne("caseUserLogin", 2);
        session.close();
        System.out.println(caseLogin);
        System.out.println(TestConfig.userLoginUrl);
        // 登录请求
        String result = getResult(caseLogin);
        // 验证登录结果
        Assert.assertEquals(caseLogin.getExpected(), result);
    }
}
