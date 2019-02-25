<%--
  Created by IntelliJ IDEA.
  User: zilonghuang
  Date: 2019/2/18
  Time: 下午11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>购物车</title>
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
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已添加到购物车的内容</h2>
    </div>
    <table id="newTable" class="m-table m-table-row n-table g-b3">
        <tr>
            <th>内容名称</th>
            <th>数量</th>
            <th>价格</th>
        </tr>
        <c:forEach var="shopCar" items="${shopCarList}" varStatus="status">
            <%--<c:set var="liId" value="p-${status.count}"  />--%>
            <%--<c:set var="imgA" value="/show?id=${ShopCar.goodsId}"  />--%>
            <tr>
                <td>${shopCar.good.title}</td>
                <td>
                    <span class="lessNum">-</span>
                    <span class="totalNum" id="allNum">${shopCar.num}</span>
                    <span class="shopcarId" hidden>${shopCar.id}</span>
                    <span class="thisId" hidden>${shopCar.good.goodsId}</span>
                    <span class="moreNum">+</span>
                </td>
                <td>${shopCar.good.price}</td>
            </tr>
        </c:forEach>
    </table>
    <div id="act-btn">
        <button class="u-btn u-btn-primary" id="back">退出</button>
        <button class="u-btn u-btn-primary" id="Account">购买</button>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：hzn<a href="#">易计划-Java开发工程师</a>大作业</p>
</div>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/settleAccount.js"></script>
</body>
</html>
