package cn.itcast.dao;

import cn.itcast.utils.JdbcUtil;
import cn.itcast.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    @Override
    public User login(User user) {
        QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
        String sql="select * from user where username=? and password=?";
        User u=null;
        try {
            u=qr.query(sql,new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
