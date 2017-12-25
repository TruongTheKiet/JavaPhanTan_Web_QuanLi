/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *****/
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.service.Helper;
import java.io.IOException;
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
public class Restaurants extends HttpServlet {
    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = this.defaultUrl + "/getAllChiNhanh";
        request.setAttribute("page", "Restaurants");
        request.setAttribute("title", "Restaurants");
        request.setAttribute("activeRestaurants", "active");
        String objectJSON = helper.getData(url);
        List<ChiNhanh> listBranch = helper.parseChiNhanh(objectJSON);
        request.setAttribute("listBranch", listBranch);
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = this.defaultUrl + "/getChiNhanh/Search/" + request.getParameter("branchSearch");
        request.setAttribute("page", "Restaurants");
        request.setAttribute("title", "Restaurants");
        request.setAttribute("activeRestaurants", "active");
        String objectJSON = helper.getData(url);
        List<ChiNhanh> listBranch = helper.parseChiNhanh(objectJSON);
        request.setAttribute("listBranch", listBranch);
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
