<%-- 
    Document   : report_newcustomer_content
    Created on : Jan 1, 2018, 10:11:47 AM
    Author     : TheKiet
--%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="tab-pane" id="year" role="tabpanel">
    <fieldset>
        <legend><div style="font-size: 20pt; color: #002a80;font-weight: bold">New Customer Report</div></legend>
    </fieldset>
    <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportNewCustomer"  >
        <div class="form-group form-group-lg">

            <div class="col-sm-2">
            </div>
            <label class="col-sm-2 control-label" for="categorydishes">Year</label>
            <div class="col-sm-3">
                <select id="year_post" name="year_post"  type="text" class="form-control" >
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
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

    <fieldset>
        <legend><div style="font-size: 20pt; color: #002a80;font-weight: bold">Activity Customer</div></legend>
    </fieldset>
    <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportActivityCustomer"  >
        <div class="form-group form-group-lg">


            <label class="col-sm-2 control-label" for="phone">Phone Customer</label>
            <div class="col-sm-2 ">
                <input maxlength="15" id="phone" name="phone" required="true" class=" day form-control" type="text"  placeholder="">
            </div>
            <label class="col-sm-1 control-label" for="categorydishes">Month</label>
            <div class="col-sm-2">
                <select id="month" name="month"  type="text" class="form-control" >
                    <option value="-1">All</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </div>
            <label class="col-sm-1 control-label" for="year_post">Year</label>
            <div class="col-sm-2">
                <select id="year_post" name="year_post"  type="text" class="form-control" >
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
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
