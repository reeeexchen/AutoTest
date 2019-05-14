package top.rex.code.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:12
 * @Version: 1.0
 */
public class DatabaseUtil {
    public static SqlSession getSqlSession() {
        // 获取配置的资源文件
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("databaseConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        return factory.openSession();
    }
}
