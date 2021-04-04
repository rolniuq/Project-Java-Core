/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author NHT
 */
public class CoPhieu {
    private String maCP;
    private String maSan;
    private String tenCT;
    private String diaChi;
    private String SDT;
    private String fax;
    private String diachiWed;
    private String email;
    private int soLg;
    

    public String getMaCP() {
        return maCP;
    }

    public String getMaSan() {
        return maSan;
    }

    public String getTenCT() {
        return tenCT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public String getFax() {
        return fax;
    }

    public String getDiachiWed() {
        return diachiWed;
    }

    public String getEmail() {
        return email;
    }

    public int getSoLg() {
        return soLg;
    }

    public void setMaCP(String maCP) {
        this.maCP = maCP;
    }

    public void setMaSan(String maSan) {
        this.maSan = maSan;
    }

    public void setTenCT(String tenCT) {
        this.tenCT = tenCT;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setDiachiWed(String diachiWed) {
        this.diachiWed = diachiWed;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSoLg(int soLg) {
        this.soLg = soLg;
    }

    
    
    public CoPhieu() {
    }

    public CoPhieu(String maCP, String maSan, String tenCT, String diaChi, String SDT, String fax, String diachiWed, String email, int soLg) {
        this.maCP = maCP;
        this.maSan = maSan;
        this.tenCT = tenCT;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.fax = fax;
        this.diachiWed = diachiWed;
        this.email = email;
        this.soLg = soLg;
    }
    
    
}
