package action;

import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RegistAction
 * Created by Administrator on 2017/1/6.
 */
public class RegistAction {

    public Object register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Object uri = null;

        //1.获取请求数据，封装
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);

        //2.调用Service
        UserService userService = new UserService();
        userService.regist(user);

        //3.跳转
        uri = "registSuccess";

        return uri;
    }
}