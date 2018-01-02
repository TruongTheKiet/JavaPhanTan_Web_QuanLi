<%-- 
    Document   : nava_header
    Created on : Dec 5, 2017, 1:35:06 AM
    Author     : TheKiet
--%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<nav class="navbar navbar-default navbar-fixed">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-example-2">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Dashboard</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="navbar-text-override">
                    <a href="">
                        <p><i class="fa fa-user" aria-hidden="true"></i> ${userDetails.username}</p>
                    </a>
                </li>
                <form class="hidden" method="post" action="/logout" id="form-logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <li class="navbar-logout-btn" >
                    <a href="#" id = "logout">
                        <p><i class="fa fa-sign-out" aria-hidden="true"></i> Log out</p>
                    </a>
                </li>
                <li class="separator hidden-lg hidden-md"></li>
            </ul>
        </div>
    </div>
</nav>
<div class="content-header-shadow"></div>