/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Frame;

import Class.LenhDat;
import static Frame.FrMain.jPanelMQLGD;
import static Frame.FrMain.ketNoi;
import Panel.PanelDSLenh;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NHT
 */
public class FrDNGD extends javax.swing.JFrame {

    /** Creates new form FrDNGD */
    private LenhDat ld;
    private String maTK;
    private int kt=1;
    public FrDNGD() {
        initComponents();
    }

    public boolean ktTK()
    {
        String pass = "";
        char[] temp =jPasswordPass.getPassword();
        for(int i=0;i<temp.length;i++)
        {
           pass=pass+temp[i];
        }
        
       
        System.out.println(pass);
        boolean kt=false;
        String sql = "select * from NhaDauTu WHERE MaTK='"+jTextFieldMaTK.getText()+"'AND MKDL='"+pass+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
               kt=true;
            }
           
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kt;
    }
    
    public void layMaTK()
    {
        String sql = "select MaTK from TKNH WHERE MaTKNH='"+ld.getMaTKNH()+"'";
        try {
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maTK=rs.getString(1);
            }
           
            rs.close();
            ps.close();
           
        } catch (SQLException ex) {
            Logger.getLogger(FrMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public FrDNGD(LenhDat ld,int kt)
    {
         
         initComponents();
         this.ld=new LenhDat();
         this.ld=ld;
         this.kt=kt;
        System.out.println("kt " + this.kt);
        System.out.println("id " + this.ld.getMaTKNH());
          layMaTK();
          System.out.println("matk" + maTK);
         jTextFieldMaTK.setText(maTK);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonCanel = new javax.swing.JButton();
        jButtonXN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPasswordPass = new javax.swing.JPasswordField();
        jTextFieldMaTK = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ĐẶT LỆNH");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(149, 149, 149)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel3)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 160));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButtonCanel.setText("Cancel");
        jButtonCanel.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonCanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCanelActionPerformed(evt);
            }
        });

        jButtonXN.setText("Xác Nhận");
        jButtonXN.setPreferredSize(new java.awt.Dimension(100, 25));
        jButtonXN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXNActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_lock_24px_1.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/icons8_home_24px.png"))); // NOI18N

        jPasswordPass.setText("123456");
        jPasswordPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPasswordPass.setPreferredSize(new java.awt.Dimension(120, 30));

        jTextFieldMaTK.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextFieldMaTK.setPreferredSize(new java.awt.Dimension(0, 30));
        jTextFieldMaTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMaTKActionPerformed(evt);
            }
        });

        jLabel4.setText("TàiKhoản");

        jLabel6.setText("MậtKhẩuĐặtLệnh");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 70, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(58, 58, 58)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldMaTK, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(58, 58, 58)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jPasswordPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonXN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGap(78, 78, 78))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldMaTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPasswordPass, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonXN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 156, 430, 330));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCanelActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCanelActionPerformed

    private void jButtonXNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXNActionPerformed
        // TODO add your handling code here:
        if(!ktTK())
        {
            JOptionPane.showMessageDialog(this, "Tài Khoản Hoặc Mật Khẩu Không Chinh Xác");
            return;
        }
        
        if(kt==1)
        {
            new FrAddLD(jTextFieldMaTK.getText()).setVisible(true);
        }
        else if(kt==2)
        {
            new FrAddLD(ld,maTK,5).setVisible(true);
        }
        else {
            
            if (ld.getMaLenh() == null) {
                JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Lệnh");
                return;
            }
            String sql;
            if ("CK".equals(ld.getMaTrangThai())) {
                sql = " UPDATE LenhDat SET MaTrangThai ='DH' WHERE MaLenh='" + ld.getMaLenh() + "'";
                try {
                    PreparedStatement ps = ketNoi.prepareStatement(sql);
                    ps.executeUpdate();
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FrDNGD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("CAK".equals(ld.getMaTrangThai()) || "DH".equals(ld.getMaTrangThai()) || "KH".equals(ld.getMaTrangThai())) {
                JOptionPane.showMessageDialog(this, "Lệnh Này Không Huỷ Đc");
            } else {
                int soLgKhop = 0;
                Timestamp ngayGioDat;
                String maCP = null;
                String loaiGD = null;
                sql = " SELECT MaCP,COUNT(SoLgKhop),NgayGioDat,loaiGD FROM(SELECT * FROM LenhKhop WHERE MaLenh ='" + ld.getMaLenh() + "') AS LK INNER JOIN LenhDat ON LK.MaLenh = LenhDat.MaLenh GROUP BY MaCP,NgayGioDat,loaiGD ";
                try {
                    PreparedStatement ps = ketNoi.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next())
                    {
                        maCP = rs.getString(1);
                        soLgKhop = rs.getInt(2);
                        ngayGioDat = rs.getTimestamp(3);
                        loaiGD = rs.getString(4);
                    }
                  
                   
                    rs.close();;
                     ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FrDNGD.class.getName()).log(Level.SEVERE, null, ex);
                }

                sql = " UPDATE LenhDat SET SLDat=" + soLgKhop + " ,MaTrangThai='KH' WHERE MaLenh='" + ld.getMaLenh() + "'";
                try {
                    PreparedStatement ps = ketNoi.prepareStatement(sql);
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(FrDNGD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(this, "SUCCES");
            
            jPanelMQLGD.removeAll();
            jPanelMQLGD.add(new PanelDSLenh());
            jPanelMQLGD.repaint();
            jPanelMQLGD.revalidate();
        }
        
        this.setVisible(false);
    }//GEN-LAST:event_jButtonXNActionPerformed

    private void jTextFieldMaTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMaTKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMaTKActionPerformed

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
            java.util.logging.Logger.getLogger(FrDNGD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrDNGD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrDNGD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrDNGD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrDNGD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCanel;
    private javax.swing.JButton jButtonXN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordPass;
    private javax.swing.JTextField jTextFieldMaTK;
    // End of variables declaration//GEN-END:variables

}
