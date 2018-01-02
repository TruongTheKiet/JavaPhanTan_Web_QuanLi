<%-- 
    Document   : left_menu
    Created on : Dec 5, 2017, 1:48:22 AM
    Author     : TheKiet
--%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div class="sidebar" data-color="none" data-image="resources/assets/img/sidebar-bg.png">
    <div class="sidebar-wrapper">
        <div class="logo">
            <div class="simple-text">
                LOGO GOES HERE
            </div>
            <div class="horizontal-separator"></div>
        </div>
        <div class="header-shadow"></div>
        <ul class="nav">
<!--            <li class="${activeDashboard}">
                <a href="dashboard">
                    <img class="tab-icon" src="resources/assets/img/icon_dashboard.png"/>
                    <p>Dashboard</p>
                </a>
            </li>-->
            <li class="${activeRestaurants}">
                <a href="/Restaurants">
                    <img class="tab-icon" src="resources/assets/img/icon_restaurants.png"/>
                    <p>Restaurants</p>
                </a>
            </li>
            <li class="${activeMenu}">
                <a href="/Menu">
                    <img class="tab-icon" src="resources/assets/img/icon_menus.png"/>
                    <p>Menus</p>
                </a>
            </li>
            <li class="${activeDishes}">
                <a href="searchDishes">
                    <img class="tab-icon" src="resources/assets/img/icon_dishes.png"/>
                    <p>Dishes</p>
                </a>
            </li>
            <li class="${activeRevenue}">
                <a href="reportRevenue">
                    <img class="tab-icon" src="resources/assets/img/icon_revenue.png"/>
                    <p>Revenue Reports</p>
                </a>
            </li>
            
            <li class="${activeReportOrder}">
                <a href="reportOrder">
                    <img class="tab-icon" src="resources/assets/img/icon_order.png"/>
                    <p>Amount Order Reports</p>
                </a>
            </li>
            <li class="${activerReportCost}">
                <a href="reportCost">
                    <img class="tab-icon" src="resources/assets/img/icon_cost.png"/>
                    <p>Cost Reports</p>
                </a>
            </li>
            <li class="${activerReportDishes}">
                <a href="reportDishes">
                    <img class="tab-icon" src="resources/assets/img/icon_reports.png"/>
                    <p>Dishes Reports</p>
                </a>
            </li>
            <li class="${activeNewCustom}">
                <a href="reportNewCustomer">
                    <img class="tab-icon" src="resources/assets/img/icon_report2.png"/>
                    <p>Customer Reports</p>
                </a>
            </li>
        </ul>
    </div>
</div>
