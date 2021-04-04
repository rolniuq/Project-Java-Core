/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Class.KetNoiSQL;
import Class.LenhDat;
import Class.ThucHienGD;
import static Frame.FrMain.jPanelMQLGD;
import static Frame.FrMain.maGD;
import static Frame.FrMain.soLgMaGD;
import Panel.PanelDSLenh;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author NHT
 */
public class FrAddLD extends javax.swing.JFrame {
    private String maCPMua[];
    private String maCPBan[];
    private String maTKNH[];
    private Connection ketNoi = KetNoiSQL.layKetNoi();
    int k;
    float giaSan=0;
    float giaTran=0;
    /**
     * Creates new form QuanLiNDT
     * @param maTK
     */
    public void layTKNH(String maTK)
    {
        String sql = "select COUNT(MaTKNH) from TKNH WHERE MaTK='"+maTK+"'";
         try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               maTKNH=new String[rs.getInt(1)];
            }
           
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int i=0; 
        sql = "select MaTKNH from TKNH WHERE MaTK='"+maTK+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               maTKNH[i++]=rs.getString(1);
            }
           
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrMain.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    }
  
    public FrAddLD() {
        initComponents();
        
        
    }
    
    public FrAddLD(String maTK) {
        initComponents();
        layTKNH(maTK);
        layCoPhieuMua();
        layCoPhieuBan();
        jTextFieldML.setText("M"+maGD[soLgMaGD-1]);
        jComboBoxTKNH.setModel(new DefaultComboBoxModel<>(maTKNH));
        jComboBoxMaCP.setModel(new javax.swing.DefaultComboBoxModel<>(maCPMua));
       
    }
    
    public FrAddLD(LenhDat ld, String maTK , int k) {
        initComponents(); 
        this.k =  k;
        if ("K1P".equals(ld.getMaTrangThai())) {
            updateLenhDatKhop1P(ld);
        } else if ("CK".equals(ld.getMaTrangThai())) {
            updateLenhDatChoKhop(ld);
        }
        
        
        layTKNH(maTK);
        layCoPhieuMua();
        layCoPhieuBan();
        jTextFieldML.setText("M" + maGD[soLgMaGD - 1]);
        jComboBoxTKNH.setModel(new DefaultComboBoxModel<>(maTKNH));
        jComboBoxMaCP.setModel(new javax.swing.DefaultComboBoxModel<>(maCPMua));
        jTextFieldGiaDat.setText(String.valueOf(ld.getGiaDat()));
        jTextFieldSLDat.setText(String.valueOf(ld.getSlDat()));
        jComboBoxTKNH.setSelectedItem(ld.getMaTKNH());
        jComboBoxMaCP.setSelectedItem(ld.getMaCP());
        
        
       
    }
    
    
    public void updateLenhDat1(String maLenh,int sLuong  , float price ,String maCP )
    {
        String sql = " UPDATE LenhDat SET SLDat='"+sLuong+"' ,  GiaDat='"+price+"' , MaCP='"+maCP+"' WHERE MaLenh='" + maLenh + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void layCoPhieuMua()
    {
        int soLg=0;
        String sql = "SELECT COUNT(MaCP) FROM CoPhieu ";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                  soLg=rs.getInt(1);
             }
           
    
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        maCPMua =new String[soLg];
       
        int i=0;
        sql = "SELECT MaCP FROM CoPhieu ";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
              maCPMua[i++]=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public void layCoPhieuBan()
    {
        int soLg=0;
        String sql = "SELECT COUNT(MaCP) FROM SoHuu WHERE MaTK =(SELECT MaTK FROM TKNH WHERE MaTKNH='"+ maTKNH[0]+"')";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
             if (rs.next()) {
                  soLg=rs.getInt(1);
             }
           
    
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        maCPBan =new String[soLg];
        int i=0;
        sql = "SELECT MaCP FROM SoHuu WHERE MaTK= (SELECT MaTK FROM TKNH WHERE MaTKNH='"+ maTKNH[0]+"')";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
               maCPBan[i++]=rs.getString(1);
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
       // System.out.println(maCPBan[0]);
    }
    
    public void addLenhDat(LenhDat ld) {
        String sql = "INSERT INTO LenhDat VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, ld.getMaLenh());
            ps.setString(2, ld.getMaTKNH());
            ps.setString(3, ld.getMaCP());
            ps.setString(4, ld.getLoaiGD());
            ps.setInt(5, ld.getSlDat());
            ps.setTimestamp(6, ld.getNgayGioDat());
            ps.setString(7, ld.getMaLoaiLenh());
            ps.setString(8, "CK");
            ps.setFloat(9, ld.getGiaDat());
            ps.executeUpdate();
             
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateLenhDatChoKhop(LenhDat ld) {
        String sql = " UPDATE LenhDat SET MaCP=?,LoaiGD=?,SLDat=?,NgayGioDat=?,MaLoaiLenh=?,GiaDat=? WHERE MaTK='" + ld.getMaLenh()+ "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, ld.getMaCP());
            ps.setString(2, ld.getLoaiGD());
            ps.setTimestamp(3, ld.getNgayGioDat());
            ps.setInt(4, ld.getSlDat());
            ps.setString(5, ld.getLoaiGD());
            ps.setFloat(6, ld.getGiaDat());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLenhDatKhop1P(LenhDat ld) {
        int soLgKhop = 0;        
        String sql = " SELECT COUNT(SoLgKhop)FROM(SELECT * FROM LenhKhop WHERE MaLenh ='" + ld.getMaLenh()+ "') AS LK INNER JOIN LenhDat ON LK.MaLenh = LenhDat.MaLenh GROUP BY MaCP";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            soLgKhop=rs.getInt(1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sql = " UPDATE LenhDat SET SLDat="+soLgKhop+ " ,MaTrangThai='KH' WHERE MaLenh='" + ld.getMaLenh()+ "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if("Mua".equals(ld.getLoaiGD()))
        {
            sql = "UPDATE TKNH SET SoTien=SoTien+" + (ld.getGiaDat()*(ld.getSlDat() - soLgKhop)) + " WHERE MaTKNH = '+"+ld.getMaTKNH()+"'" ;
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ps.executeUpdate();
                
            } catch (SQLException ex) {
                Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            sql = " UPDATE SoHuu SET SoLg=SoLg+" + (ld.getSlDat() - soLgKhop) + "WHERE MaTK =(SELECT MaTK FROM TKNH WHERE MaTKNH='"+ld.getMaTKNH()+"') AND MaCP= '" +ld.getMaCP()+ "'" ;
            try {
                PreparedStatement ps = ketNoi.prepareStatement(sql);
                ps.executeUpdate();
               
            } catch (SQLException ex) {
                Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void addLenhKhop(String maLenh,float giaKhop,int solgKhop,int nano)
    {
        java.util.Date dateNow=new java.util.Date();
        Timestamp date=new Timestamp(dateNow.getYear()-2000+100, dateNow.getMonth()-1, dateNow.getDate(), dateNow.getHours(), dateNow.getMinutes()+1, dateNow.getSeconds(),nano );
        
        String sql = "INSERT INTO LenhKhop VALUES (?,?,?,?)" ;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, maLenh);
            ps.setTimestamp(1,date);
            ps.setFloat(1, giaKhop);
            ps.setInt(1, solgKhop);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateLenhDat(String maLenh,String maTrangThai)
    {
        String sql = " UPDATE LenhDat SET MaTrangThai='"+maTrangThai+"' WHERE MaTK='" + maLenh + "'";
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
      
    public int laySoLgCPHienCo(String maTK,String maCP)
    {
        int temp=0;
        String sql = "SELECT SoLg FROM SoHuu WHERE MaTK='"+ maTK+ "' AND MaCP='"+maCP+"'";
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
    
    public int laySoLgCoPhieuDangBan(String maTK,String maCP)
    {
        int temp=Integer.valueOf(jTextFieldSLDat.getText());
        String sql = "SELECT COUNT(SLDat)\n" +
        "FROM (SELECT *FROM LenhDat WHERE MaCP='"+maCP+"+' AND CONVERT(DATE,NgayGioDat) = CONVERT(DATE,GETDATE()) AND LoaiGD='Ban' AND (MaTrangThai!='CHK' OR MaTrangThai!='DH')) LD \n" +
        "INNER JOIN (SELECT *FROM TKNH WHERE MaTK= '"+maTK+"') TK \n" +
        "ON LD.MaTKNH = TK.MaTKNH ";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                temp=temp+rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
    
    public float laySoTienDangMua(String maTKNH)
    {
        float soTienDat = Integer.valueOf(jTextFieldSLDat.getText())*Float.valueOf(jTextFieldGiaDat.getText());
        String sql = "SELECT COUNT(SLDat*GiaDat) FROM LenhDat WHERE MaTKNH='"+maTKNH+"' AND LoaiGD='Mua' AND CONVERT(DATE,NgayGioDat) = CONVERT(DATE,GETDATE()) AND (MaTrangThai!='CHK' OR MaTrangThai!='DH') " ;
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                soTienDat=soTienDat+rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soTienDat;
    }
    
    public float laySoTienHienCo(String maTKNH)
    {
        float soTienDangCo = 0;
        String sql = "SELECT SoTien FROM TKNH WHERE MaTKNH='"+maTKNH+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                soTienDangCo=rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soTienDangCo;
    }
           
    public boolean kiemTraLenhDat()
    {
        String maTKNH=jComboBoxTKNH.getItemAt((jComboBoxTKNH.getSelectedIndex()));
        String maTK=layMaTK(maTKNH);
        String maCP=jComboBoxMaCP.getItemAt(jComboBoxMaCP.getSelectedIndex());
        
        if (jRadioButtonMua.isSelected()) {
            if (laySoTienHienCo(maTKNH) <= laySoTienDangMua(maTKNH)) {
                JOptionPane.showMessageDialog(this, "Số Tiền Của Bạn Không Đủ Vui Lòng Nạp Thêm Tiền Hoặc Chọn TKNH Khác");
                return false;
            }
        } else {
            if (laySoLgCPHienCo(maTK, maCP) < laySoLgCoPhieuDangBan(maTK, maCP)) {
                JOptionPane.showMessageDialog(this, "Số Lượng Cổ Phiếu Không Đủ");
                return false;
            }
        }
        return true;
    }
    
    public void layGiaCP(String maCP)
    {
        float giaTC = 0;
        float bienDoGia = 0;
        String sql = "DECLARE @ma varchar(50) SET @ma='"+maCP+"' SELECT GiaTC,BienDoGia FROM (SELECT *FROM GiaThamChieu WHERE MaCP = @ma AND NgayGD=(SELECT NGAYMAX = MAX(NgayGD) FROM GiaThamChieu WHERE MaCP = @ma AND NgayGD <= CONVERT(DATE,GETDATE()))) TC INNER JOIN (SELECT *FROM CoPhieu WHERE MaCP = @ma) CP ON TC.MaCP=CP.MaCP INNER JOIN (SELECT *FROM SanGiaoDich ) SGD ON CP.MaSan = SGD.MaSan";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                giaTC=rs.getFloat(1);
                bienDoGia=rs.getFloat(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FrAddLD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        giaSan=giaTC-(giaTC*bienDoGia);
        giaTran=giaTC+(giaTC*bienDoGia);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupLGD = new javax.swing.ButtonGroup();
        buttonGroupLL = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldML = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldSLDat = new javax.swing.JTextField();
        jTextFieldGiaDat = new javax.swing.JTextField();
        jButtonXN = new javax.swing.JButton();
        jButtonCa = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxNgay = new javax.swing.JComboBox<String>();
        jComboBoxThang = new javax.swing.JComboBox<String>();
        jComboBoxNam = new javax.swing.JComboBox<String>();
        jComboBoxGio = new javax.swing.JComboBox<String>();
        jComboBoxPhut = new javax.swing.JComboBox<String>();
        jComboBoxMaCP = new javax.swing.JComboBox<String>();
        jRadioButtonMua = new javax.swing.JRadioButton();
        jRadioButtonBan = new javax.swing.JRadioButton();
        jRadioButtonATC = new javax.swing.JRadioButton();
        jRadioButtonATO = new javax.swing.JRadioButton();
        jRadioButtonLO = new javax.swing.JRadioButton();
        jComboBoxTKNH = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Ngày Đặt:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Mã TKNH:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Mã Loại Lệnh:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Loại Giao Dịch:");

        jTextFieldML.setEditable(false);
        jTextFieldML.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldML.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldML.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldML.setPreferredSize(new java.awt.Dimension(0, 25));
        jTextFieldML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMLActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Giờ Đặt:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Giá Đặt(VND):");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Mã Lệnh:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Mã Cổ Phiếu:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Số Lượng Đặt:");

        jTextFieldSLDat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldSLDat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldSLDat.setPreferredSize(new java.awt.Dimension(0, 25));

        jTextFieldGiaDat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldGiaDat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextFieldGiaDat.setPreferredSize(new java.awt.Dimension(0, 25));

        jButtonXN.setBackground(new java.awt.Color(0, 0, 0));
        jButtonXN.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXN.setText("Xác Nhận");
        jButtonXN.setBorder(null);
        jButtonXN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXNActionPerformed(evt);
            }
        });

        jButtonCa.setBackground(new java.awt.Color(0, 0, 0));
        jButtonCa.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCa.setText("Cancel");
        jButtonCa.setBorder(null);
        jButtonCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ĐẶT LỆNH");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(427, 427, 427))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel16)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jComboBoxNgay.setMinimumSize(new java.awt.Dimension(80, 30));
        jComboBoxNgay.setPreferredSize(new java.awt.Dimension(31, 30));
        String date[]=new String[31];
        java.util.Date dateNow=new java.util.Date();

        for(int i=0;i<31;i++)
        {
            if(i<9)
            {
                date[i]="0"+String.valueOf(i+1);
            }
            else
            date[i]=String.valueOf(i+1);
        }
        jComboBoxNgay.setModel(new javax.swing.DefaultComboBoxModel<>(date));
        jComboBoxNgay.setSelectedIndex(dateNow.getDate()-1);

        jComboBoxThang.setPreferredSize(new java.awt.Dimension(80, 30));
        String month[]=new String[12];
        for(int i=0;i<12;i++)
        {
            if(i<9)
            {
                month[i]="0"+String.valueOf(i+1);
            }
            else
            month[i]=String.valueOf(i+1);
        }
        jComboBoxThang.setModel(new javax.swing.DefaultComboBoxModel<>(month));
        jComboBoxThang.setSelectedIndex(dateNow.getMonth());
        jComboBoxThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxThangActionPerformed(evt);
            }
        });

        jComboBoxNam.setPreferredSize(new java.awt.Dimension(80, 30));
        String year[]=new String[200];
        for(int i=0;i<200;i++)
        {
            year[i]=String.valueOf(1900+i);
        }
        jComboBoxNam.setModel(new javax.swing.DefaultComboBoxModel<>(year));
        jComboBoxNam.setSelectedItem(String.valueOf(2000-100+dateNow.getYear()));
        jComboBoxNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNamActionPerformed(evt);
            }
        });

        jComboBoxGio.setPreferredSize(new java.awt.Dimension(80, 30));
        String hours[]=new String[24];
        for(int i=0;i<24;i++)
        {
            if(i<10)
            {
                hours[i]="0"+String.valueOf(i);
            }
            else
            hours[i]=String.valueOf(i);
        }
        jComboBoxGio.setModel(new javax.swing.DefaultComboBoxModel<>(hours));
        jComboBoxGio.setSelectedIndex(dateNow.getHours());

        jComboBoxPhut.setMinimumSize(new java.awt.Dimension(80, 30));
        String minute[]=new String[60];
        for(int i=0;i<60;i++)
        {
            if(i<10)
            {
                minute[i]="0"+String.valueOf(i);
            }
            else
            minute[i]=String.valueOf(i);
        }
        jComboBoxPhut.setModel(new javax.swing.DefaultComboBoxModel<>(minute));
        jComboBoxPhut.setSelectedIndex(dateNow.getMinutes());

        jComboBoxMaCP.setPreferredSize(new java.awt.Dimension(100, 30));
        //jComboBoxMaCP.setModel(new javax.swing.DefaultComboBoxModel<>(maCPMua));
        jComboBoxMaCP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMaCPActionPerformed(evt);
            }
        });

        buttonGroupLGD.add(jRadioButtonMua);
        jRadioButtonMua.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonMua.setSelected(true);
        jRadioButtonMua.setText("Mua");
        jRadioButtonMua.setPreferredSize(new java.awt.Dimension(53, 30));
        jRadioButtonMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMuaActionPerformed(evt);
            }
        });

        buttonGroupLGD.add(jRadioButtonBan);
        jRadioButtonBan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonBan.setText("Bán");
        jRadioButtonBan.setPreferredSize(new java.awt.Dimension(49, 30));
        jRadioButtonBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBanActionPerformed(evt);
            }
        });

        buttonGroupLL.add(jRadioButtonATC);
        jRadioButtonATC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonATC.setText("ATC");
        jRadioButtonATC.setPreferredSize(new java.awt.Dimension(53, 30));
        jRadioButtonATC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonATCActionPerformed(evt);
            }
        });

        buttonGroupLL.add(jRadioButtonATO);
        jRadioButtonATO.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonATO.setText("ATO");
        jRadioButtonATO.setPreferredSize(new java.awt.Dimension(51, 30));
        jRadioButtonATO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonATOActionPerformed(evt);
            }
        });

        buttonGroupLL.add(jRadioButtonLO);
        jRadioButtonLO.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonLO.setSelected(true);
        jRadioButtonLO.setText("LO");
        jRadioButtonLO.setPreferredSize(new java.awt.Dimension(43, 30));
        jRadioButtonLO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonLOActionPerformed(evt);
            }
        });

        jComboBoxTKNH.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBoxTKNH.setPreferredSize(new java.awt.Dimension(86, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addGap(145, 145, 145)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxMaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 570, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldML, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jButtonXN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)
                                .addComponent(jButtonCa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldSLDat, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxNgay, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxGio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxThang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxPhut, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonMua, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonATC, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonATO, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonLO, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldGiaDat, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTKNH, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldML, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxTKNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxMaCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jRadioButtonMua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldSLDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPhut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButtonATC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonLO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonATO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldGiaDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCaActionPerformed

    private void jButtonXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXNActionPerformed
        // TODO add your handling code here:
        if("".equals(jTextFieldGiaDat.getText())||"".equals(jTextFieldSLDat.getText()))
        {
            JOptionPane.showMessageDialog(this, "Vui Lòng Điền Đầy Đủ Thông Tin");
            return;
        }
        
        String reNumber="^[0-9]{1,6}$";
        String reFloat="\\-?\\d+\\.\\d+";
        if (!jTextFieldGiaDat.getText().matches(reFloat) && !jTextFieldGiaDat.getText().matches(reNumber)) {
            JOptionPane.showMessageDialog(this, "Giá Đặt Phải Là Kiểu Float");
            return;
        }
        if (!jTextFieldSLDat.getText().matches(reNumber)) {
            JOptionPane.showMessageDialog(this, "Số Lượng Phải Là Kiểu Số");
            return;
        }
        if(Integer.valueOf(jTextFieldSLDat.getText())<=0)
        {
            JOptionPane.showMessageDialog(this, "Số Lượng Phải > 0");
            return;
        }
        if(Float.valueOf(jTextFieldGiaDat.getText())<=0)
        {
            JOptionPane.showMessageDialog(this, "Giá Đặt Phải > 0");
            return;
        }
        
        if(!kiemTraLenhDat())
        {
            return;
        }
        String maLL = null,loaiGD = null;
        if(jRadioButtonMua.isSelected())
        {
            loaiGD="Mua";
        }
        else
        {
            loaiGD="Ban";
        }
        
        if(jRadioButtonATO.isSelected())
        {
            maLL="ATO";
        }
        else if(jRadioButtonATC.isSelected())
        {
             maLL="ATC";
        }
        else
        {
             maLL="LO";
        }
        Timestamp a=new Timestamp(Integer.valueOf(jComboBoxNam.getItemAt(jComboBoxNam.getSelectedIndex()))-2000+100, Integer.valueOf(jComboBoxThang.getItemAt(jComboBoxThang.getSelectedIndex()))-1, Integer.valueOf(jComboBoxNgay.getItemAt(jComboBoxNgay.getSelectedIndex())), Integer.valueOf(jComboBoxGio.getItemAt(jComboBoxGio.getSelectedIndex())), Integer.valueOf(jComboBoxPhut.getItemAt(jComboBoxPhut.getSelectedIndex())), 0, 0);
       
        addLenhDat(new LenhDat(jTextFieldML.getText(),jComboBoxTKNH.getItemAt(jComboBoxTKNH.getSelectedIndex()),jComboBoxMaCP.getItemAt(jComboBoxMaCP.getSelectedIndex()),loaiGD,Integer.valueOf(jTextFieldSLDat.getText()),a,maLL,"CK",Integer.valueOf(jTextFieldGiaDat.getText())));
        JOptionPane.showMessageDialog(this, "Succes");
        soLgMaGD--;
        
        this.setVisible(false);
        new ThucHienGD().thucHienGD();
        
        jPanelMQLGD.removeAll();
        jPanelMQLGD.add(new PanelDSLenh());
        jPanelMQLGD.repaint();
        jPanelMQLGD.revalidate();
    }//GEN-LAST:event_jButtonXNActionPerformed

    private void jComboBoxThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxThangActionPerformed
        // TODO add your handling code here:
         String date[];
        int numberDate = 0;
        int tempM = tempM = Integer.valueOf(jComboBoxThang.getItemAt(jComboBoxThang.getSelectedIndex()));
        switch (tempM) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                date = new String[31];
                numberDate = 31;
                break;
            case 2:
                int tempY = Integer.valueOf(jComboBoxNam.getItemAt(jComboBoxNam.getSelectedIndex()));
                if ((tempY % 4 == 0 && tempY % 100 != 0) || tempY % 400 == 0) {
                    date = new String[29];
                    numberDate = 29;
                } else {
                    date = new String[28];
                    numberDate = 28;
                }
                break;
            default:
                date = new String[30];
                numberDate = 30;
                break;
        }
        for (int i = 0; i < numberDate; i++) {
            if (i < 10) {
                date[i] = "0" + String.valueOf(i + 1);
            } else {
                date[i] = String.valueOf(i + 1);
            }
        }
        jComboBoxNgay.setModel(new javax.swing.DefaultComboBoxModel<>(date));
    }//GEN-LAST:event_jComboBoxThangActionPerformed

    private void jComboBoxNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNamActionPerformed
        // TODO add your handling code here:
        int tempM = tempM = Integer.valueOf(jComboBoxThang.getItemAt(jComboBoxThang.getSelectedIndex()));
        if (tempM == 2) {
            String date[];
            int numberDate = 0;
            int tempY = Integer.valueOf(jComboBoxNam.getItemAt(jComboBoxNam.getSelectedIndex()));
            if ((tempY % 4 == 0 && tempY % 100 != 0) || tempY % 400 == 0) {
                date = new String[29];
                numberDate = 29;
            } else {
                date = new String[28];
                numberDate = 28;
            }
            for (int i = 0; i < numberDate; i++) {
                if (i < 10) {
                    date[i] = "0" + String.valueOf(i + 1);
                } else {
                    date[i] = String.valueOf(i + 1);
                }
            }
            jComboBoxNgay.setModel(new javax.swing.DefaultComboBoxModel<>(date));
        }
    }//GEN-LAST:event_jComboBoxNamActionPerformed
            
    private void jRadioButtonATOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonATOActionPerformed
        // TODO add your handling code here:
        layGiaCP(jComboBoxMaCP.getItemAt(jComboBoxMaCP.getSelectedIndex()));
        if(jRadioButtonATO.isSelected())
        {
            jTextFieldGiaDat.setEnabled(false);
            jTextFieldGiaDat.setBackground(new  Color(204,204,204));
            if(jRadioButtonMua.isSelected())
            {
                jTextFieldGiaDat.setText(String.valueOf(giaTran));
            }
            else 
            {
                jTextFieldGiaDat.setText(String.valueOf(giaSan));
            }
        }
    }//GEN-LAST:event_jRadioButtonATOActionPerformed

    private void jRadioButtonATCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonATCActionPerformed
        // TODO add your handling code here:
        layGiaCP(jComboBoxMaCP.getItemAt(jComboBoxMaCP.getSelectedIndex()));
        if(jRadioButtonATC.isSelected())
        {
            jTextFieldGiaDat.setEnabled(false);
            jTextFieldGiaDat.setBackground(new  Color(204,204,204));
             if(jRadioButtonMua.isSelected())
            {
                jTextFieldGiaDat.setText(String.valueOf(giaTran));
            }
            else 
            {
                jTextFieldGiaDat.setText(String.valueOf(giaSan));
            }
        }
    }//GEN-LAST:event_jRadioButtonATCActionPerformed

    private void jRadioButtonLOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonLOActionPerformed
        // TODO add your handling code here:
        if(jRadioButtonLO.isSelected())
        {
            jTextFieldGiaDat.setEnabled(true);
            jTextFieldGiaDat.setBackground(Color.WHITE);
            jTextFieldGiaDat.setText("");
        }
    }//GEN-LAST:event_jRadioButtonLOActionPerformed

    private void jRadioButtonMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMuaActionPerformed
        // TODO add your handling code here:
        if(jRadioButtonMua.isSelected())
        {
             jTextFieldML.setText("M"+maGD[soLgMaGD-1]);
             jComboBoxMaCP.setModel(new javax.swing.DefaultComboBoxModel<>(maCPMua));
        }
    }//GEN-LAST:event_jRadioButtonMuaActionPerformed

    private void jRadioButtonBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBanActionPerformed
        // TODO add your handling code here:
        if(jRadioButtonBan.isSelected())
        {
            jTextFieldML.setText("B"+maGD[soLgMaGD-1]);
            jComboBoxMaCP.setModel(new javax.swing.DefaultComboBoxModel<>(maCPBan));
        }
    }//GEN-LAST:event_jRadioButtonBanActionPerformed

    private void jComboBoxMaCPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMaCPActionPerformed
        // TODO add your handling code here:
        layGiaCP(jComboBoxMaCP.getItemAt(jComboBoxMaCP.getSelectedIndex()));
        if (jRadioButtonMua.isSelected()) {
            jTextFieldGiaDat.setText(String.valueOf(giaTran));
        } else {
            jTextFieldGiaDat.setText(String.valueOf(giaSan));
        }
    }//GEN-LAST:event_jComboBoxMaCPActionPerformed

    private void jTextFieldMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMLActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrAddLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrAddLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrAddLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrAddLD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrAddLD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupLGD;
    private javax.swing.ButtonGroup buttonGroupLL;
    private javax.swing.JButton jButtonCa;
    private javax.swing.JButton jButtonXN;
    private javax.swing.JComboBox<String> jComboBoxGio;
    private javax.swing.JComboBox<String> jComboBoxMaCP;
    private javax.swing.JComboBox<String> jComboBoxNam;
    private javax.swing.JComboBox<String> jComboBoxNgay;
    private javax.swing.JComboBox<String> jComboBoxPhut;
    private javax.swing.JComboBox<String> jComboBoxTKNH;
    private javax.swing.JComboBox<String> jComboBoxThang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonATC;
    private javax.swing.JRadioButton jRadioButtonATO;
    private javax.swing.JRadioButton jRadioButtonBan;
    private javax.swing.JRadioButton jRadioButtonLO;
    private javax.swing.JRadioButton jRadioButtonMua;
    private javax.swing.JTextField jTextFieldGiaDat;
    private javax.swing.JTextField jTextFieldML;
    private javax.swing.JTextField jTextFieldSLDat;
    // End of variables declaration//GEN-END:variables
}
