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
public class Menu implements Serializable{
    public int ID;
    public int ID_ChiNhanh;
    public int ID_MonAn;

    public Menu(int ID, int ID_ChiNhanh, int ID_MonAn) {
        this.ID = ID;
        this.ID_ChiNhanh = ID_ChiNhanh;
        this.ID_MonAn = ID_MonAn;
    }

    
    public Menu() {
    }

    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID_ChiNhanh() {
        return ID_ChiNhanh;
    }

    public void setID_ChiNhanh(int ID_ChiNhanh) {
        this.ID_ChiNhanh = ID_ChiNhanh;
    }

    public int getID_MonAn() {
        return ID_MonAn;
    }

    public void setID_MonAn(int ID_MonAn) {
        this.ID_MonAn = ID_MonAn;
    }
    
    
}
