/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.ChiPhi;
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
public class report_cost_year extends HttpServlet {

  

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    private int id_branch;
    private String date_from, date_to;
    private  String year ;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id_branch = Integer.parseInt(request.getParameter("id_branch"));
        year = request.getParameter("year_post");
        date_to = year + "-12-31";
        date_from =  year + "-01-01";

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
        String url = this.defaultUrl + "/getAllChiNhanh";
        String ObjecJSONbranch = helper.getData(url);
        List<ChiNhanh> listBranch = helper.parseChiNhanh(ObjecJSONbranch);
       
        String branch_report = "";
         url = this.defaultUrl + "/getChiPhiNam";
        String data = "{\"id_branch\":" + id_branch + ",\"date_from\":\"" + date_from + "\",\"date_to\":\"" + date_to + "\"}";
        String ObjecJSON = helper.pushData(url, data, "POST");
        List<ChiPhi> list = helper.parseChiPhi(ObjecJSON);
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

        if (list.size() == 0) {
            request.setAttribute("page", "reportCost");
            request.setAttribute("title", "Report Cost");
            request.setAttribute("activerReportCost", "active");

//        request.setAttribute("erroryear", "hidden");
//        request.setAttribute("errorday", "hidden");
//        request.setAttribute("errorweekmonth", "hidden");
            url = this.defaultUrl + "/getAllChiNhanh";
            String objectJSON = helper.getData(url);
            listBranch = helper.parseChiNhanh(objectJSON);
            request.setAttribute("listBranch", listBranch);
            String view = "/WEB-INF/index.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
            dispatcher.forward(request, response);
            return;
        }

        int cost_total = 0;
        for (int i = 0; i < list.size(); i++) {
            cost_total = cost_total + list.get(i).getChiphi();
        }
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("date", year);
        item.put("titleDate", "Year :");
        item.put("cost_total", cost_total);
        if (id_branch == -1) {
            item.put("branch", "All branch");
        } else {
            for (int i = 0; i < listBranch.size(); i++) {
                if (id_branch == listBranch.get(i).getId()) {
                    item.put("branch", listBranch.get(i).getTen());
                    break;
                }
            }
        }
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
            JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_cost.jrxml", reportLocation + "/report/test_report_cost.jasper");

            File fis = new File(reportLocation + "/report/test_report_cost.jasper");
            if (!fis.exists()) {
                JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_cost.jrxml", reportLocation + "/report/test_report_cost.jasper");
            }
            file = new FileInputStream(reportLocation + "/report/test_report_cost.jasper");
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
