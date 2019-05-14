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
import top.rex.code.model.CaseAddUser;
import top.rex.code.model.User;
import top.rex.code.utils.DatabaseUtil;

import java.io.IOException;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:14
 * @Version: 1.0
 */
public class AddUserTest {
    @Test(dependsOnGroups = "loginTrue", description = "添加用户信息")
    public void addUserCase() throws InterruptedException {
        SqlSession session = DatabaseUtil.getSqlSession();
        CaseAddUser caseAddUser = session.selectOne("caseAddUser", 1);
        session.close();
        System.out.println(caseAddUser);
        System.out.println(TestConfig.userAddUrl);
        // 发请求，获取结果
        String result = getResult(caseAddUser);
        // 验证结果
        session = DatabaseUtil.getSqlSession();
        User user = session.selectOne("addUser",caseAddUser);
        session.close();
        System.out.println(user.toString());
        Assert.assertEquals(caseAddUser.getExpected(),result);
    }

    private String getResult(CaseAddUser caseAddUser) {
        HttpPost post = new HttpPost(TestConfig.userAddUrl);
        JSONObject param = new JSONObject();
        param.put("username",caseAddUser.getUsername());
        param.put("password",caseAddUser.getPassword());
        param.put("age",caseAddUser.getAge());
        param.put("sex",caseAddUser.getSex());
        param.put("permission",caseAddUser.getPermission());
        param.put("isDelete",caseAddUser.getIsDelete());
        // 设置头信息
        post.setHeader("Content-Type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"UTF-8");
        post.setEntity(entity);
        // 设置Cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        // 返回结果
        String result = null;
        try {
            HttpResponse response = TestConfig.defaultHttpClient.execute(post);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }

}
