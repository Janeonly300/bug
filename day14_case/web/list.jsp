<%@page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        h3 {
            text-align: center;
        }

        #search {
            float: left;
            margin: 10px;
        }

        #search div {
            margin-right: 5px;
        }

        #add_del {
            float: right;
            margin: 10px;
        }

        #add_del a {
            margin-left: 5px;
        }

        td, th {
            text-align: center;
        }

        #paging_info {
            font-size: 15px;
            margin-left: 10px;
            line-height: 35px;
        }
    </style>
    <script>

        function deleteUser(id) {
            //用户安全提示
            if (confirm("您确定要删除吗?")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/DeleteUserServlet?id="+id;
            }
        }

        window.onload = function () {
            // 给删除选中按钮添加删除事件
            document.getElementById("delSelected").onclick = function () {
                if (confirm("您确定要删除吗？")) {
                    var flag = false;
                    // 判断是否有选中条目
                    var cbs = document.getElementsByName("id");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            // 有一个条目选中了
                            flag = true;
                            break;
                        }
                    }
                    // 有条目被选中
                    if (flag) {
                        // 表单提交
                        document.getElementById("form").submit();
                    }
                }
            }

            // 全选按钮
            // // 1.获取第一个 cb
            // document.getElementById("firstCb").onclick = function () {
            //     //2.获取下边列表中所有的 cb
            //     var cbs = document.getElementsByName("id");
            //     //3.遍历
            //     for (var i = 0; i < cbs.length; i++) {
            //         //4.设置这些cbs[i]的checked状态 = firstCb.checked
            //         cbs[i].checked = this.checked;
            //     }
            // }
        }
    </script>
</head>
<body>
<div class="container">
    
    <h3>用户信息列表</h3>
    <div style="float: left">
        <form class="form-inline" action="${pageContext.request.contextPath}/FindUserByPageServlet">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="username" value="${condition.username[0]}" class="form-control" id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName22">籍贯</label>
                <input type="text" name="address" value="${condition.address[0]}" class="form-control" id="exampleInputName22">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">Email</label>
                <input type="email" value="${condition.email[0]}" name="email" class="form-control" id="exampleInputEmail2" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <div style="float: right;padding: 5px" >
        <a class="btn btn-primary" href="add.jsp">添加联系人</a>
        <a class="btn btn-primary" id="delSelected" >删除选中</a>
    </div>
    <%--用户信息表单--%>
    <form id="form" action="${pageContext.request.contextPath}/DeleteSelectUserServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>qq</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pb.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="id" value="${user.id}"></td>
                    <td>${s. count}</td>
                    <td>${user.username}</td>
                    <td>${user.sex}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/FindUserServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id})">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">
                    <c:if test="${pb.currentPage==i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage!=i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5&username=${condition.username[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a>
                        </li>
                    </c:if>

                </c:forEach>
                <li>
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px" >
                共有${pb.totalCount}条记录,共分为${pb.totalPage}页
                </span>
            </ul>
    </div>

</div>
</body>
</html>