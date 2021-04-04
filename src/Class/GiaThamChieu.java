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
public class GiaThamChieu {
    private String maCP;
    private Date ngayGD;
    private float giaTC;

    public String getMaCP() {
        return maCP;
    }

    public Date getNgayGD() {
        return ngayGD;
    }

    public float getGiaTC() {
        return giaTC;
    }

    public GiaThamChieu() {
    }

    public GiaThamChieu(String maCP, Date ngayGD, float giaTC) {
        this.maCP = maCP;
        this.ngayGD = ngayGD;
        this.giaTC = giaTC;
    }

    public void setMaCP(String maCP) {
        this.maCP = maCP;
    }

    public void setGiaTC(float giaTC) {
        this.giaTC = giaTC;
    }

   
    
    
}
