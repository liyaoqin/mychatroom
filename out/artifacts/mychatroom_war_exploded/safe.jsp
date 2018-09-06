<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${existUser==null}">
    out.print("<script laguage='javascript'>alert('账户过期，需重新登录');window.location='login.jsp';</script>");
</c:if>

<%--if(null==session.getAttribute("existUser")){
    out.print("<script laguage='javascript'>alert('账户过期，需重新登录');window.location='index.jsp';</script>");
    return;
}--%>

