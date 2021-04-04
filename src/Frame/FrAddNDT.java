/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import Class.NhaDauTu;
import Panel.PanelNDT;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static Frame.FrMain.jPanelMQLNDT;
import static Frame.FrMain.ketNoi;
import java.sql.ResultSet;


/**
 *
 * @author NHT
 */
public class FrAddNDT extends javax.swing.JFrame {

    public boolean kt;

    public FrAddNDT(NhaDauTu ndt) {
        initComponents();
        kt = false;
        this.jComboBoxNamCap.setSelectedItem(ndt.getNgayCap().getYear());
        this.jComboBoxNamSinh.setSelectedItem(ndt.getNgaySinh().getYear());
        this.jComboBoxNgayCap.setSelectedItem(ndt.getNgayCap().getDate());
        this.jComboBoxNgaySinh.setSelectedItem(ndt.getNgaySinh().getDate());
        this.jComboBoxThangCap.setSelectedItem(ndt.getNgayCap().getMonth());
        this.jComboBoxThangSinh.setSelectedItem(ndt.getNgaySinh().getMonth());

        if ("Nam".equals(ndt.getPhai())) {
            this.jRadioButtonNam.isSelected();
        } else {
            this.jRadioButtonNu.isSelected();
        }

        if ("Trong Nước".equals(ndt.getTrNgNc())) {
            this.jRadioButtonTrong.isSelected();
        } else {
            this.jRadioButtonNgoai.isSelected();
        }

        System.out.println(ndt.getMaTK());
        this.jTextFieldCMND.setText(ndt.getCMND());
        this.jTextFieldDiaChi.setText(ndt.getDiaChi());
        this.jTextFieldEmail.setText(ndt.getEmail());
        this.jTextFieldHo.setText(ndt.getHo());
        this.jTextFieldMKDT.setText(ndt.getMKDL());
        this.jTextFieldMMGD.setText(ndt.getMMGD());
        this.jTextFieldMTK.setText(ndt.getMaTK());
        this.jTextFieldMTK.setEnabled(false);
        this.jTextFieldNoiCap.setText(ndt.getNoiCap());
        this.jTextFieldNoiSinh.setText(ndt.getNoiSinh());
        this.jTextFieldSDT.setText(ndt.getSDT());
        this.jTextFieldTen.setText(ndt.getTen());
    }

    /**
     * Creates new form QuanLiNDT
     */
    public FrAddNDT() {
        initComponents();
        kt = true;
    }
    
   
    public void addNhaDauTu(NhaDauTu ndt) {
        
        String sql = "INSERT INTO NhaDauTu VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, ndt.getMaTK());
            ps.setString(2, ndt.getHo());
            ps.setString(3, ndt.getTen());
            ps.setDate(4, new java.sql.Date(ndt.getNgaySinh().getTime()));
            ps.setString(5, ndt.getNoiSinh());
            ps.setString(6, ndt.getPhai());
            ps.setString(7, ndt.getDiaChi());
            ps.setString(8, ndt.getEmail());
            ps.setString(9, ndt.getSDT());
            ps.setString(10, ndt.getCMND());
            ps.setDate(11, ndt.getNgayCap());
            ps.setString(12, ndt.getNoiCap());
            ps.setString(13, ndt.getTrNgNc());
            ps.setString(14, ndt.getMMGD());
            ps.setString(15, ndt.getMKDL());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddNDT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateNhaDauTu(NhaDauTu ndt) {
        
        String sql = " UPDATE NhaDauTu SET MaTK=?,Ho=?,Ten=?,NgaySinh=?,NoiSinh=?,Phai=?,DiaChi=?,Email=?,SDT=?,CMND=?,NgayCap=?,NoiCap=?,TrNgNc=?,MMGD=?,MKDL=? WHERE MaTK='" + ndt.getMaTK() + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ps.setString(1, ndt.getMaTK());
            ps.setString(2, ndt.getHo());
            ps.setString(3, ndt.getTen());
            ps.setDate(4, new java.sql.Date(ndt.getNgaySinh().getTime()));
            ps.setString(5, ndt.getNoiSinh());
            ps.setString(6, ndt.getPhai());
            ps.setString(7, ndt.getDiaChi());
            ps.setString(8, ndt.getEmail());
            ps.setString(9, ndt.getSDT());
            ps.setString(10, ndt.getCMND());
            ps.setDate(11, new java.sql.Date(ndt.getNgayCap().getTime()));
            ps.setString(12, ndt.getNoiCap());
            ps.setString(13, ndt.getTrNgNc());
            ps.setString(14, ndt.getMMGD());
            ps.setString(15, ndt.getMKDL());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FrAddNDT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int testMaNDT() {
       
        int tonTai = 0;
        String sql = "select * from NhaDauTu where MaTK ='" + jTextFieldMTK.getText() + "'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai = 1;
            }
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PanelNDT.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        sql = "select * from NhaDauTu where CMND='" +jTextFieldCMND.getText()+"' OR Email ='"+jTextFieldEmail.getText() +"' OR SDT='"+jTextFieldSDT.getText()+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai = 2;
            }
            rs.close();
            ps.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(PanelNDT.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tonTai;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupGioiTinh = new javax.swing.ButtonGroup();
        buttonGroupTrgNgoaiNc = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldHo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldMTK = new javax.swing.JTextField();
        jTextFieldNoiCap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldTen = new javax.swing.JTextField();
        jTextFieldNoiSinh = new javax.swing.JTextField();
        jRadioButtonNam = new javax.swing.JRadioButton();
        jRadioButtonNu = new javax.swing.JRadioButton();
        jTextFieldDiaChi = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jTextFieldSDT = new javax.swing.JTextField();
        jTextFieldMKDT = new javax.swing.JTextField();
        jRadioButtonTrong = new javax.swing.JRadioButton();
        jRadioButtonNgoai = new javax.swing.JRadioButton();
        jTextFieldCMND = new javax.swing.JTextField();
        jComboBoxNgayCap = new javax.swing.JComboBox<String>();
        jComboBoxThangCap = new javax.swing.JComboBox<String>();
        jComboBoxNamCap = new javax.swing.JComboBox<String>();
        jComboBoxNgaySinh = new javax.swing.JComboBox<String>();
        jComboBoxThangSinh = new javax.swing.JComboBox<String>();
        jComboBoxNamSinh = new javax.swing.JComboBox<String>();
        jTextFieldMMGD = new javax.swing.JTextField();
        jButtonXN = new javax.swing.JButton();
        jButtonCa = new javax.swing.JButton();
        lb1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setForeground(new java.awt.Color(255, 0, 51));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setText("Mât Khẩu Đặt Lệnh:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setText("Email:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Họ Và Tên Lót:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setText("Mật Mã Giao Dịch:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Phái:");

        jTextFieldHo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setText("Nơi Sinh:");

        jTextFieldMTK.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextFieldNoiCap.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Địa Chỉ:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("SĐT:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Trong/Ngoài Nước:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setText("Ngày Cấp:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Mã Tài Khoản:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Tên:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("CMND/Hộ Chiếu:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Nơi Cấp:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Ngày Sinh:");

        jTextFieldTen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextFieldNoiSinh.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldNoiSinh.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldNoiSinhCaretUpdate(evt);
            }
        });

        buttonGroupGioiTinh.add(jRadioButtonNam);
        jRadioButtonNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButtonNam.setSelected(true);
        jRadioButtonNam.setText("Nam");

        buttonGroupGioiTinh.add(jRadioButtonNu);
        jRadioButtonNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButtonNu.setText("Nữ");

        jTextFieldDiaChi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextFieldSDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTextFieldMKDT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldMKDT.setText("123456789");

        buttonGroupTrgNgoaiNc.add(jRadioButtonTrong);
        jRadioButtonTrong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButtonTrong.setSelected(true);
        jRadioButtonTrong.setText("Trong Nước");

        buttonGroupTrgNgoaiNc.add(jRadioButtonNgoai);
        jRadioButtonNgoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButtonNgoai.setText("Ngoài Nước");

        jTextFieldCMND.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        String date[]=new String[31];
        for(int i=0;i<31;i++)
        {
            if(i<9)
            {
                date[i]="0"+String.valueOf(i+1);
            }
            else
            date[i]=String.valueOf(i+1);
        }
        jComboBoxNgayCap.setModel(new javax.swing.DefaultComboBoxModel<>(date));

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
        jComboBoxThangCap.setModel(new javax.swing.DefaultComboBoxModel<>(month));
        jComboBoxThangCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxThangCapActionPerformed(evt);
            }
        });

        String year[]=new String[200];
        for(int i=0;i<200;i++)
        {
            year[i]=String.valueOf(1900+i);
        }
        jComboBoxNamCap.setModel(new javax.swing.DefaultComboBoxModel<>(year));
        jComboBoxNamCap.setSelectedIndex(0);
        jComboBoxNamCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNamCapActionPerformed(evt);
            }
        });

        jComboBoxNgaySinh.setModel(new javax.swing.DefaultComboBoxModel<>(date));

        jComboBoxThangSinh.setModel(new javax.swing.DefaultComboBoxModel<>(month));
        jComboBoxThangSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxThangSinhActionPerformed(evt);
            }
        });

        jComboBoxNamSinh.setModel(new javax.swing.DefaultComboBoxModel<>(year));
        jComboBoxNamSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNamSinhActionPerformed(evt);
            }
        });

        jTextFieldMMGD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldMMGD.setText("123456789");

        jButtonXN.setBackground(new java.awt.Color(0, 0, 0));
        jButtonXN.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXN.setText("Xác Nhận");
        jButtonXN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXNActionPerformed(evt);
            }
        });

        jButtonCa.setBackground(new java.awt.Color(0, 0, 0));
        jButtonCa.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCa.setText("Cancel");
        jButtonCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaActionPerformed(evt);
            }
        });

        lb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldMTK, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldHo, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(346, 346, 346)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonXN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(113, 113, 113)
                                        .addComponent(jButtonCa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNoiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonNam, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButtonNu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCMND, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxNgayCap, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxThangCap, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxNamCap, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldNoiCap, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButtonTrong)
                                .addGap(31, 31, 31)
                                .addComponent(jRadioButtonNgoai))
                            .addComponent(jTextFieldMMGD, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldMKDT, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel15))
                .addGap(100, 100, 100))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldMTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldHo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxThangSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNoiSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButtonNam)
                    .addComponent(jRadioButtonNu))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldCMND, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxNgayCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxThangCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxNamCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldNoiCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jRadioButtonTrong)
                    .addComponent(jRadioButtonNgoai))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextFieldMMGD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldMKDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonXN)
                    .addComponent(jButtonCa))
                .addGap(28, 28, 28))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1010, 830));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("ĐĂNG KÍ NHÀ ĐẦU TƯ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(324, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(313, 313, 313))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(48, 48, 48))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 160));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxThangCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxThangCapActionPerformed
        // TODO add your handling code here:
        String date[];
        int numberDate = 0;
        int tempM = tempM = Integer.valueOf(jComboBoxThangCap.getItemAt(jComboBoxThangCap.getSelectedIndex()));
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
                int tempY = Integer.valueOf(jComboBoxNamCap.getItemAt(jComboBoxNamCap.getSelectedIndex()));
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
        jComboBoxNgayCap.setModel(new javax.swing.DefaultComboBoxModel<>(date));
    }//GEN-LAST:event_jComboBoxThangCapActionPerformed

    private void jComboBoxNamCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNamCapActionPerformed
        // TODO add your handling code here:

        int tempM = tempM = Integer.valueOf(jComboBoxThangCap.getItemAt(jComboBoxThangCap.getSelectedIndex()));
        if (tempM == 2) {
            String date[];
            int numberDate = 0;
            int tempY = Integer.valueOf(jComboBoxNamCap.getItemAt(jComboBoxNamCap.getSelectedIndex()));
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
            jComboBoxNgayCap.setModel(new javax.swing.DefaultComboBoxModel<>(date));
        }
    }//GEN-LAST:event_jComboBoxNamCapActionPerformed

    private void jComboBoxThangSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxThangSinhActionPerformed
        // TODO add your handling code here:
        String date[];
        int numberDate = 0;
        int tempM = tempM = Integer.valueOf(jComboBoxThangSinh.getItemAt(jComboBoxThangSinh.getSelectedIndex()));
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
                int tempY = Integer.valueOf(jComboBoxNamSinh.getItemAt(jComboBoxNamSinh.getSelectedIndex()));
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
        jComboBoxNgaySinh.setModel(new javax.swing.DefaultComboBoxModel<>(date));
    }//GEN-LAST:event_jComboBoxThangSinhActionPerformed

    private void jComboBoxNamSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNamSinhActionPerformed
        // TODO add your handling code here:
        int tempM = tempM = Integer.valueOf(jComboBoxThangSinh.getItemAt(jComboBoxThangSinh.getSelectedIndex()));
        if (tempM == 2) {
            String date[];
            int numberDate = 0;
            int tempY = Integer.valueOf(jComboBoxNamSinh.getItemAt(jComboBoxNamSinh.getSelectedIndex()));
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
            jComboBoxNgaySinh.setModel(new javax.swing.DefaultComboBoxModel<>(date));
        }
    }//GEN-LAST:event_jComboBoxNamSinhActionPerformed

    private void jButtonXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXNActionPerformed
        // TODO add your handling code here:
        //lb1.setText("");
        if("".equals(jTextFieldTen.getText())||"".equals(jTextFieldCMND.getText())||"".equals(jTextFieldDiaChi.getText())||"".equals(jTextFieldEmail.getText())||"".equals(jTextFieldHo.getText())||"".equals(jTextFieldMKDT.getText())||"".equals(jTextFieldMMGD.getText())||"".equals(jTextFieldMTK.getText())||"".equals(jTextFieldNoiCap.getText())||"".equals(jTextFieldNoiSinh.getText())||"".equals(jTextFieldSDT.getText()))
        {
            JOptionPane.showMessageDialog(this, "Vui Lòng Điền Đầy Đủ Thông Tin");
            return;
        }
        String reEmail="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        String reSDT="^(([+]{1}[0-9]{2}|0)[0-9]{9})$";
        String reCMND="[0-9]{9}";
        String reMK="[a-zA-Z0-9]{6,}";
        
        if (!jTextFieldEmail.getText().matches(reEmail)) {
            JOptionPane.showMessageDialog(this, "Email Không Đúng Đinh Dạng");
            return;
        }
        if (!jTextFieldSDT.getText().matches(reSDT)) {
            JOptionPane.showMessageDialog(this, "SĐT Không Đúng Đinh Dạng");
            return;
        }
        if (!jTextFieldCMND.getText().matches(reCMND)) {
            JOptionPane.showMessageDialog(this, "CMND Không Đúng Định Dạng");
            return;
        }
        if(kt)
        {
            if(!jTextFieldMMGD.getText().matches(reMK) || !jTextFieldMKDT.getText().matches(reMK))
            {
                JOptionPane.showMessageDialog(this, "Mật Khẩu Không Đúng Đinh Dạng");
                return;
            }

            if(testMaNDT()==1)
            {
                JOptionPane.showMessageDialog(this, "MaTK Đã Tồn Tại");
                return;
            }
            else if(testMaNDT()==2)
            {
                JOptionPane.showMessageDialog(this, "SĐT Hoặc Email Hoặc CMND Đã Tồn Tại");
                return;
            }
        }
        
        String phai;
        String trNgNc;
        Date ngaySinh = new Date(Integer.valueOf(jComboBoxNamSinh.getItemAt(jComboBoxNamSinh.getSelectedIndex()))-2000+100,Integer.valueOf(jComboBoxThangSinh.getItemAt(jComboBoxThangSinh.getSelectedIndex()))-1,Integer.valueOf(jComboBoxNgaySinh.getItemAt(jComboBoxNgaySinh.getSelectedIndex())));
        Date ngayCap = new Date(Integer.valueOf(jComboBoxNamCap.getItemAt(jComboBoxNamCap.getSelectedIndex()))-2000+100, Integer.valueOf(jComboBoxThangCap.getItemAt(jComboBoxThangCap.getSelectedIndex()))-1, Integer.valueOf(jComboBoxNgayCap.getItemAt(jComboBoxNgayCap.getSelectedIndex())));
        if (jRadioButtonNam.isSelected()) {
            phai = "Nam";
        } else {
            phai = "Nữ";
        }

        if (jRadioButtonTrong.isSelected()) {
            trNgNc = "Trong Nước";
        } else {
            trNgNc = "Ngoài Nước";
        }

       
        NhaDauTu ndt = new NhaDauTu(jTextFieldMTK.getText(), jTextFieldHo.getText(), jTextFieldTen.getText(), ngaySinh, jTextFieldNoiSinh.getText(), phai, jTextFieldDiaChi.getText(), jTextFieldEmail.getText(), jTextFieldSDT.getText(), jTextFieldCMND.getText(), ngayCap, jTextFieldNoiCap.getText(), trNgNc, jTextFieldMMGD.getText(), jTextFieldMKDT.getText());
        if (kt) {
            addNhaDauTu(ndt);
        } else {
            updateNhaDauTu(ndt);
        }

        JOptionPane.showMessageDialog(this, "SUCCES");
        //lb1.setText("Đăng kí thành công");
        this.setVisible(false);
        
        jPanelMQLNDT.removeAll();
        jPanelMQLNDT.add(new PanelNDT());
        jPanelMQLNDT.repaint();
        jPanelMQLNDT.revalidate();

    }//GEN-LAST:event_jButtonXNActionPerformed

    private void jButtonCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCaActionPerformed

    private void jTextFieldNoiSinhCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldNoiSinhCaretUpdate
        // TODO add your handling code here:
        //jTextFieldNoiSinh.setText("asd");
    }//GEN-LAST:event_jTextFieldNoiSinhCaretUpdate

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
            java.util.logging.Logger.getLogger(FrAddNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrAddNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrAddNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrAddNDT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrAddNDT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupGioiTinh;
    private javax.swing.ButtonGroup buttonGroupTrgNgoaiNc;
    private javax.swing.JButton jButtonCa;
    private javax.swing.JButton jButtonXN;
    private javax.swing.JComboBox<String> jComboBoxNamCap;
    private javax.swing.JComboBox<String> jComboBoxNamSinh;
    private javax.swing.JComboBox<String> jComboBoxNgayCap;
    private javax.swing.JComboBox<String> jComboBoxNgaySinh;
    private javax.swing.JComboBox<String> jComboBoxThangCap;
    private javax.swing.JComboBox<String> jComboBoxThangSinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JRadioButton jRadioButtonNam;
    private javax.swing.JRadioButton jRadioButtonNgoai;
    private javax.swing.JRadioButton jRadioButtonNu;
    private javax.swing.JRadioButton jRadioButtonTrong;
    private javax.swing.JTextField jTextFieldCMND;
    private javax.swing.JTextField jTextFieldDiaChi;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldHo;
    private javax.swing.JTextField jTextFieldMKDT;
    private javax.swing.JTextField jTextFieldMMGD;
    private javax.swing.JTextField jTextFieldMTK;
    private javax.swing.JTextField jTextFieldNoiCap;
    private javax.swing.JTextField jTextFieldNoiSinh;
    private javax.swing.JTextField jTextFieldSDT;
    private javax.swing.JTextField jTextFieldTen;
    private javax.swing.JLabel lb1;
    // End of variables declaration//GEN-END:variables
}
