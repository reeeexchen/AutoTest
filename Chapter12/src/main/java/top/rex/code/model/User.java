package top.rex.code.model;

import lombok.Data;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 23:53
 * @Version: 1.0
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer isDelete;

    @Override
    public String toString() {
        return (
                "{id:" + id + "," +
                "username:" + username + "," +
                "password:" + password + "," +
                "age:" + age + "," +
                "sex:" + sex + "," +
                "permission:" + permission + "," +
                "isDelete:" + isDelete + "}"
        );
    }
}
