/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.MonAn;
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
public class addRestaurants extends HttpServlet {
    
    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*search all mon an*/
        String url = this.defaultUrl + "/getAllMonAn";
        String objectJSON = helper.getData(url);
        List<MonAn> listMonAn = helper.parseMonAn(objectJSON);
        request.setAttribute("listMonAn", listMonAn);
        
        
        request.setAttribute("page", "AddRestaurants");
        request.setAttribute("title", "Restaurants");
        request.setAttribute("activeRestaurants", "active");
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("namebranch");
        String address = request.getParameter("addressbranch");
        String phone = request.getParameter("phonebranch");
        String city = request.getParameter("citybranch");
        int  amountTable = Integer.parseInt(request.getParameter("tablebranch"));
        String list_monan = request.getParameter("menuadd");
        
        String url = this.defaultUrl + "/addChiNhanh";
        ChiNhanh tmp = new ChiNhanh(0, name, address, phone, city, amountTable, -1 );
        String dataReturn =  helper.pushData(url, helper.parseClassToJson(tmp), "POST");
        JSONObject obj = new JSONObject(dataReturn);
        
        String id = obj.get("id").toString();
        int id_branchadd = Integer.parseInt(id);
        /*Add menu*/
         url = this.defaultUrl + "/addMenu";
         String add = "{\"id_branch\": "+ id_branchadd +",\"list_id_monan\":\""+ list_monan +"\"}";
         helper.pushData(url, add , "POST");
         
          /*Update Branch in Webpage*/
        helper.getData("http://localhost:8080/QuanAnWebApp-1.0-SNAPSHOT/api/update/chinhanh");
        
        response.sendRedirect("Restaurants");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
