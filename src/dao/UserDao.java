package dao;

import entity.User;

public class UserDao {

    //模拟登陆
    public User login(User user){
        if("admin".equals(user.getName()) &&
                "admin".equals(user.getPwd())){
            return user;
        }
        return null;
    }

    //模拟注册
    public void regist(User user){
        System.out.println("注册成功，欢迎您 " + user.getName());
    }
}