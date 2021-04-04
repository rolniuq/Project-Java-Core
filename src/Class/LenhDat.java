/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;
import java.sql.Timestamp;
/**
 *
 * @author NHT
 */
public class LenhDat {
    private String maLenh;
    private String maTKNH;
    private String maCP;
    private String loaiGD;
    private int slDat;
    private Timestamp ngayGioDat;
    private String maLoaiLenh;
    private String maTrangThai;
    private float giaDat;

    public String getMaLenh() {
        return maLenh;
    }

    public void setMaLenh(String maLenh) {
        this.maLenh = maLenh;
    }

    public String getMaTKNH() {
        return maTKNH;
    }

    public void setMaTKNH(String maTKNH) {
        this.maTKNH = maTKNH;
    }

    public String getMaCP() {
        return maCP;
    }

    public void setMaCP(String maCP) {
        this.maCP = maCP;
    }

    public String getLoaiGD() {
        return loaiGD;
    }

    public void setLoaiGD(String loaiGD) {
        this.loaiGD = loaiGD;
    }

    public int getSlDat() {
        return slDat;
    }

    public void setSlDat(int slDat) {
        this.slDat = slDat;
    }

    public Timestamp getNgayGioDat() {
        return ngayGioDat;
    }

    public void setNgayGioDat(Timestamp ngayGioDat) {
        this.ngayGioDat = ngayGioDat;
    }

    public String getMaLoaiLenh() {
        return maLoaiLenh;
    }

    public void setMaLoaiLenh(String maLoaiLenh) {
        this.maLoaiLenh = maLoaiLenh;
    }

    public String getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(String maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public float getGiaDat() {
        return giaDat;
    }

    public void setGiaDat(float giaDat) {
        this.giaDat = giaDat;
    }
    
    
    
    public LenhDat() {
    }

    public LenhDat(String maLenh, String maTKNH, String maCP, String loaiGD, int slDat, Timestamp ngayGioDat, String maLoaiLenh, String maTrangThai, float giaDat) {
        this.maLenh = maLenh;
        this.maTKNH = maTKNH;
        this.maCP = maCP;
        this.loaiGD = loaiGD;
        this.slDat = slDat;
        this.ngayGioDat = ngayGioDat;
        this.maLoaiLenh = maLoaiLenh;
        this.maTrangThai = maTrangThai;
        this.giaDat = giaDat;
    }
    
}
