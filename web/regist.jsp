<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018-11-19
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/regist.action" name="frmRegister"  method="post">
    用户名： <input type="text" name="name"> <br/>
    密码： <input type="text" name="pwd"> <br/>
    <input type="submit" value="注册"> <br/>
</form>
</body>
</html>
