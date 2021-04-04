/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Date;

/**
 *
 * @author NHT
 */
public class NhaDauTu {
    
    private String maTK;
    private String ho;
    private String ten;
    private Date ngaySinh;
    private String noiSinh;
    private String phai;
    private String diaChi;
    private String email;
    private String SDT;
    private String CMND;
    private Date ngayCap;
    private String noiCap;
    private String trNgNc;
    private String MMGD;
    private String MKDL;

    NhaDauTu(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getMaTK() {
        return maTK;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public String getPhai() {
        return phai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getEmail() {
        return email;
    }

    public String getSDT() {
        return SDT;
    }

    public String getCMND() {
        return CMND;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public String getTrNgNc() {
        return trNgNc;
    }

    public String getMMGD() {
        return MMGD;
    }

    public String getMKDL() {
        return MKDL;
    }

    public NhaDauTu() {
    }

    public NhaDauTu(String maTK, String ho, String ten, Date ngaySinh, String noiSinh, String phai, String diaChi, String email, String SDT, String CMND, Date ngayCap, String noiCap, String trNgNc, String MMGD, String MKDL) {
        this.maTK = maTK;
        this.ho = ho;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.phai = phai;
        this.diaChi = diaChi;
        this.email = email;
        this.SDT = SDT;
        this.CMND = CMND;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
        this.trNgNc = trNgNc;
        this.MMGD = MMGD;
        this.MKDL = MKDL;
    }
    
    
}
