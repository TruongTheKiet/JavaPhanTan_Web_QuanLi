<%-- 
    Document   : report_revenue
    Created on : Dec 30, 2017, 12:53:29 AM
    Author     : TheKiet
--%>

<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" data-toggle="tab" href="#home" role="tab"><b>Day</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#profile" role="tab"><b>Week</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#month" role="tab"><b>Month</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#messages" role="tab"><b>Quarterly</b></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" data-toggle="tab" href="#settings" role="tab"><b>Year</b></a>
    </li>
</ul>

<!-- Tab panes -->
<div class="tab-content">
    <div class="tab-pane active" id="home" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="reportRevenue" id="formaddbranch" onsubmit="return submitaddbranch()">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="day"> Day</label>
                <div class="col-sm-3 ">
                    <input id="day" name="day" required="true" class=" day form-control" type="text"  placeholder="dd-mm-yyyy">
                </div>

                <label class="col-sm-2 control-label" for="categorydishes">Type</label>
                <div class="col-sm-3">
                    <select id="id_branch" name="id_branch"  type="text" class="form-control" >
                        <option value="-1"> All</option>
                        <option value="2">Group By Branch</option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                        Export 
                    </button>
                </div>
            </div>

        </form>
    </div>
    <div class="tab-pane" id="profile" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="addRestaurants" id="formaddbranch" onsubmit="return submitaddbranch()">
            <div class="form-group form-group-lg">
                <label class="col-sm-1 control-label" for="day"> Week</label>
                <div class="col-sm-2">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="1"> Week 1</option>
                        <option value="2"> Week 2</option>
                        <option value="3"> Week 3</option>
                        <option value="4"> Week 4</option>
                    </select>
                </div>

                <label class="col-sm-1 control-label" for="categorydishes">Year</label>
                <div class="col-sm-2">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="day">Type</label>
                <div class="col-sm-3">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="1"> All</option>
                        <option value="2"> Group by branch</option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                        Export 
                    </button>
                </div>
            </div>

        </form>
    </div>
    <div class="tab-pane" id="month" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="addRestaurants" id="formaddbranch" onsubmit="return submitaddbranch()">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label" for="day"> Day</label>
                <div class="col-sm-3 ">
                    <input id="day" name="day" required="true" class=" day form-control" type="text"  placeholder="dd/mm/yyyy">
                </div>

                <label class="col-sm-2 control-label" for="categorydishes">Type</label>
                <div class="col-sm-3">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="1"> All</option>
                        <option value="2">Group By Branch</option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                        Export 
                    </button>
                </div>
            </div>

        </form>
    </div>
    <div class="tab-pane" id="messages" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="addRestaurants" id="formaddbranch" onsubmit="return submitaddbranch()">
            <div class="form-group form-group-lg">
                <label class="col-sm-1 control-label" for="day"> Week</label>
                <div class="col-sm-2">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="1"> Quarterly 1</option>
                        <option value="2"> Quarterly 2</option>
                        <option value="3"> Quarterly 3</option>
                        <option value="4"> Quarterly 4</option>
                    </select>
                </div>

                <label class="col-sm-1 control-label" for="categorydishes">Year</label>
                <div class="col-sm-2">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                </div>
                <label class="col-sm-1 control-label" for="day">Type</label>
                <div class="col-sm-3">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="1"> All</option>
                        <option value="2"> Group by branch</option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                        Export 
                    </button>
                </div>
            </div>

        </form>
    </div>
    <div class="tab-pane" id="settings" role="tabpanel">
        <form style="margin-top: 50px;" class="form-horizontal" method="POST" action="addRestaurants" id="formaddbranch" onsubmit="return submitaddbranch()">
            <div class="form-group form-group-lg">


                <label class="col-sm-2 control-label" for="categorydishes">Year</label>
                <div class="col-sm-3">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                </div>
                <label class="col-sm-2 control-label" for="day">Type</label>
                <div class="col-sm-3">
                    <select id="categorydishes" name="categorydishes"  type="text" class="form-control" >
                        <option value="1"> All</option>
                        <option value="2"> Group by branch</option>
                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="form-control btn btn-primary" style="color: #ffffff; background: #0976b4">
                        Export 
                    </button>
                </div>
            </div>

        </form>
    </div>
    
</div>