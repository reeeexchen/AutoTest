package top.rex.code.model;

import lombok.Data;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 23:57
 * @Version: 1.0
 */
@Data
public class CaseAddUser {
    private String username;
    private String password;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer isDelete;
    private String expected;

    @Override
    public String toString() {
        return (
                "{" +
                        "username:" + username + "," +
                        "password:" + password + "," +
                        "age:" + age + "," +
                        "sex:" + sex + "," +
                        "permission:" + permission + "," +
                        "isDelete:" + isDelete +
                        "}"
        );
    }
}
