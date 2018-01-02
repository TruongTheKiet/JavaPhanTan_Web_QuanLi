<%-- 
    Document   : test
    Created on : Jun 4, 2017, 10:24:38 AM
    Author     : TheKiet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/assets/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/mycss.css" />" rel="stylesheet">

        <title>Đăng Nhập</title>
    </head>
    <body>

        <div id="fullscreen_bg" class="fullscreen_bg"/>
        <div id="regContainer" class="container" >
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel-login">
                        <div class="panel-heading">

                            <div>
                                <a href="#" class="active" id="login-form-link">Login</a>
                            </div>

                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="/j_spring_security_check" method="post" role="form" style="display: block;">
                                        <div class="form-group">
                                            <label for="TENTAIKHOAN">Username</label>
                                            <input type="text" name="username" id="TENTAIKHOAN" tabindex="1" class="form-control" placeholder="Username" value="">
                                        </div>
                                        <div class="form-group">
                                            <label for="MATKHAU">Password</label>
                                            <input type="password" name="password" id="MATKHAU" tabindex="2" class="form-control" placeholder="Password">
                                        </div>

                                        <c:if test="${param.error == 'true'}">
                                            <div style="color:red;margin:10px 0px;">

                                                Login Failed!!!<br />
                                                Reason :  ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

                                            </div>
                                        </c:if>
                                        <input type="hidden" name="${_csrf.parameterName}"
                                               value="${_csrf.token}" />
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Log In">
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="<c:url value="resources/assets/js/jquery-1.10.2.js" />"type="text/javascript" ></script>
        <script src="<c:url value="resources/assets/js/bootstrap.min.js"/>" type="text/javascript" ></script>

    </body>
</html>
