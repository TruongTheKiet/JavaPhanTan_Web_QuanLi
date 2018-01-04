<%-- 
    Document   : dishes_search_content
    Created on : Dec 26, 2017, 11:30:45 PM
    Author     : TheKiet
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.javateam.web_quanli.model.MonAn"%>
<%@page import="java.util.List"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div style="margin: auto;">
    <div id="searchmonan" >
        <a href="${pageContext.request.contextPath}/Dishes"type="button" class="btn btn-primary" style=" margin: auto;float: left" ><i class=" fa-lg fa fa-plus"></i>Add Dish</a>
        <!--button search-->
        <div style="margin-bottom: 20px; text-align: center" class="container">
            <div class="row">
                <div class="span4">
                    <form class="form-inline" method='post' action = 'searchDishes'>
                        <div class="input-append">
                            <div class="form-group">
                                <label style="margin-right: 20px" for="monanSearch" >Name Dishes</label>
                                <input style="width: 450px" type="text" class="form-control" id='monanSearch' name='monanSearch' placeholder="Name dishes">
                            </div>
                            <button type="submit" class="btn">Search</button>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    
                    </form>
                </div>

            </div>
        </div>
        <!--table-->
        <table class="table" >
            <thead class=" thead-light">
                <tr>
                    <th style="width: 400px;font-size: 12pt; font-weight: bold">ID</th>
                    <th style="width: 350px; font-size: 12pt; font-weight: bold" >Name</th>
                    <th style="width: 200px; font-size: 12pt; font-weight: bold">Price ($)</th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="weighttbody">

                <%
                    List<MonAn> listMonAn = (ArrayList<MonAn>) request.getAttribute("listMonAn");
                    for (int i = 0; i < listMonAn.size(); i++) {
                        MonAn monan = listMonAn.get(i);
                %>
                <tr>
                    <td style="width: 400px"><%=monan.getId()%></td>
                    <td style="width: 350px" ><%=monan.getTen()%></td>
                    <td style="width: 200px" ><%=monan.getGia()%></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/editDishes?id=<%=monan.getId()%>" type="button" class="btn btn-primary btn-sm"><b>Detail</b></a>
                    </td>

                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</div>
