<%--
  Created by IntelliJ IDEA.
  User: zilonghuang
  Date: 2019/2/19
  Time: 下午11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>编辑成功</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            <c:choose>
                <c:when test="${loginStatus eq 'buyerUser'}">
                    买家你好，<span class="name">${userName}</span>！<a href="/logout">[退出]</a>
                </c:when>
                <c:when test="${loginStatus eq 'sellerUser'}">
                    卖家你好，<span class="name">${userName}</span>！<a href="/logout">[退出]</a>
                </c:when>
                <c:otherwise>
                    请<a href="/login">[登录]</a>
                </c:otherwise>
            </c:choose>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
            <c:choose>
                <c:when test="${loginStatus=='buyerUser'}">
                    <li><a href="/account">账务</a></li>
                    <li><a href="/settleAccount">购物车</a></li>
                </c:when>
                <c:when test="${loginStatus=='sellerUser'}">
                    <li><a href="/public">发布</a></li>
                </c:when>
            </c:choose>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="n-result">
        <c:set var="showA" value="/show?id=${goodsId}"></c:set>
        <h3>编辑成功！</h3>
        <p><a href="${showA}">[查看内容]</a><a href="/">[返回首页]</a></p>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：hzn<a href="#">易计划-Java开发工程师</a>大作业</p>
</div>
</body>
</html>
