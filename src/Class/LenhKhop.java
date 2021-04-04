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
public class LenhKhop {
    private String maLenh;
    private Timestamp ngayGioKhop;
    private float giaKhop;
    private int soLgKhop;

    public String getMaLenh() {
        return maLenh;
    }

    public void setMaLenh(String maLenh) {
        this.maLenh = maLenh;
    }

    public Timestamp getNgayGioKhop() {
        return ngayGioKhop;
    }

    public void setNgayGioKhop(Timestamp ngayGioKhop) {
        this.ngayGioKhop = ngayGioKhop;
    }

    public float getGiaKhop() {
        return giaKhop;
    }

    public void setGiaKhop(float giaKhop) {
        this.giaKhop = giaKhop;
    }

    public int getSoLgKhop() {
        return soLgKhop;
    }

    public void setSoLgKhop(int soLgKhop) {
        this.soLgKhop = soLgKhop;
    }

    
    
    public LenhKhop() {
    }

    public LenhKhop(String maLenh, Timestamp ngayGioKhop, float giaKhop, int soLgKhop) {
        this.maLenh = maLenh;
        this.ngayGioKhop = ngayGioKhop;
        this.giaKhop = giaKhop;
        this.soLgKhop = soLgKhop;
    }
    
}
