<%-- 
    Document   : template
    Created on : Oct 8, 2017, 2:05:17 PM
    Author     : TheKiet
--%>
<!DOCTYPE html>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <tiles:insertAttribute name="header"/>
    <body>
        <div class="wrapper">
            <tiles:insertAttribute name="left_menu"/>
            <div class="main-panel">
                <tiles:insertAttribute name="nava_header"/>
                <div class="content">
                    <div class="container-fluid">
                        <tiles:insertAttribute name="_content"/>
                    </div>
                </div>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/>
    </body>
</html>
