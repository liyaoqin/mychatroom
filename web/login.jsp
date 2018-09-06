<%--
  Created by IntelliJ IDEA.
  User: lyq
  Date: 2018/8/8
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${ msg }
<form id="form1" action="${pageContext.request.contextPath }/user" method="get" onsubmit="return check()">
    <input type="hidden" name="method" value="login">
    用户名：<input type="text" placeholder="请输入用户名..." name="username"><br/>
    密码：<input type="text" placeholder="请输入密码..." name="password"><br/>
    <input type="submit" value="登录">
</form>
</body>
<script type="text/javascript">
    function check() {
        if(document.getElementById("form1").username.value==""){
            alert("请输入用户名！");
            document.getElementById("form1").username.focus();
            return false;
        }

        if(document.getElementById("form1").password.value==""){
            alert("请输入密码名！");
            document.getElementById("form1").password.focus();
            return false;
        }
    }
</script>
</html>
