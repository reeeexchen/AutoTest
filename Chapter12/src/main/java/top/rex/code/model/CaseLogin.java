package top.rex.code.model;

import lombok.Data;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/11 23:57
 * @Version: 1.0
 */
@Data
public class CaseLogin {
    private Integer id;
    private String username;
    private String password;
    private String expected;
}
