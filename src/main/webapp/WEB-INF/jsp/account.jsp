<%--
  Created by IntelliJ IDEA.
  User: zilonghuang
  Date: 2019/2/18
  Time: 下午11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>账户</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
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
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <c:choose>
        <c:when test="${result.value!=null && fn:length(result.value)>0}">
            <table class="m-table m-table-row n-table g-b3">
                <colgroup>
                    <col class="img"/>
                    <col/>
                    <col class="time"/>
                    <col/>
                    <col class="num"/>
                    <col/>
                    <col class="price"/>
                    <col/>
                </colgroup>
                <thead>
                <tr>
                    <th style="text-align: center">内容图片</th>
                    <th style="text-align: center">内容名称</th>
                    <th style="text-align: center">购买时间</th>
                    <th style="text-align: center">购买数量</th>
                    <th style="text-align: center">购买价格</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${result.value}" varStatus="status">
                    <c:set var="imgA" value="/show?id=${order.good.goodsId}"/>
                    <tr>
                        <td><a href="${imgA}"><img src="${order.good.goodsImg}" alt=""></a></td>
                        <td><h4><a href="${imgA}">${order.good.title}</a></h4></td>
                        <td>
                                <span class="v-time">
                                    <fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </span>
                        </td>
                        <td><span class="v-num">${order.goodsNum}</span></td>
                        <td><span class="v-unit">¥</span><span class="value">${order.goodsPrice}</span></td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="4">
                        <div class="total">总计：</div>
                    </td>
                    <td><span class="v-unit">¥</span><span class="value">${sumPrice}</span></td>
                </tr>
                </tfoot>
            </table>
        </c:when>
        <c:otherwise>
            <div class="n-result">
                <h3>暂无内容！</h3>
            </div>
        </c:otherwise>
    </c:choose>

</div>
<div class="n-foot">
    <p>版权所有：hzn<a href="#">易计划-Java开发工程师</a>大作业</p>
</div>
</body>
</body>
</html>
