package com.uit.minhman.gdg02.Model;

import java.io.Serializable;

/**
 * Created by Minh Man on 10/29/2016.
 */

public class Bus_Info implements Serializable {
    private String masotuyen;
    private String tentuyen;
    private String luotdi;
    private String luotve;
    private String loaihinhhoatdong;
    private String culy;
    private String sochuyentrongngay;
    private String thoigianchuyen;

    public String getGiancach() {
        return giancach;
    }

    public void setGiancach(String giancach) {
        this.giancach = giancach;
    }

    public String getMasotuyen() {
        return masotuyen;
    }

    public void setMasotuyen(String masotuyen) {
        this.masotuyen = masotuyen;
    }

    public String getTentuyen() {
        return tentuyen;
    }

    public void setTentuyen(String tentuyen) {
        this.tentuyen = tentuyen;
    }

    public String getLuotdi() {
        return luotdi;
    }

    public void setLuotdi(String luotdi) {
        this.luotdi = luotdi;
    }

    public String getLuotve() {
        return luotve;
    }

    public void setLuotve(String luotve) {
        this.luotve = luotve;
    }

    public String getLoaihinhhoatdong() {
        return loaihinhhoatdong;
    }

    public void setLoaihinhhoatdong(String loaihinhhoatdong) {
        this.loaihinhhoatdong = loaihinhhoatdong;
    }

    public String getCuly() {
        return culy;
    }

    public void setCuly(String culy) {
        this.culy = culy;
    }

    public String getSochuyentrongngay() {
        return sochuyentrongngay;
    }

    public void setSochuyentrongngay(String sochuyentrongngay) {
        this.sochuyentrongngay = sochuyentrongngay;
    }

    public String getThoigianchuyen() {
        return thoigianchuyen;
    }

    public void setThoigianchuyen(String thoigianchuyen) {
        this.thoigianchuyen = thoigianchuyen;
    }

    public String getThoigianhoatdong() {
        return thoigianhoatdong;
    }

    public void setThoigianhoatdong(String thoigianhoatdong) {
        this.thoigianhoatdong = thoigianhoatdong;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    private String giancach;
    private String thoigianhoatdong;
    private String loaixe;

    public Bus_Info() {
        this.masotuyen = "";
        this.tentuyen = "";
        this.loaixe = "";
        this.luotdi = "";
        this.luotve = "";
        this.loaihinhhoatdong = "Buýt có trợ giá";
        this.culy = "";
        this.sochuyentrongngay = "";
        this.thoigianchuyen = "";
        this.giancach = "";
        this.thoigianhoatdong = "";
    }

    public Bus_Info(String masotuyen, String tentuyen, String luotdi, String luotve, String loaihinhhoatdong, String culy, String sochuyentrongngay, String thoigianchuyen, String giancach, String thoigianhoatdong, String loaixe) {
        this.masotuyen = masotuyen;
        this.tentuyen = tentuyen;
        this.loaixe = loaixe;
        this.luotdi = luotdi;
        this.luotve = luotve;
        this.loaihinhhoatdong = loaihinhhoatdong;
        this.culy = culy;
        this.sochuyentrongngay = sochuyentrongngay;
        this.thoigianchuyen = thoigianchuyen;
        this.giancach = giancach;
        this.thoigianhoatdong = thoigianhoatdong;
    }

    public String GetInfo(){
        return
                "Mã số tuyến: "+masotuyen
                        +"\nTên tuyến: "+tentuyen
                        +"\nLượt đi: "+ luotdi
                        +"\nLượt về: "+luotve
                        +"\nLoại hình hoạt động: "+loaihinhhoatdong
                        +"\nCự ly: "+culy
                        +"\nSố chuyến trong ngày: "+sochuyentrongngay
                        +"\nThời gian chuyến: "+thoigianchuyen
                        +"\nGiãn cách: "+giancach
                        +"\nThời gian hoạt động: "+thoigianhoatdong
                        +"\nLoại xe: "+loaixe;
    }
}