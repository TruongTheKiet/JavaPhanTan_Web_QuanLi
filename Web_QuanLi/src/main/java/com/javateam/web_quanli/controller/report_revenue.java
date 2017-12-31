/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.DanhMucMonAn;
import com.javateam.web_quanli.model.DoanhThuDay;
import com.javateam.web_quanli.model.MonAn;
import com.javateam.web_quanli.service.Helper;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
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
public class report_revenue extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("page", "Revenue");
        request.setAttribute("title", "Revenue Report");
        request.setAttribute("activeRevenue", "active");
        String view = "/WEB-INF/index.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date_order = request.getParameter("day");
        int id_branch = Integer.parseInt(request.getParameter("id_branch"));

        String url = this.defaultUrl + "/getDoanhThuNgay";
        String data = "{\"id_branch\":" + id_branch + ",\"date_order\":\"" + date_order + "\"}";
        String ObjecJSON = helper.pushData(url, data, "POST");
        List<DoanhThuDay> list = helper.parseDoanhThuDay(ObjecJSON);
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
        int total_revenue = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> item1 = new HashMap<String, Object>();
            item1.put("index", (i+1));
            item1.put("id_order", list.get(i).getId_order());
            item1.put("time", list.get(i).getTime());
            item1.put("branch_name", list.get(i).getBranch_name());
            item1.put("total", list.get(i).getTotal());
            total_revenue = total_revenue + list.get(i).getTotal();
            map.add(item1);
        }
        
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("total_revenue", total_revenue);
        item.put("date", date_order);
       
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
             JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report.jrxml", reportLocation + "/report/test_report.jasper");
          
            File fis = new File(reportLocation + "/report/test_report.jasper");
            if (!fis.exists()) {
                JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report.jrxml", reportLocation + "/report/test_report.jasper");
            }
            file = new FileInputStream(reportLocation + "/report/test_report.jasper");
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
