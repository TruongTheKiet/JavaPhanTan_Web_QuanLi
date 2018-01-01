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
public class KhachHangMoi implements Serializable{
    public String thang;
    public int soluong;

    public KhachHangMoi(String thang, int soluong) {
        this.thang = thang;
        this.soluong = soluong;
    }

    
    public KhachHangMoi() {
    }

    
    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
    
}
