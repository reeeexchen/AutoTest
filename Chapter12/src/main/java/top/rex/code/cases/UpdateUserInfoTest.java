package top.rex.code.cases;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import top.rex.code.config.TestConfig;
import top.rex.code.model.CaseGetUserInfo;
import top.rex.code.model.CaseUpdateUser;
import top.rex.code.model.User;
import top.rex.code.utils.DatabaseUtil;

import javax.xml.crypto.Data;
import java.io.IOException;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:14
 * @Version: 1.0
 */
public class UpdateUserInfoTest {

    private int getResult(CaseUpdateUser caseUpdateUser) {
        HttpPost post = new HttpPost(TestConfig.userUpdateUrl);
        JSONObject param = new JSONObject();
        param.put("id", caseUpdateUser.getUserId());
        param.put("username", caseUpdateUser.getUsername());
        param.put("sex", caseUpdateUser.getSex());
        param.put("age", caseUpdateUser.getAge());
        param.put("permission", caseUpdateUser.getPermission());
        param.put("isDelete", caseUpdateUser.getIsDelete());
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(param.toString(), "UTF-8"));
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result = "";
        HttpResponse response;
        try {
            response = TestConfig.defaultHttpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(result);
    }

    @Test(dependsOnGroups = "loginTrue", description = "修改用户信息")
    public void updateUserInfoCase() throws InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        CaseUpdateUser caseUpdateUser = session.selectOne("caseUpdateUser", 1);
        session.close();
        System.out.println(caseUpdateUser.toString());
        System.out.println(TestConfig.userGetDetailUrl);
        // 获取结果
        int result = getResult(caseUpdateUser);
        // 验证修改
        session = DatabaseUtil.getSqlSession();
        User user = session.selectOne(caseUpdateUser.getExpected(), caseUpdateUser);
        session.close();
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }


    @Test(dependsOnGroups = "loginTrue", description = "删除用户信息")
    public void deleteUserInfoCase() throws InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        CaseUpdateUser caseDeleteUser = session.selectOne("caseUpdateUser", 2);
        session.close();
        System.out.println(caseDeleteUser.toString());
        System.out.println(TestConfig.userGetDetailUrl);

        int result = getResult(caseDeleteUser);
        session = DatabaseUtil.getSqlSession();
        User user = session.selectOne(caseDeleteUser.getExpected(), caseDeleteUser);
        session.close();
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);
    }
}
