/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.DanhMucMonAn;
import com.javateam.web_quanli.model.MonAn;
import com.javateam.web_quanli.service.Helper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author TheKiet
 */
public class edit_Dishes extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    private static final String SAVE_DIR = "UploadImage";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String url = this.defaultUrl + "/getMonAn/" + id;

        String objectJSON = helper.getData(url);
        MonAn monan = helper.parseOneMonAn(objectJSON);

        url = this.defaultUrl + "/getAllDanhMuc";
        objectJSON = helper.getData(url);
        List<DanhMucMonAn> listDanhMucMonAn = helper.parseDanhMucMonAn(objectJSON);

        request.setAttribute("listDanhMuc", listDanhMucMonAn);
        request.setAttribute("monan", monan);
        request.setAttribute("page", "editDishes");
        request.setAttribute("title", "Dishes");
        request.setAttribute("activeDishes", "active");
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String linkImage;
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        // Create path components to save the file
        final String path = savePath;
        final Part filePart = request.getPart("imagedishes");

        final String fileName = getFileName(filePart);
        if (fileName.compareTo("") != 0 ) {
            OutputStream out = null;
            InputStream filecontent = null;
            final PrintWriter writer = response.getWriter();

            try {
                out = new FileOutputStream(new File(path + File.separator
                        + fileName));
                filecontent = filePart.getInputStream();

                int read = 0;
                final byte[] bytes = new byte[1024];

                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                int id = Integer.parseInt(request.getParameter("iddishes"));
                linkImage = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "BPQuanLi/UploadImage/" + fileName;
                String name = request.getParameter("namedishes");
                int price = Integer.parseInt(request.getParameter("pricedishes"));
                String image = linkImage;
                String inforrmation = request.getParameter("information");
                int id_DanhMuc = Integer.parseInt(request.getParameter("categorydishes"));
                MonAn monanAdd = new MonAn(id, name, price, image, inforrmation, id_DanhMuc);

                String url = this.defaultUrl + "/updateMonAn";
                helper.pushData(url, helper.parseClassToJson(monanAdd), "PUT");

            } catch (FileNotFoundException fne) {
                response.sendRedirect("/Dishes");
            } finally {
                response.sendRedirect("/Dishes");
                if (out != null) {
                    out.close();
                }
                if (filecontent != null) {
                    filecontent.close();
                }

            }
        } else {
            int id = Integer.parseInt(request.getParameter("iddishes"));
            linkImage = request.getParameter("link");
            String name = request.getParameter("namedishes");
            int price = Integer.parseInt(request.getParameter("pricedishes"));
            String image = linkImage;
            String inforrmation = request.getParameter("information");
            int id_DanhMuc = Integer.parseInt(request.getParameter("categorydishes"));
            MonAn monanAdd = new MonAn(id, name, price, image, inforrmation, id_DanhMuc);

            String url = this.defaultUrl + "/updateMonAn";
            helper.pushData(url, helper.parseClassToJson(monanAdd), "PUT");
        }
        
        response.sendRedirect("searchDishes");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
