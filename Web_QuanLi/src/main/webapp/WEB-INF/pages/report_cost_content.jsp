<%-- 
    Document   : cost_report_content
    Created on : Jan 1, 2018, 10:02:11 PM
    Author     : TheKiet
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.javateam.web_quanli.model.ChiNhanh"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#day" role="tab"><b>Day</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#week_month" role="tab"><b>Week/Month</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#quaterly" role="tab"><b>Quarterly</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#year" role="tab"><b>Year</b></a>
    </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
    <%List<ChiNhanh> listBranch = (ArrayList<ChiNhanh>) request.getAttribute("listBranch");
        String errorday = (String)request.getAttribute("errorday");
        String errorweekmonth = (String)request.getAttribute("errorweekmonth");
        String erroryear = (String)request.getAttribute("erroryear");
    %>
    <div class="tab-pane active" id="day" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportCost" id="formaddbranch" onsubmit="return submitaddbranch()">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="day"> Day</label>
                <div class="col-sm-3 ">
                    <input  maxlength="10" id="day" name="day" required="true" class=" day form-control" type="text"  placeholder="YYYY-MM-DD">
                </div>

                <label class="col-sm-2 control-label" for="id_branch">Type</label>
                <div class="col-sm-3">
                    <select id="id_branch" name="id_branch"  type="text" class="form-control" >
                        <option value="-1"> All</option>
                        <%for(int i = 0 ; i < listBranch.size(); i++){
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
    <div class="tab-pane" id="week_month" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportCostWeekMonth" id="formaddbranch">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="day_from">Date from</label>
                <div class="col-sm-3 ">
                    <input maxlength="10" id="day_from" name="day_from" required="true" class=" day form-control" type="text"  placeholder="YYYY-MM-DD">
                </div>
                <label class="col-sm-1 control-label" for="day_to">Date to</label>
                <div class="col-sm-3 ">
                    <input maxlength="10" id="day_to" name="day_to" required="true" class=" day form-control" type="text"  placeholder="YYYY-MM-DD">
                </div>

                <label class="col-sm-1 control-label" for="day">Type</label>
                <div class="col-sm-2">
                    <select id="id_branch" name="id_branch"  type="text" class="form-control" >
                        <option value="-1"> All</option>
                        <%for(int i = 0 ; i < listBranch.size(); i++){
                            ChiNhanh branch = listBranch.get(i);
                        %>
                        <option value="<%=branch.getId()%>"><%=branch.getTen()%></option>
                        <%}%>
                    </select>
                </div>
            </div>
          
            <div class="form-group form-group-lg">
                <div class="col-sm-4">
                </div>
                <div class="col-sm-4">
                    <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                        Export 
                    </button>
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 

        </form>
    </div>
    <div class="tab-pane" id="quaterly" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportCostQuarterly" id="formaddbranch" onsubmit="return submitaddbranch()">
            <div class="form-group form-group-lg">
                <label class="col-sm-1 control-label" for="day"> Week</label>
                <div class="col-sm-2">
                    <select id="quarterly" name="quarterly"  type="text" class="form-control" >
                        <option value="1"> Quarterly 1</option>
                        <option value="2"> Quarterly 2</option>
                        <option value="3"> Quarterly 3</option>
                        <option value="4"> Quarterly 4</option>
                    </select>
                </div>

                <label class="col-sm-1 control-label" for="year_quarterly">Year</label>
                <div class="col-sm-2">
                    <select id="year_quarterly" name="year_quarterly"  type="text" class="form-control" >
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="day">Type</label>
                <div class="col-sm-3">
                    <select id="id_branch" name="id_branch"  type="text" class="form-control" >
                        <option value="-1"> All</option>
                        <%for(int i = 0 ; i < listBranch.size(); i++){
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
    <div class="tab-pane" id="year" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportCostYear"  >
            <div class="form-group form-group-lg">


                <label class="col-sm-2 control-label" for="categorydishes">Year</label>
                <div class="col-sm-3">
                    <select id="year_post" name="year_post"  type="text" class="form-control" >
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                </div>
                <label class="col-sm-2 control-label" for="day">Type</label>
                <div class="col-sm-3">
                    <select id="id_branch" name="id_branch"  type="text" class="form-control" >
                        <option value="-1"> All</option>
                        <%for(int i = 0 ; i < listBranch.size(); i++){
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

</div>
