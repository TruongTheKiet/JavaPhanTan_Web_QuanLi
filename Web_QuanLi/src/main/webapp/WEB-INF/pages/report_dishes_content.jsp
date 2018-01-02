<%-- 
    Document   : report_dishes_content
    Created on : Jan 1, 2018, 9:25:01 PM
    Author     : TheKiet
--%>

<%-- 
    Document   : report_revenue
    Created on : Dec 30, 2017, 12:53:29 AM
    Author     : TheKiet
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.javateam.web_quanli.model.ChiNhanh"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="tab-content">
    <%List<ChiNhanh> listBranch = (ArrayList<ChiNhanh>) request.getAttribute("listBranch");
    %>

    <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportDishes" >
        <div class="form-group form-group-lg">
            <label class="col-sm-2 control-label" for="thang">Choose Month</label>
            <div class="col-sm-2">
                <select id="thang" name="thang"  type="text" class="form-control" >
                    <%for(int i = 1; i < 13 ; i++){%>
                    <option value="<%=i%>"><%=i%></option>
                    <%}%>
                </select>
            </div>

            <label class="col-sm-1 control-label" for="nam">Year</label>
            <div class="col-sm-2">
                <select id="nam" name="nam"  type="text" class="form-control" >
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                </select>
            </div>
            <label class="col-sm-1 control-label" for="id_branch">Type</label>
            <div class="col-sm-2">
                <select id="id_branch" name="id_branch"  type="text" class="form-control" >
                    <option value="-1"> All</option>
                    <%for (int i = 0; i < listBranch.size(); i++) {
                            ChiNhanh branch = listBranch.get(i);
                    %>
                    <option value="<%=branch.getId()%>"><%=branch.getTen()%></option>
                    <%}%>
                </select>
            </div>
            <div class="col-sm-2">
                <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                    Export 
                </button>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 

    </form>
</div>