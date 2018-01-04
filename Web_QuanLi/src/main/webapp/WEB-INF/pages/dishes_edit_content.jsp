<%-- 
    Document   : dishes_edit_content
    Created on : Dec 29, 2017, 6:14:03 PM
    Author     : TheKiet
--%>
<%@page import="com.javateam.web_quanli.model.MonAn"%>
<%@page import="com.javateam.web_quanli.model.DanhMucMonAn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div >
    <%
        MonAn monan = (MonAn) request.getAttribute("monan");
    %>
    <form class="form-horizontal" method="POST" action="${pageContext.request.contextPath}/editDishes" enctype="multipart/form-data">
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="iddishes">ID dishes</label>
            <div class="col-sm-8 ">
                <input value="<%=monan.getId()%>" readonly id="iddishes" name="iddishes" readonly="true" required="true" class="form-control" type="text"  placeholder="Name dishes">
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="namedishes">Name dishes</label>
            <div class="col-sm-8 ">
                <input value="<%=monan.getTen()%>" id="namedishes" name="namedishes" required="true" class="form-control" type="text"  placeholder="Name dishes">
            </div>
        </div>
        <div class="form-group form-group-lg ">
            <label class="col-sm-2 control-label" for="pricedishes">Price ($)</label>
            <div class="col-sm-8"> 
                <input value="<%=monan.getGia()%>" id="pricedishes"  name="pricedishes" required="true" type="number" value="1000" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" />
            </div>
        </div>

        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="information">Information</label>
            <div class="col-sm-8">
                <textarea name="information" name="information" required="true" class="form-control" rows="3">"<%=monan.getThongTin()%></textarea>
            </div>
        </div>
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="categorydishes">Category</label>
            <div class="col-sm-8">
                <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                    <% List<DanhMucMonAn> listDanhMuc = (ArrayList<DanhMucMonAn>) request.getAttribute("listDanhMuc");
                        for (int i = 0; i < listDanhMuc.size(); i++) {
                            DanhMucMonAn danhmuc = listDanhMuc.get(i);
                            if (monan.getId_DanhMuc() == danhmuc.getId()) {
                    %>
                    <option selected value="<%=danhmuc.getId()%>"> <%=danhmuc.getTenDanhMuc()%> </option>
                    <%} else {%>
                    <option value="<%=danhmuc.getId()%>"> <%=danhmuc.getTenDanhMuc()%> </option>
                    <%}
                        }%>
                </select>
            </div>
        </div>
        <input hidden="true" name="link" id="link" value="<%=monan.getHinhAnh()%>"  type="text" >
        <div class="form-group form-group-lg" style="text-align: center">
            <label class="col-sm-2 control-label" for="imagedishes">Upload image</label>
            <div class="input-group col-sm-7" style="margin-left: 190px!important">
                <input name="imagedishes" id="imagedishes"  type="file" class="form-control " >
            </div>
            <img id='img-upload' style="width: 700px;"/>
        </div>

        <div class="form-group form-group-lg">
            <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                    Update dishes
                </button>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
                    
    </form>
</div>
