package top.rex.code.utils;

import top.rex.code.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description:
 * @Author: Rex Chan
 * @Date: 2019/5/12 0:07
 * @Version: 1.0
 */
public class ConfigFileUtil {
    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceName name) {
        String address = bundle.getString("test.url");
        String uri = "";
        if (name == InterfaceName.GET_USER_LIST) {
            uri = bundle.getString("test.user.list.uri");
        } else if (name == InterfaceName.GET_USER_INFO) {
            uri = bundle.getString("test.user.info.uri");
        } else if (name == InterfaceName.LOGIN) {
            uri = bundle.getString("test.user.login.uri");
        } else if (name == InterfaceName.ADD_USER) {
            uri = bundle.getString("test.user.add.uri");
        } else if (name == InterfaceName.UPDATE_USER_INFO) {
            uri = bundle.getString("test.user.update.uri");
        }
        return address + uri;
    }
}
