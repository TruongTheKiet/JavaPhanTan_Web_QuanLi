/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.MonAn;

import com.javateam.web_quanli.model.Menu;
import com.javateam.web_quanli.service.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author TheKiet
 */
public class editRestaurant extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String url = this.defaultUrl + "/getChiNhanh/" + id;
        String objectJSON = helper.getData(url);
        /*chi nhanh edit*/
        ChiNhanh chinhanh = helper.parseOneChiNhanh(objectJSON);
        /*mon an chi nahnh*/
        url= this.defaultUrl + "/getMenuBranch/" + id;
         objectJSON = helper.getData(url);
        List<Menu> menu_branch = helper.parseMenu(objectJSON);
        String list_id = "";
        for(int i = 0; i < menu_branch.size(); i++){
             
            list_id = list_id + menu_branch.get(i).getID_MonAn();
            if(i <= (menu_branch.size() - 2))
               list_id = list_id + ",";
        }
        /*all mon an*/
        url = this.defaultUrl + "/getAllMonAn";
        objectJSON = helper.getData(url);
        List<MonAn> listMonAn = helper.parseMonAn(objectJSON);
        
        request.setAttribute("branch", chinhanh);
        request.setAttribute("listMonAnBranch", menu_branch);
        
        request.setAttribute("listMonAn", listMonAn);
        
        request.setAttribute("page", "editRestaurants");
        request.setAttribute("title", "Restaurants");
        request.setAttribute("activeRestaurants", "active");
        
        request.setAttribute("list_id", list_id);
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idbranch"));
        String name = request.getParameter("namebranch");
        String address = request.getParameter("addressbranch");
        String phone = request.getParameter("phonebranch");
        String city = request.getParameter("citybranch");
        int  amountTable = Integer.parseInt(request.getParameter("tablebranch"));
        String list_monanadd = request.getParameter("menuadd");
        String list_monanDelete = request.getParameter("menudelete");
        
        
        String url = this.defaultUrl + "/updateChiNhanh";
        ChiNhanh tmp = new ChiNhanh(id, name, address, phone, city, amountTable, -1 );
        String dataReturn =  helper.pushData(url, helper.parseClassToJson(tmp), "PUT");
        
        
        url = this.defaultUrl + "/addMenu";
        String add = "{\"id_branch\": " + id + ",\"list_id_monan\":\"" + list_monanadd + "\"}";
        helper.pushData(url, add, "POST");

        url = this.defaultUrl + "/deleteMenu/" + id + "/" + list_monanDelete;
        helper.pushData(url, add, "DELETE");
        response.sendRedirect("Restaurants");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
