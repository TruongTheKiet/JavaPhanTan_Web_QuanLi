/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

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
public class editMenu extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.sendRedirect("Menu");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = this.defaultUrl + "/updateDanhMuc";
        String name = request.getParameter("name_modal");
        int id = Integer.parseInt(request.getParameter("id_modal"));
        DanhMucMonAn danhmuc = new DanhMucMonAn();
        danhmuc.tenDanhMuc = name;
        danhmuc.id = id;
        helper.pushData(url, helper.parseClassToJson(danhmuc), "PUT");
        response.sendRedirect("Menu");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
