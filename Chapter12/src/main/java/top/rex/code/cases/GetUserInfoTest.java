package top.rex.code.cases;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import top.rex.code.config.TestConfig;
import top.rex.code.model.CaseGetUserInfo;
import top.rex.code.model.User;
import top.rex.code.utils.DatabaseUtil;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:14
 * @Version: 1.0
 */
public class GetUserInfoTest {
    @Test(dependsOnGroups = "loginTrue", description = "获取用户信息")
    public void getUserInfoCase() {
        SqlSession session = DatabaseUtil.getSqlSession();
        CaseGetUserInfo caseGetUserInfo = session.selectOne("caseGetUserInfo", 1);
        System.out.println(caseGetUserInfo.toString());
        System.out.println(TestConfig.userGetDetailUrl);
        // 获取结果
        JSONArray resultJson = getJsonResult(caseGetUserInfo);
        // 验证结果
        User user = session.selectOne(caseGetUserInfo.getExpected(), caseGetUserInfo);
        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
        JSONArray jsonArray1 = new JSONArray(resultJson.getString(0));
        Assert.assertEquals(jsonArray.toString(), jsonArray1.toString());
    }

    private JSONArray getJsonResult(CaseGetUserInfo caseGetUserInfo) {
        HttpPost post = new HttpPost(TestConfig.userGetDetailUrl);
        JSONObject param = new JSONObject();
        param.put("id", caseGetUserInfo.getUserId());
        post.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "UTF-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result = "";
        HttpResponse response;
        try {
            response = TestConfig.defaultHttpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List resultList = Arrays.asList(result);
        return new JSONArray(resultList);
    }

}
