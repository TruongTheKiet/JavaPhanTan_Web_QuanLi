/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.DanhMucMonAn;
import com.javateam.web_quanli.model.KhachHangMoi;
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
public class report_new_customer extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    private static final String SAVE_DIR = "UploadImage";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("page", "NewCustomer");
        request.setAttribute("title", "New Customer");
        request.setAttribute("errornewcustomer", "hidden");
        request.setAttribute("erroractivity", "hidden");
        request.setAttribute("activeDishes", "activeNewCustom");
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer year = Integer.parseInt(request.getParameter("year_post").toString());
        String url = defaultUrl + "/getNewCustomer/" + year;
        String objectJSON = helper.getData(url);
        List<KhachHangMoi> list = helper.parseKhachHangMoi(objectJSON);
        List<KhachHangMoi> map = new ArrayList<KhachHangMoi>();

        if (list.size() == 0 || list == null) {
            request.setAttribute("page", "NewCustomer");
            request.setAttribute("title", "New Customer");
            request.setAttribute("activeDishes", "activeNewCustom");

            request.setAttribute("errornewcustomer", "show");
            request.setAttribute("erroractivity", "hidden");

            String view = "/WEB-INF/index.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
            dispatcher.forward(request, response);
            return;
        }
        int total_customer = 0;

        for (Integer j = 0; j < 12; j++) {
            KhachHangMoi item1 = new KhachHangMoi();
            boolean check = false;
            for (int i = 0; i < list.size(); i++) {

                if ((j + 1) == Integer.parseInt(list.get(i).getThang())) {
                    item1.setThang(list.get(i).getThang());
                    item1.setSoluong(list.get(i).getSoluong());

                    total_customer = total_customer + list.get(i).getSoluong();
                    map.add(item1);
                    check = true;
                    break;
                }
            }
            if (!check) {
                Integer tmp = (j + 1);
                item1.setThang(tmp.toString());
                item1.setSoluong(0);
                map.add(item1);

            }
        }

        Map<String, Object> item = new HashMap<String, Object>();
        String s_year = year.toString();
        item.put("date", s_year);
        item.put("titleDate", "Year :");
        item.put("total_customer", total_customer);
        response.setContentType("application/pdf");
        // set input and output stream

        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> maps1 = new HashMap<String, Object>();
        maps1.put("data", map);
        maps.add(maps1);
        
        ServletOutputStream servletOutputStream = response.getOutputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // get report location
            ServletContext context = getServletContext();
            String reportLocation = context.getRealPath("WEB-INF");
            //JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report.jrxml", reportLocation + "/report/test_report.jasper");
            FileInputStream file;
            // get report
            JasperCompileManager.compileReportToFile(reportLocation + "/report/report_new_customer.jrxml", reportLocation + "/report/report_new_customer.jasper");

            File fis = new File(reportLocation + "/report/report_new_customer.jasper");
            if (!fis.exists()) {
                JasperCompileManager.compileReportToFile(reportLocation + "/report/report_new_customer.jrxml", reportLocation + "/report/report_new_customer.jasper");
            }
            file = new FileInputStream(reportLocation + "/report/report_new_customer.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(file);

            // fill it
            JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(maps);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
