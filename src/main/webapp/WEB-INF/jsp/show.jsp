<%--
  Created by IntelliJ IDEA.
  User: zilonghuang
  Date: 2019/2/19
  Time: 下午1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>展示</title>
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
    <c:if test="${not empty result.value}">
        <div class="n-show f-cb" id="showContent">
            <div class="img"><img src="${result.value.goodsImg}" alt=""></div>
            <div class="cnt">
                <h2>${result.value.title}</h2>
                <p class="summary">${result.value.goodsAbstract}</p>
                <div class="price">
                    <span class="v-unit">¥</span><span class="v-value">${result.value.price}</span>
                </div>
                <c:if test="${loginStatus != 'visitor'}">
                    <div class="num">购买数量：<span id="plusNum" class="lessNum"><a>-</a></span>
                        <span class="totalNum" id="allNum">
                        <c:if test="${result.value.haveBuy==1}">
                            ${oldNum}
                        </c:if>
                        <c:if test="${result.value.haveBuy!=1}">
                            ${result.value.goodsNum}
                        </c:if>
                    </span>
                        <span id="addNum" class="moreNum"><a>+</a></span>
                    </div>
                </c:if>
                <div class="oprt f-cb">
                        <%--会变化的地方--%>
                    <c:choose>
                        <c:when test="${loginStatus eq 'buyerUser' && result.value.haveBuy!=1}">
                            <button class="u-btn u-btn-primary" id="add" data-id="${result.value.goodsId}"
                                    data-title="${result.value.title}" data-price="${result.value.price}">
                                加入购物车
                            </button>
                        </c:when>
                        <c:when test="${loginStatus eq 'buyerUser' && result.value.haveBuy==1}">
                            <span class="u-btn u-btn-primary z-dis">已购买</span>
                            <span class="buyprice">当时购买价格：${oldPrice}</span>
                        </c:when>
                        <c:when test="${loginStatus eq 'sellerUser' && result.value.sellerUid==uid}">
                            <c:set var="editA" value="/edit?id=${result.value.goodsId}"></c:set>
                            <a href="${editA}" class="u-btn u-btn-primary">编 辑</a>
                        </c:when>
                        <c:when test="${loginStatus eq 'sellerUser'}">
                            <button class="u-btn u-btn-primary" id="add" data-id="${result.value.goodsId}"
                                    data-title="${result.value.title}" data-price="${result.value.price}">
                                加入购物车
                            </button>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="m-tab m-tab-fw m-tab-simple f-cb">
            <h2>详细信息</h2>
        </div>
        <div class="n-detail">
                ${result.value.goodsText}
        </div>
    </c:if>

</div>
<div class="n-foot">
    <p>版权所有：hzn<a href="#">易计划-Java开发工程师</a>大作业</p>
</div>
</div>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/pageShow.js"></script>
</body>
</html>
