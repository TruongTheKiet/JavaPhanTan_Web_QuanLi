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
public class MonAnThang implements Serializable{
    public int stt;
    public String tenchinhanh;
    public String tenmonan;
    public int soluong;

    public MonAnThang(int stt, String tenchinhanh, String tenmonan, int soluong) {
        this.stt = stt;
        this.tenchinhanh = tenchinhanh;
        this.tenmonan = tenmonan;
        this.soluong = soluong;
    }

    public MonAnThang() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenchinhanh() {
        return tenchinhanh;
    }

    public void setTenchinhanh(String tenchinhanh) {
        this.tenchinhanh = tenchinhanh;
    }

    public String getTenmonan() {
        return tenmonan;
    }

    public void setTenmonan(String tenmonan) {
        this.tenmonan = tenmonan;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    
}
