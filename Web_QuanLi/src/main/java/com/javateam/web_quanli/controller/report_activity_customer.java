/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.DoanhThuDay;
import com.javateam.web_quanli.model.HoatDong;
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
public class report_activity_customer extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    private String phone;
    private int search_all;
    private String nam, thang;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        thang = request.getParameter("month");
        phone = request.getParameter("phone");
        search_all = Integer.parseInt(thang) == -1 ? -1 : 0;
        nam = request.getParameter("year_post");

        DoanhThuDayAll(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void DoanhThuDayAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String branch_report = "";
        String url = this.defaultUrl + "/getActivityCustomer";
       String data = "{\"thang\":\"" + thang +"\",\"nam\":\"" +  nam +"\",	\"phone\":\""+phone+"\",\"search_all\":"+ search_all+"}";
        String ObjecJSON = helper.pushData(url, data, "POST");
        List<HoatDong> list = helper.parseHoatDong(ObjecJSON);
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

        if (list.size() == 0 || list == null) {
            request.setAttribute("page", "NewCustomer");
            request.setAttribute("title", "New Customer");
            request.setAttribute("errornewcustomer", "hidden");
            request.setAttribute("erroractivity", "hidden");
            request.setAttribute("activeNewCustom", "active");
            String view = "/WEB-INF/index.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
            dispatcher.forward(request, response);
            return;
        }
        int total_revenue = 0;
        int atstore = 0, takeaway = 0, online = 0;
        String date_order = "";
        if (search_all != -1) {
            date_order = thang + "/" + nam;
        } else {
            date_order = "Begin create account";
        }
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("revenue_total", total_revenue);
        item.put("date", date_order);
        item.put("titleDate", "Date :");
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
            JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_activity_customer.jrxml", reportLocation + "/report/test_report_activity_customer.jasper");

            File fis = new File(reportLocation + "/report/test_report_activity_customer.jasper");
            if (!fis.exists()) {
                JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_activity_customer.jrxml", reportLocation + "/report/test_report_activity_customer.jasper");
            }
            file = new FileInputStream(reportLocation + "/report/test_report_activity_customer.jasper");
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
