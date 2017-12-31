/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.controller;

import com.javateam.web_quanli.model.ChiNhanh;
import com.javateam.web_quanli.model.DoanhThuDay;
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
public class report_revenue_year extends HttpServlet {

    Helper helper = new Helper();
    private String defaultUrl = "http://localhost:8080/RestAPI_QuanLi";
    private int id_branch;
    private String date_from, date_to;
    private String year;

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
        String branch_report = "";
        String url = this.defaultUrl + "/getDoanhThuNam";
        String data = "{\"id_branch\":" + id_branch + ",\"date_from\":\"" + date_from + "\",\"date_to\":\"" + date_to + "\"}";
        String ObjecJSON = helper.pushData(url, data, "POST");
        List<DoanhThuDay> list = helper.parseDoanhThuDay(ObjecJSON);
        List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

        if (list.size() == 0) {
            request.setAttribute("page", "Revenue");
            request.setAttribute("title", "Revenue Report");
            request.setAttribute("activeRevenue", "active");
            request.setAttribute("erroryear", "show");
            request.setAttribute("errorday", "hidden");
            request.setAttribute("errorweekmonth", "hidden");
            String url_return = this.defaultUrl + "/getAllChiNhanh";
            String objectJSON = helper.getData(url_return);
            List<ChiNhanh> listBranch = helper.parseChiNhanh(objectJSON);
            request.setAttribute("listBranch", listBranch);
            String view = "/WEB-INF/index.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(view);
            dispatcher.forward(request, response);
            return;
        }
        int total_revenue = 0, total_order = 0;
        int atstore = 0, takeaway = 0, online = 0;
        String date = "";
        int stt = 1, tongdonhang = 0, tientongdonhangngay = 0;
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> item1 = new HashMap<String, Object>();
            if (id_branch != -1) {
                branch_report = list.get(i).getBranch_name();
            } else {
                branch_report = "All branch";
            }
            switch (list.get(i).getType_order()) {
                case 0:/*store*/
                    atstore++;
                    break;
                case 1:/*take away */
                    takeaway++;
                    break;
                case 2:
                    /*online*/
                    online++;
                    break;
            }
            if (i == 0) {
                date = list.get(i).getTime();
                tongdonhang = tongdonhang + 1;
                tientongdonhangngay = tientongdonhangngay + list.get(i).getTotal();
            } else if (date.compareTo(list.get(i).getTime()) == 0) {
                tongdonhang = tongdonhang + 1;
                tientongdonhangngay = tientongdonhangngay + list.get(i).getTotal();
            } else {
                item1.put("stt", stt);
                item1.put("id_order", tongdonhang);
                item1.put("time", date);
                item1.put("branch_name", "");
                item1.put("total", tientongdonhangngay);
                item1.put("type_order", 0);
                map.add(item1);
                date = list.get(i).getTime();
                tongdonhang = 1;
                tientongdonhangngay = 0;
                tientongdonhangngay = tientongdonhangngay + list.get(i).getTotal();
                stt++;
            }

//            item1.put("stt", (i + 1));
//            item1.put("id_order", list.get(i).getId_order());
//            item1.put("time", list.get(i).getTime());
//            item1.put("branch_name", list.get(i).getBranch_name());
//            item1.put("total", list.get(i).getTotal());
//            item1.put("type_order", list.get(i).getType_order());
            total_revenue = total_revenue + list.get(i).getTotal();
            total_order = total_order + 1;
            if (i == list.size() - 1) {
                item1 = new HashMap<String, Object>();
                item1.put("stt", stt);
                item1.put("id_order", tongdonhang);
                item1.put("time", date);
                item1.put("branch_name", "");
                item1.put("total", tientongdonhangngay);
                item1.put("type_order", 0);
                map.add(item1);
            }
        }

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("revenue_total", total_revenue);
        item.put("date", year);
        item.put("titleDate", "Year :");
        item.put("total_order", total_order);
        item.put("atstore", atstore);
        item.put("takeaway", takeaway);
        item.put("online", online);
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
            JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_year.jrxml", reportLocation + "/report/test_report_year.jasper");

            File fis = new File(reportLocation + "/report/test_report_year.jasper");
            if (!fis.exists()) {
                JasperCompileManager.compileReportToFile(reportLocation + "/report/test_report_year.jrxml", reportLocation + "/report/test_report_year.jasper");
            }
            file = new FileInputStream(reportLocation + "/report/test_report_year.jasper");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(file);

            // fill it
            JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(map);
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
