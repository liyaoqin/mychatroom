package cn.itcast.vo;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;
import java.util.Objects;

public class User implements HttpSessionBindingListener {
    private Integer uid;
    private String username;
    private String password;
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uid);
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("进入了...");
        HttpSession session=httpSessionBindingEvent.getSession();
        Map<User,HttpSession> userMap= (Map<User, HttpSession>) session.getServletContext().getAttribute("userMap");
        userMap.put(this,session);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("退出了....");
        HttpSession session = httpSessionBindingEvent.getSession();
        // 获得人员列表
        Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
                .getServletContext().getAttribute("userMap");
        // 将用户移除了
        userMap.remove(this);
    }
}
