/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.DoanhThuDay;
import com.javateam.web_quanli.model.MonAnThang;
import com.javateam.web_quanli.service.Helper;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author TheKiet
 */
public class report_dishes extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";

    private int id_branch;
    private String thang, nam;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("page", "reportDishes");
        request.setAttribute("title", "Report Dishes");
        request.setAttribute("activerReportDishes", "active");

//        request.setAttribute("erroryear", "hidden");
//        request.setAttribute("errorday", "hidden");
//        request.setAttribute("errorweekmonth", "hidden");
        String url = this.defaultUrl + "/getAllChiNhanh";
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
        thang = request.getParameter("thang");
        nam = request.getParameter("nam");
        id_branch = Integer.parseInt(request.getParameter("id_branch"));

        switch (id_branch) {
            case -1:
                DoanhThuDayAll(request, response);
                break;
            default:
                DoanhThuDayAll(request, response);
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void DoanhThuDayAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String branch_report = "";
        String url = this.defaultUrl + "/getDishesMonth/" + id_branch + "/" + thang + "/" + nam;
        String ObjecJSON = helper.getData(url);
        List<MonAnThang> list = helper.parseMonAnThang(ObjecJSON);
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

        if (list.size() == 0) {
            request.setAttribute("page", "reportDishes");
            request.setAttribute("title", "Report Dishes");
            request.setAttribute("activerReportDishes", "active");

//        request.setAttribute("erroryear", "hidden");
//        request.setAttribute("errorday", "hidden");
//        request.setAttribute("errorweekmonth", "hidden");
             url = this.defaultUrl + "/getAllChiNhanh";
            String objectJSON = helper.getData(url);
            List<ChiNhanh> listBranch = helper.parseChiNhanh(objectJSON);
            request.setAttribute("listBranch", listBranch);
            String view = "/WEB-INF/index.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
            dispatcher.forward(request, response);
            return;
        }
       
            if (id_branch != -1) {
                branch_report = list.get(0).getTenchinhanh();
            } else {
                branch_report = "All branch";
            }


        Map<String, Object> item = new HashMap<String, Object>();
        item.put("date", thang + "/" + nam);
        item.put("titleDate", "Date :");
        item.put("branch", branch_report);
        response.setContentType("application/pdf");

        // set input and output stream
        ServletOutputStream servletOutputStream = response.getOutputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // get report location
            ServletContext context = getServletContext();
            String reportLocation = context.getRealPath("WEB-INF");
            //JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report.jrxml", reportLocation + "/report/test_report.jasper");
            FileInputStream file;
            // get report
            JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_dishes.jrxml", reportLocation + "/report/test_report_dishes.jasper");

            File fis = new File(reportLocation + "/report/test_report_dishes.jasper");
            if (!fis.exists()) {
                JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_dishes.jrxml", reportLocation + "/report/test_report_dishes.jasper");
            }
            file = new FileInputStream(reportLocation + "/report/test_report_dishes.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(file);

            // fill it
            JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(list);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, item, jrbcds);

            // export to pdf
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

            response.setContentLength(baos.size());
            baos.writeTo(servletOutputStream);

            // close it
            file.close();

            bufferedInputStream.close();

        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            servletOutputStream.flush();
            servletOutputStream.close();
            baos.close();
        }
    }
}
