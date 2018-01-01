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
public class HoatDong implements Serializable{
    public int id;
    public String tenkh;
    public int tongtien;
    public int soluongdh;

    public HoatDong() {
    }

    public HoatDong(int id, String tenkh, int tongtien, int soluongdh) {
        this.id = id;
        this.tenkh = tenkh;
        this.tongtien = tongtien;
        this.soluongdh = soluongdh;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getSoluongdh() {
        return soluongdh;
    }

    public void setSoluongdh(int soluongdh) {
        this.soluongdh = soluongdh;
    }
    
}
