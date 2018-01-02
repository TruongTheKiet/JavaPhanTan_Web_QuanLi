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
public class DonHang implements Serializable{
    public String time;
    public int donhang;

    public DonHang(String time, int donhang) {
        this.time = time;
        this.donhang = donhang;
    }

    public DonHang() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDonhang() {
        return donhang;
    }

    public void setDonhang(int donhang) {
        this.donhang = donhang;
    }

    
    
}
