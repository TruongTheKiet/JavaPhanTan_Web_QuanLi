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
public class ChiPhi implements Serializable{
    public int stt;
    public String time;
    public int chiphi;

    public ChiPhi(int stt, String time, int chiphi) {
        this.stt = stt;
        this.time = time;
        this.chiphi = chiphi;
    }

    public ChiPhi() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getChiphi() {
        return chiphi;
    }

    public void setChiphi(int chiphi) {
        this.chiphi = chiphi;
    }
    
}
