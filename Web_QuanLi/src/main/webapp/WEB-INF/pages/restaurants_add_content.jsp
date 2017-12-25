<%-- 
    Document   : restaurants_add_content
    Created on : Dec 25, 2017, 11:08:38 PM
    Author     : TheKiet
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.javateam.web_quanli.model.ChiNhanh"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div style="margin: auto;">
    <div id="searchbranch" >
        <button type="button" class="btn btn-primary" style=" margin: auto;float: left" ><i class=" fa-lg fa fa-plus"></i>Add Branch</button>
        <!--button search-->
        <div style="margin-bottom: 20px; text-align: center" class="container">
            <div class="row">
                <div class="span4">
                    <form class="form-inline" method='post' action = 'Restaurants'>
                        <div class="input-append">
                            <div class="form-group">
                                <label style="margin-right: 20px" for="branchSearch" >Name Branch</label>
                                <input style="width: 450px" type="text" class="form-control" id='branchSearch' name='branchSearch' placeholder="Name branch">
                            </div>
                            <button type="submit" class="btn">Search</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <!--table-->
        <table class="table" >
            <thead class=" thead-light">
                <tr>
                    <th style="width: 400px;font-size: 12pt; font-weight: bold">Branch Name</th>
                    <th style="width: 350px; font-size: 12pt; font-weight: bold" >City</th>
                    <th style="width: 200px; font-size: 12pt; font-weight: bold">Phone</th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="weighttbody">

                <%
                    List<ChiNhanh> listBranch = (ArrayList<ChiNhanh>) request.getAttribute("listBranch");
                    for (int i = 0; i < listBranch.size(); i++) {
                        ChiNhanh branch = listBranch.get(i);
                %>
                <tr>
                    <td style="width: 400px"><%=branch.getTen()%></td>
                    <td style="width: 350px" ><%=branch.getTinhThanh()%></td>
                    <td style="width: 200px" ><%=branch.getSdt()%></td>
                    <td>
                        <a href="/Restaurans/Detail/<%=branch.getId()%>" type="button" class="btn btn-primary btn-sm"><b>Detail</b></a>
                    </td>

                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</div>
