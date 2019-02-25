<%--
  Created by IntelliJ IDEA.
  User: zilonghuang
  Date: 2019/2/18
  Time: 下午6:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
    <title>主页面</title>
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
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <c:choose>
                    <c:when test="${loginStatus=='buyerUser' && indextype==1}">
                        <li  ><a href="/">所有内容</a></li>
                        <li class="z-sel" ><a href="/?type=1">未购买的内容</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="z-sel"><a href="/">所有内容</a></li>
                        <c:if test="${loginStatus=='buyerUser'}">
                            <li ><a href="/?type=1">未购买的内容</a></li>
                        </c:if>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
    <div class="n-plist">
        <c:choose>
            <%--${list== null || fn:length(list) == 0}--%>
            <c:when test="${result.value!=null && fn:length(result.value)>0}">
                <ul class="f-cb" id="plist">
                    <c:forEach var="good" items="${result.value}" varStatus="status">
                        <c:set var="liId" value="p-${status.count}"  />
                        <c:set var="imgA" value="/show?id=${good.goodsId}"  />
                        <li id="${liId}">
                            <a href="${imgA}" class="link">
                                <div class="img"><img src="${good.goodsImg}" alt="SICPP"></div>
                                <h3>${good.title}</h3>
                                <div class="price">
                                    <span class="v-unit">¥</span>
                                    <span class="v-value">${good.price}</span>
                                </div>
                                <c:if test="${loginStatus eq 'buyerUser' && good.haveBuy!=1}">
                                    <span class="had"><b>已购买</b></span>
                                </c:if>
                                <c:if test="${loginStatus eq 'sellerUser' && good.isSold==1}">
                                    <span class="had"><b>已售出</b></span>
                                </c:if>
                            </a>
                            <c:if test="${loginStatus eq 'sellerUser' && good.isSold==0}">
                                <span class="u-btn u-btn-normal u-btn-xs del" data-del="125">删除</span>
                            </c:if>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                对不起，还没有数据
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：hzn<a href="#">易计划-Java开发工程师</a>大作业</p>
</div>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/pageindex.js"></script>
</body>
</html>
