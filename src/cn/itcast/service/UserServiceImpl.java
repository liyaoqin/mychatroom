package cn.itcast.service;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.UserDaoImpl;
import cn.itcast.vo.User;

public class UserServiceImpl implements UserService{
    @Override
    public User login(User user) {
        UserDao userDao=new UserDaoImpl();
        User u=userDao.login(user);
        return u;
    }
}
