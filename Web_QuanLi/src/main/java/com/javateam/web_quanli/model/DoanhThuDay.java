/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javateam.web_quanli.model;

import java.io.Serializable;

/**
 *
 * @author TheKiet
 */
public class DoanhThuDay implements Serializable{
    public int stt;
    public int id_order;
    public String time;
    public String branch_name;
    public int total;
    
    public int type_order;

    public int getType_order() {
        return type_order;
    }

    public void setType_order(int type_order) {
        this.type_order = type_order;
    }
    
    public DoanhThuDay(int stt, int id_order, String time, String branch_name, int total, int type_order) {
        this.stt = stt;
        this.id_order = id_order;
        this.time = time;
        this.branch_name = branch_name;
        this.total = total;
        this.type_order = type_order;
    }


    
    public DoanhThuDay() {
    }

    
    public DoanhThuDay(int stt, int id_order, String time, String branch_name, int total) {
        this.stt = stt;
        this.id_order = id_order;
        this.time = time;
        this.branch_name = branch_name;
        this.total = total;
    }

    
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    
    

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
