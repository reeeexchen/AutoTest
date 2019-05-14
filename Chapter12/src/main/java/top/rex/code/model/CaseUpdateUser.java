package top.rex.code.model;

import lombok.Data;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 23:57
 * @Version: 1.0
 */
@Data
public class CaseUpdateUser {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer isDelete;
    private String expected;
}
