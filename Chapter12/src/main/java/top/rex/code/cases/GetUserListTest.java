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
import top.rex.code.model.CaseGetUserList;
import top.rex.code.model.User;
import top.rex.code.utils.DatabaseUtil;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:14
 * @Version: 1.0
 */
public class GetUserListTest {
    @Test(dependsOnGroups = "loginTrue", description = "获取性别为男的用户列表")
    public void getListUserCase() {
        SqlSession session = DatabaseUtil.getSqlSession();
        CaseGetUserList caseGetUserList = session.selectOne("caseGetUserList", 1);
        System.out.println(caseGetUserList.toString());
        System.out.println(TestConfig.userGetListUrl);

        // 获取结果
        JSONArray resultJson = getJsonResult(caseGetUserList);
        // 验证
        List<User> userList = session.selectList(caseGetUserList.getExpected(), caseGetUserList);

        JSONArray userListJson = new JSONArray(userList);
        Assert.assertEquals(userListJson.length(), resultJson.length());
        for (int i = 0; i < resultJson.length(); i++) {
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(), actual.toString());
            System.out.println("expect - " + expect.toString());
            System.out.println("actual - " + actual.toString());
        }
    }

    private JSONArray getJsonResult(CaseGetUserList caseGetUserList) {
        HttpPost post = new HttpPost(TestConfig.userGetListUrl);
        JSONObject param = new JSONObject();
        param.put("username", caseGetUserList.getUsername());
        param.put("sex", caseGetUserList.getSex());
        param.put("age", caseGetUserList.getAge());
        post.setHeader("Content-Type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "UTF-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result = "";
        HttpResponse response = null;
        try {
            response = TestConfig.defaultHttpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }

}
