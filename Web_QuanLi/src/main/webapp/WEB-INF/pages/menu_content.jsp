<%-- 
    Document   : restaurants_add_content
    Created on : Dec 25, 2017, 11:08:38 PM
    Author     : TheKiet
--%>

<%@page import="com.javateam.web_quanli.model.DanhMucMonAn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div style="margin: auto;">
    <div id="searchbranch" >
        <!--button search-->
        <div style="margin-bottom: 20px; text-align: center" class="container">
            <div class="row">
                <div class="span4">
                    <form class="form-inline" method='post' action = 'Menu'>
                        <div class="input-append">
                            <div class="form-group">
                                <label style="margin-right: 20px" for="branchSearch" >Name Category</label>
                                <input style="width: 450px" type="text" class="form-control" id='nameCategory' name='nameCategory' placeholder="Name branch">
                            </div>
                            <button type="submit" class="btn">Add</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
        <!--table-->
        <table class="table" >
            <thead class=" thead-light">
                <tr>
                    <th style="width: 200px;font-size: 12pt; font-weight: bold">ID</th>
                    <th style="width: 650px; font-size: 12pt; font-weight: bold" >Menu Name</th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="weighttbody">

                <%
                    List<DanhMucMonAn> listDanhMucMonAn = (ArrayList<DanhMucMonAn>) request.getAttribute("listDanhMuc");
                    for (int i = 0; i < listDanhMucMonAn.size(); i++) {
                        DanhMucMonAn danhmuc = listDanhMucMonAn.get(i);
                %>
                <tr>
                    <td id="id_<%=danhmuc.getId()%>" style="width: 200px"><%=danhmuc.getId()%></td>
                    <td id="name_<%=danhmuc.getId()%>" style="width: 650px" ><%=danhmuc.getTenDanhMuc()%></td>
                    <td>
                        <button data-button="<%=danhmuc.getId()%>" type="button" class="openmodal btn btn-primary btn-sm" style="color: white; background: #245269"><i class="fa fa-pencil-square-o fa-2x"></i></button> 
                    </td>

                </tr>
                <%}%>
            </tbody>
        </table>
    </div>

    <!--/*Modal edit category*/-->
    <div>
        <div class="modal fade" id="editcategoryModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" >
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Edit category</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form method='post' action = 'editMenu'>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">ID</label>
                                <input readonly="true" type="text" class="form-control" id="id_modal" name="id_modal">
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Category name</label>
                                <input class="form-control" id="name_modal" name="name_modal"></input>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
