package service;

import dao.UserDao;
import entity.User;

public class UserService {

    private UserDao dao = new UserDao();

    // 模拟登陆
    public User login(User user){
        return dao.login(user);
    }

    // 模拟注册
    public void regist(User user) {
        dao.regist(user);
    }
}