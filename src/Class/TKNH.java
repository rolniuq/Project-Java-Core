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
public class TKNH {
    private String maTK;
    private String maTKNN;
    private String maNH;
    private float tien;

    public String getMaTK() {
        return maTK;
    }

    public String getMaTKNN() {
        return maTKNN;
    }

    public String getMaNH() {
        return maNH;
    }

    public float getTien() {
        return tien;
    }

    public TKNH() {
    }

    public TKNH(String maTK, String maTKNN, String tenNganHang, float tien) {
        this.maTK = maTK;
        this.maTKNN = maTKNN;
        this.maNH = tenNganHang;
        this.tien = tien;
    }
}