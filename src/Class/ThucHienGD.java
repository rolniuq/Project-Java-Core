/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import Frame.FrAddLD;
import static Frame.FrMain.ketNoi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NHT
 */
class LayLenh
{
    private String maTKNH;
    private String maCP;
    private int soLgKhop;
    private Float giaKhop;
    private String loaiGD;

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

    public int getSoLgKhop() {
        return soLgKhop;
    }

    public void setSoLgKhop(int soLgKhop) {
        this.soLgKhop = soLgKhop;
    }

    public Float getGiaKhop() {
        return giaKhop;
    }

    public void setGiaKhop(Float giaKhop) {
        this.giaKhop = giaKhop;
    }

    public String getLoaiGD() {
        return loaiGD;
    }

    public void setLoaiGD(String loaiGD) {
        this.loaiGD = loaiGD;
    }
    
    
}
public class ThucHienGD {
    public void updateTienTKNH(String maTKNH, float soTien)
    {
        String sql = "UPDATE TKNH SET SoTien=SoTien +"+soTien+" WHERE MaTKNH ='"+maTKNH+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCP(String maTK,String maCP, int soLg)
    {
        String sql = "UPDATE SoHuu SET SoLg=SoLg +"+soLg+" WHERE MaTK ='"+maTK+"' AND MaCP='"+maCP+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void addLenhKhop(String maLenh,float giaKhop,int solgKhop,int nano)
    {
        java.util.Date dateNow=new java.util.Date();
        Timestamp date=new Timestamp(dateNow.getYear(), dateNow.getMonth(), dateNow.getDate(), dateNow.getHours(), dateNow.getMinutes()+1, dateNow.getSeconds(),nano );
        
        String sql = "INSERT INTO LenhKhop VALUES (?,?,?,?)" ;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLenh);
            ps.setTimestamp(2,date);
            ps.setFloat(3, giaKhop);
            ps.setInt(4, solgKhop);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLenhDat(String maLenh,String maTrangThai)
    {
        String sql = " UPDATE LenhDat SET MaTrangThai='"+maTrangThai+"' WHERE MaLenh='" + maLenh + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String layMaTK(String maTKNH)
    {
        String temp=null;
        String sql = "SELECT MaTK FROM TKNH WHERE MaTKNH='"+maTKNH+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                temp=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    public int laySoLgKhopCuaLenh(String maLenh)
    {
        int temp=0;
        String sql = "SELECT COUNT(SoLgKhop) FROM LenhKhop WHERE MaLenh='"+ maLenh+ "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                temp=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    public void updateChuaKhop()
    {
        ArrayList<String> maLenh=new ArrayList<>();
        String sql = "SELECT MaLenh FROM LenhDat WHERE CONVERT(DATE,NgayGioDat) < CONVERT(DATE,GETDATE()) AND MaTrangThai ='CK' " ;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                maLenh.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(String ml:maLenh)
        {
            sql = "UPDATE LenhDat SET MaTrangThai='CAK' WHERE MaLenh=?";
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ps.setString(1, ml);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ThucHienGD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
    public void upDateTienVaCP()
    {
        ArrayList<LayLenh> layLenh =new ArrayList<>();
        String sql = "SELECT MaTKNH,MaCP,GiaKhop,SoLgKhop,LoaiGD \n" +
                    "FROM (SELECT *FROM LenhKhop WHERE DATEADD(DAY, 2,CONVERT(DATE,NgayGioKhop)) = CONVERT(DATE,GETDATE())) LK \n" +
                    "INNER JOIN (SELECT *FROM LenhDat  WHERE DATEADD(DAY, 2,CONVERT(DATE,NgayGioDat)) = CONVERT(DATE,GETDATE())) LD\n" +
                    "ON LD.MaLenh=LK.MaLenh ";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                LayLenh a=new LayLenh();
                a.setMaTKNH(rs.getString(1));
                a.setMaCP(rs.getString(2));
                a.setGiaKhop(rs.getFloat(3));
                a.setSoLgKhop(rs.getInt(4));
                a.setLoaiGD(rs.getString(5));
                layLenh.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(LayLenh a:layLenh)
        {
            if("Mua".equals(a.getLoaiGD()))
            {
                updateTienTKNH(a.getMaTKNH(),-a.getGiaKhop()*a.getSoLgKhop());
                updateCP(layMaTK(a.getMaTKNH()), a.getMaCP(), a.getSoLgKhop());
            }
            else
            {
                updateTienTKNH(a.getMaTKNH(),a.getGiaKhop()*a.getSoLgKhop());
                updateCP(layMaTK(a.getMaTKNH()), a.getMaCP(), -a.getSoLgKhop());
            }
        }  
    }
    
    public void thucHienGD()
    {
          
        ArrayList<LenhDat> ld=new ArrayList<LenhDat>();
        String sql = "SELECT *FROM LenhDat WHERE NgayGioDat <= GETDATE() AND (MaTrangThai='CK'OR MaTrangThai='K1P') ORDER BY NgayGioDat";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                LenhDat a=new LenhDat();
                a.setMaLenh(rs.getString(1));
                a.setMaTKNH(rs.getString(2));
                a.setMaCP(rs.getString(3));
                a.setLoaiGD(rs.getString(4));
                a.setSlDat(rs.getInt(5));
                a.setNgayGioDat(rs.getTimestamp(6));
                a.setMaLoaiLenh(rs.getString(7));
                a.setMaTrangThai(rs.getString(8));
                a.setGiaDat(rs.getFloat(9));
                                
                ld.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(LenhDat a:ld)
        {
            //sql = "SELECT *FROM LenhDat WHERE LoaiGD='Ban' WHERE MaCP ORDER BY NgayGioDat,GiaDat";
            int soLg = a.getSlDat() - laySoLgKhopCuaLenh(a.getMaLenh());
            int nano = 0;
            if(soLg <=0)
            {
                continue;
            }
            
            if("Mua".equals(a.getLoaiGD()))
            {
                 sql = "SELECT * FROM LenhDat WHERE LoaiGD='Ban' AND MaCP='" + a.getMaCP() 
                         + "' AND (MaTrangThai ='CK' OR MaTrangThai ='K1P' ) AND NgayGioDat <= '"+a.getNgayGioDat()+"'"
                         + " AND GiaDat <= " + a.getGiaDat() + " ORDER BY GiaDat,NgayGioDat";
                  
            }
            else
                 sql = "SELECT * FROM LenhDat WHERE LoaiGD='Mua' AND MaCP='" + a.getMaCP() 
                         + "' AND (MaTrangThai ='CK' OR MaTrangThai ='K1P' ) AND NgayGioDat <= '"+a.getNgayGioDat()+"'"
                         + " AND GiaDat >= " + a.getGiaDat() + " ORDER BY GiaDat DESC,NgayGioDat";
                         System.out.println("asad");
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next() && soLg > 0) {
                    int soLgConLai = rs.getInt("SLDat") - laySoLgKhopCuaLenh(rs.getString("MaLenh"));  
                    String tempMaTK1=layMaTK(a.getMaTKNH());
                    String tempMaTK2=layMaTK(rs.getString("MaTKNH"));
                    if (soLgConLai > 0 && !tempMaTK1.equals(tempMaTK2)) {
                        if (soLg == soLgConLai) {

                            updateLenhDat(a.getMaLenh(), "KH");
                            updateLenhDat(rs.getString("MaLenh"), "KH");

                            addLenhKhop(a.getMaLenh(), rs.getFloat("GiaDat"), soLg, nano);
                            addLenhKhop(rs.getString("MaLenh"), rs.getFloat("GiaDat"), soLg, nano);

                            soLg = 0;
                        } else if (soLg > soLgConLai) {
                            updateLenhDat(a.getMaLenh(), "K1P");
                            updateLenhDat(rs.getString("MaLenh"), "KH");

                            addLenhKhop(a.getMaLenh(), rs.getFloat("GiaDat"), Integer.valueOf(rs.getInt("SLDat")), nano);
                            addLenhKhop(rs.getString("MaLenh"), rs.getFloat("GiaDat"), Integer.valueOf(rs.getInt("SLDat")), nano);

                            soLg = soLg - soLgConLai;
                        } else {

                            updateLenhDat(a.getMaLenh(), "KH");
                            updateLenhDat(rs.getString("MaLenh"), "K1P");

                            addLenhKhop(a.getMaLenh(), rs.getFloat("GiaDat"), soLg, nano);
                            addLenhKhop(rs.getString("MaLenh"), rs.getFloat("GiaDat"), soLg, nano);

                            soLg = 0;
                        }
                        nano++;
                    }
                }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
    public void update()
    {
        updateChuaKhop();
        thucHienGD();
        upDateTienVaCP();
    }
}
