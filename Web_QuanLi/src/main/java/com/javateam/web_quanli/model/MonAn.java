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
public class MonAn implements Serializable{
   
    public int id;
    public String ten;
    public int gia;
    public String hinhAnh;
    public String thongTin;
    public int id_DanhMuc;

    public MonAn(int id, String ten, int gia, String hinhAnh, String thongTin, int id_DanhMuc) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.thongTin = thongTin;
        this.id_DanhMuc = id_DanhMuc;
    }

    
    public MonAn() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getThongTin() {
        return thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }

    public int getId_DanhMuc() {
        return id_DanhMuc;
    }

    public void setId_DanhMuc(int id_DanhMuc) {
        this.id_DanhMuc = id_DanhMuc;
    }
    
}
