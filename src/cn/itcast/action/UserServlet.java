package cn.itcast.action;

import cn.itcast.service.UserService;
import cn.itcast.service.UserServiceImpl;
import cn.itcast.utils.BaseServlet;
import cn.itcast.vo.User;
import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.Request;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    public String exit(HttpServletRequest req,HttpServletResponse res) throws IOException {
        HttpSession  session=req.getSession();
        session.invalidate();
        res.sendRedirect(req.getContextPath()+"/login.jsp");
        return null;
    }
    public String sendMessage(HttpServletRequest req,HttpServletResponse res) throws IOException {
        String from=req.getParameter("from");
        String face=req.getParameter("face");
        String to=req.getParameter("to");
        String color=req.getParameter("color");
        String content=req.getParameter("content");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String formatDate = sdf.format(date);
        ServletContext application=getServletContext();
        String sourceMessage= (String) application.getAttribute("message");
        sourceMessage+="<font color='blue'><strong>" + from
                + "</strong></font><font color='#CC0000'>" + face
                + "</font>对<font color='green'>[" + to + "]</font>说："
                + "<font color='" + color + "'>" + content + "</font>（"
                + formatDate + "）<br/>";
        application.setAttribute("message",sourceMessage);
        return getMessage(req,res);

    }
    public  String check(HttpServletRequest req,HttpServletResponse res) throws IOException {
        User existUser = (User) req.getSession().getAttribute("existUser");
        if(existUser==null){
            res.getWriter().println("1");
        }else{
            res.getWriter().println("2");
        }
        return null;
    }
    public String kick(HttpServletRequest req,HttpServletResponse res) throws IOException {
        int uid=Integer.parseInt(req.getParameter("uid"));
        Map<User,HttpSession> userMap= (Map<User, HttpSession>) getServletContext().getAttribute("userMap");
        User user=new User();
        user.setUid(uid);
        HttpSession session=userMap.get(user);
        session.invalidate();
        res.sendRedirect(req.getContextPath()+"/main.jsp");
        return null;
    }
    public String login(HttpServletRequest req, HttpServletResponse res){
        Map<String, String[]> map = req.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
            UserService us=new UserServiceImpl();
            User existUser=us.login(user);
            if(existUser==null){
                req.setAttribute("msg","用户名或密码错误");
                return "/login.jsp";
            }else{
                req.getSession().invalidate();
                ServletContext application = getServletContext();
                Map<User,HttpSession> userMap= (Map<User, HttpSession>) application.getAttribute("userMap");
                if(userMap.containsKey(existUser)){
                    HttpSession session=userMap.get("existUser");
                    session.invalidate();
                }
                req.getSession().setAttribute("existUser",existUser);

                String sourceMessage="";
                if (null != application.getAttribute("message")) {
                    sourceMessage = application.getAttribute("message")
                            .toString();
                }
                sourceMessage += "系统公告："
                        + existUser.getUsername() + "走进了聊天室！<br/>";
                application.setAttribute("message", sourceMessage);
              /*  Map<User,HttpSession> userMap1= (Map<User, HttpSession>) application.getAttribute("userMap");
                for(Map.Entry<User,HttpSession> entry:userMap1.entrySet()){
                    System.out.println(entry.getKey().getUsername());
                }*/
                res.sendRedirect(req.getContextPath()+"/main.jsp");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getMessage(HttpServletRequest req,HttpServletResponse res) throws IOException {
        String message= (String) getServletContext().getAttribute("message");
        if(message!=null){
            res.getWriter().println(message);
        }
        return null;
    }
}
