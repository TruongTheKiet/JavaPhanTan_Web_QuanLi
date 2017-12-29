<%-- 
    Document   : restaurants_edit_content
    Created on : Dec 29, 2017, 10:22:59 AM
    Author     : TheKiet
--%>


<%@page import="com.javateam.web_quanli.model.Menu"%>
<%@page import="com.javateam.web_quanli.model.ChiNhanh"%>
<%@page import="com.javateam.web_quanli.model.MonAn"%>
<%@page import="com.javateam.web_quanli.model.DanhMucMonAn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div >
    <%
        List<MonAn> listMonAn = (ArrayList<MonAn>) request.getAttribute("listMonAn");
        List<Menu> listMonAnBranch = (ArrayList<Menu>) request.getAttribute("listMonAnBranch");
        ChiNhanh chinhanh = (ChiNhanh) request.getAttribute("branch");
        String list_id = (String)request.getAttribute("list_id");
    %>
    <form class="form-horizontal" method="POST" action="editRestaurant">

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="idbranch">Branch ID</label>
            <div class="col-sm-8 ">
                <input value="<%=chinhanh.getId()%>" readonly="true" id="idbranch" name="idbranch" required="true" class="form-control" type="text"  placeholder="Branch ID">
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="namedishes">Branch Name</label>
            <div class="col-sm-8 ">
                <input value="<%=chinhanh.getTen()%>" id="namebranch" name="namebranch" required="true" class="form-control" type="text"  placeholder="Branch Name">
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="addressbranch">Address</label>
            <div class="col-sm-8">
                <textarea  name="addressbranch" name="addressbranch" required="true" class="form-control" rows="3"><%=chinhanh.getDiaChi()%></textarea>
            </div>
        </div>

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="phonebranch">Phone</label>
            <div class="col-sm-8 ">
                <input value="<%=chinhanh.getSdt()%>" id="phonebranch" name="phonebranch" required="true" class="form-control" type="text"  placeholder="Branch Phone">
            </div>
        </div>

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="citybranch">City</label>
            <div class="col-sm-8 ">
                <input value="<%=chinhanh.getTinhThanh()%>"id="citybranch" name="citybranch" required="true" class="form-control" type="text"  placeholder="Branch City">
            </div>
        </div>

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="tablebranch">Amount Table</label>
            <div class="col-sm-8 ">
                <input  value="<%=chinhanh.getSoLuongBan()%>"id="tablebranch" name="tablebranch" required="true" class="form-control" type="number"  placeholder="Amount Table">
            </div>
        </div>


        <input id="menuadd" name="menuadd"  class="form-control" type="hidden">
        <input id="menudelete" name="menudelete" class="form-control" type="hidden">
        <input id="menuexist" name="menuexist" value="<%=list_id%>"class="form-control" type="hidden">
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" >Menu</label>
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
                    for (int i = 0; i < listMonAn.size(); i++) {
                        MonAn monan = listMonAn.get(i);
                        String check = "";
                %>
                <tr>
                    <td style="width: 400px"><%=monan.getId()%></td>
                    <td style="width: 350px" ><%=monan.getTen()%></td>
                    <td style="width: 200px" ><%=monan.getGia()%></td>
                    <%
                        for (int j = 0; j < listMonAnBranch.size(); j++) {
                            if (monan.getId() == listMonAnBranch.get(j).getID_MonAn()) {
                                check = "checked";
                            }%>
                    <%}%>
                    <td>
                        <input class="updatecheckboxchoose" <%=check%> type="checkbox" name="<%=monan.getId()%>" value="<%=monan.getId()%>">
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>

        <div class="form-group form-group-lg" style="margin-top: 480px" >
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <button id ="updateRestaurant" type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                    Update dishes
                </button>
            </div>
        </div>
    </form>
</div>
