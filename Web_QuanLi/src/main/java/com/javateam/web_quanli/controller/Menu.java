/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.DanhMucMonAn;
import com.javateam.web_quanli.service.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TheKiet
 */
public class Menu extends HttpServlet {
     Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = this.defaultUrl + "/getAllDanhMuc";
        request.setAttribute("page", "Menu");
        request.setAttribute("title", "Menu");
        request.setAttribute("activeMenu", "active");
        String objectJSON = helper.getData(url);
        List<DanhMucMonAn> listDanhMucMonAn = helper.parseDanhMucMonAn(objectJSON);
        request.setAttribute("listDanhMuc", listDanhMucMonAn);
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        request.getParameter
        String url = this.defaultUrl + "/addDanhMuc";
        String name = request.getParameter("nameCategory");
        DanhMucMonAn danhmuc = new DanhMucMonAn();
        danhmuc.tenDanhMuc = name;
        helper.pushData(url, helper.parseClassToJson(danhmuc), "POST");
        
        /*Update Category in Webpage*/
        helper.getData("http://localhost:8080/QuanAnWebApp-1.0-SNAPSHOT/api/update/danhmuc");
        
        this.doGet(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
