package top.rex.code.model;

import lombok.Data;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/9 22:59
 * @Version: 1.0
 */
@Data
public class User {
    private String username;
    private String password;
    private String nickname;
    private String sex;
    private Integer age;
}
