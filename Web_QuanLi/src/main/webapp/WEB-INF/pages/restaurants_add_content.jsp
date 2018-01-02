<%-- 
    Document   : restaurants_add_content
    Created on : Dec 25, 2017, 11:08:38 PM
    Author     : TheKiet
--%>
<%@page import="com.javateam.web_quanli.model.MonAn"%>
<%@page import="com.javateam.web_quanli.model.DanhMucMonAn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div >
    <form class="form-horizontal" method="POST" action="addRestaurants" id="formaddbranch" onsubmit="return submitaddbranch()">
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="namedishes">Branch Name</label>
            <div class="col-sm-8 ">
                <input id="namebranch" name="namebranch" required="true" class="form-control" type="text"  placeholder="Branch Name">
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="addressbranch">Address</label>
            <div class="col-sm-8">
                <textarea name="addressbranch" name="addressbranch" required="true" class="form-control" rows="3"></textarea>
            </div>
        </div>

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="phonebranch">Phone</label>
            <div class="col-sm-8 ">
                <input id="phonebranch" name="phonebranch" required="true" class="form-control" type="number"  placeholder="Branch Phone">
            </div>
        </div>

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="citybranch">City</label>
            <div class="col-sm-8 ">
                <input id="citybranch" name="citybranch" required="true" class="form-control" type="text"  placeholder="Branch City">
            </div>
        </div>

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="tablebranch">Amount Table</label>
            <div class="col-sm-8 ">
                <input id="tablebranch" name="tablebranch" required="true" class="form-control" type="number"  placeholder="Amount Table">
            </div>
        </div>


        <input id="menuadd" name="menuadd"  class="form-control" type="hidden">
        <input id="menudelete" name="menudelete" class="form-control" type="hidden">
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
                    List<MonAn> listMonAn = (ArrayList<MonAn>) request.getAttribute("listMonAn");
                    for (int i = 0; i < listMonAn.size(); i++) {
                        MonAn monan = listMonAn.get(i);
                %>
                <tr>
                    <td style="width: 400px"><%=monan.getId()%></td>
                    <td style="width: 350px" ><%=monan.getTen()%></td>
                    <td style="width: 200px" ><%=monan.getGia()%></td>
                    <td>
                        <input class="checkboxchoose" type="checkbox" name="<%=monan.getId()%>" value="<%=monan.getId()%>">
                    </td>

                </tr>
                <%}%>
            </tbody>
        </table>

        <div class="form-group form-group-lg" style="margin-top: 480px" >
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <button id ="addRestaurant" type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                    Add dishes
                </button>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
    </form>
</div>
