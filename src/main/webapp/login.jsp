<%--
  Created by IntelliJ IDEA.
  User: zilonghuang
  Date: 2019/2/16
  Time: 下午6:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="css/style.css" charset="utf-8"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            请<a href="/login.jsp">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <input class="u-ipt" name="userName" autofocus/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <input class="u-ipt" type="password" name="password"/>
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block">登 录</button>
        </div>
    </div>
</form>
<div class="n-foot">
    <p>版权所有：hzn<a href="#">易计划-Java开发工程师</a>大作业</p>
</div>
<script type="text/javascript" src="js/md5.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/pageLogin.js"></script>
</body>
</html>
