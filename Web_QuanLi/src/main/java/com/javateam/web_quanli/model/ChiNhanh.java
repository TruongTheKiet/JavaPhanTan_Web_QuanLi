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
public class ChiNhanh implements Serializable{

    public int id;
    public String ten;
    public String diaChi;
    public String sdt;
    public String tinhThanh;
    public int soLuongBan;
    public int id_QuanLy;

    public ChiNhanh(int id, String ten, String diaChi, String sdt, String tinhThanh, int soLuongBan, int id_QuanLy) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.tinhThanh = tinhThanh;
        this.soLuongBan = soLuongBan;
        this.id_QuanLy = id_QuanLy;
    }
    
    public ChiNhanh() {
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public int getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public int getId_QuanLy() {
        return id_QuanLy;
    }

    public void setId_QuanLy(int id_QuanLy) {
        this.id_QuanLy = id_QuanLy;
    }

}
