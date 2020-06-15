<%--
  Created by IntelliJ IDEA.
  User: 简一
  Date: 2020/6/9
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>首页</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.2.1.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div align="center">
    <div>${user.name}，欢迎您</div>
    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=1&rows=5" style="....">查询所有用户</a>
    <%
     out.write("正常");
    %>
</div>
</body>
</html>
</html>
