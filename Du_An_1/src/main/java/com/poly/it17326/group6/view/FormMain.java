/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.github.sarxos.webcam.Webcam;

import com.poly.it17326.group6.view.FormNhanVien;
import com.poly.it17326.group6.domainmodel.TaiKhoan;
import com.poly.it17326.group6.service.ChiTietSPService;
import com.poly.it17326.group6.service.TaiKhoanService;
import com.poly.it17326.group6.service.impl.ChiTietSPServiceImpl;
import com.poly.it17326.group6.service.impl.TaiKhoanServiceImpl;
import java.awt.Color;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author 123
 */
public class FormMain extends javax.swing.JFrame {

    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();
private WebcamPanel panel = null;
    private Webcam webcam = null;
    /**
     * Creates new form DemoBanHang
     */
    public FormMain() {
        initComponents();
        tpBanHang.removeAll();
        setLocationRelativeTo(null);
        loadIcon();

    }
      
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void loadIcon() {
        lbFormBanHang.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\banHang.png"));
        lbFormSanPham.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\box.png"));
        lbFormNhanVien.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\employee.png"));
        lbFormThongke.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\statistics.png"));
        btnDangXuat.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\door.png"));
        btnDoiMK.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\password.png"));
        btnKetThuc.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\cancel.png"));
        btnKhachHang.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\learning.png"));
        btnThongKe.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\bar-chart.png"));
        lbLogo.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\logo.png"));
    }

    public void setTk(String email) {
        int idTK = 0;
        for (TaiKhoan tk : taiKhoanService.getCheck(email)) {
            lbTenNV.setText(tk.getHoTenNV());
            lbChucVu.setText(tk.getChucVu().getTen());
            idTK = tk.getId();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        tpBanHang = new javax.swing.JTabbedPane();
        pnBanHang = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbFormBanHang = new javax.swing.JLabel();
        lbFormSanPham = new javax.swing.JLabel();
        lbFormNhanVien = new javax.swing.JLabel();
        lbFormThongke = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTenNV = new javax.swing.JLabel();
        lbChucVu = new javax.swing.JLabel();
        lbFormKhuyenMai1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        btnDoiMK = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu5.setText("jMenu5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý cửa hàng sữa");
        setBackground(new java.awt.Color(0, 102, 102));

        tpBanHang.setBackground(new java.awt.Color(153, 0, 0));
        tpBanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102), 2));

        pnBanHang.setAutoscrolls(true);

        javax.swing.GroupLayout pnBanHangLayout = new javax.swing.GroupLayout(pnBanHang);
        pnBanHang.setLayout(pnBanHangLayout);
        pnBanHangLayout.setHorizontalGroup(
            pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1367, Short.MAX_VALUE)
        );
        pnBanHangLayout.setVerticalGroup(
            pnBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 843, Short.MAX_VALUE)
        );

        tpBanHang.addTab("tab1", pnBanHang);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));

        lbFormBanHang.setBackground(new java.awt.Color(204, 0, 153));
        lbFormBanHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbFormBanHang.setForeground(new java.awt.Color(255, 255, 255));
        lbFormBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFormBanHang.setText("Bán Hàng");
        lbFormBanHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        lbFormBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFormBanHangMouseClicked(evt);
            }
        });

        lbFormSanPham.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbFormSanPham.setForeground(new java.awt.Color(255, 255, 255));
        lbFormSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFormSanPham.setText("Sản Phẩm");
        lbFormSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        lbFormSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFormSanPhamMouseClicked(evt);
            }
        });

        lbFormNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbFormNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lbFormNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFormNhanVien.setText("Nhân viên");
        lbFormNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        lbFormNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFormNhanVienMouseClicked(evt);
            }
        });

        lbFormThongke.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbFormThongke.setForeground(new java.awt.Color(255, 255, 255));
        lbFormThongke.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFormThongke.setText("Thống kê");
        lbFormThongke.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        lbFormThongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFormThongkeMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Chức vụ:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhân viên:");

        lbTenNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbTenNV.setForeground(new java.awt.Color(255, 255, 255));

        lbChucVu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbChucVu.setForeground(new java.awt.Color(255, 255, 255));

        lbFormKhuyenMai1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbFormKhuyenMai1.setForeground(new java.awt.Color(255, 255, 255));
        lbFormKhuyenMai1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFormKhuyenMai1.setText("Khuyến mãi");
        lbFormKhuyenMai1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        lbFormKhuyenMai1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbFormKhuyenMai1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbFormBanHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFormSanPham, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFormNhanVien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbFormThongke, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(lbTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lbChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbFormKhuyenMai1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(lbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(lbFormBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(lbFormSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(lbFormNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbFormThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(628, Short.MAX_VALUE)
                    .addComponent(lbFormKhuyenMai1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(164, 164, 164)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbFormBanHang, lbFormNhanVien, lbFormSanPham, lbFormThongke});

        jToolBar1.setBackground(new java.awt.Color(0, 102, 102));
        jToolBar1.setRollover(true);
        jToolBar1.add(jLabel2);

        btnDangXuat.setBackground(new java.awt.Color(0, 102, 102));
        btnDangXuat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangXuat);

        btnDoiMK.setBackground(new java.awt.Color(0, 102, 102));
        btnDoiMK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDoiMK.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMK.setText("Đổi mật khẩu");
        btnDoiMK.setFocusable(false);
        btnDoiMK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDoiMK.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnDoiMK.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDoiMK);

        btnKetThuc.setBackground(new java.awt.Color(0, 102, 102));
        btnKetThuc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnKetThuc.setForeground(new java.awt.Color(255, 255, 255));
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKetThuc);

        btnKhachHang.setBackground(new java.awt.Color(0, 102, 102));
        btnKhachHang.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnKhachHang.setForeground(new java.awt.Color(255, 255, 255));
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setFocusable(false);
        btnKhachHang.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKhachHang.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnKhachHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKhachHang);

        btnThongKe.setBackground(new java.awt.Color(0, 102, 102));
        btnThongKe.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setText("Thống kê");
        btnThongKe.setFocusable(false);
        btnThongKe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnThongKe.setMargin(new java.awt.Insets(2, 20, 2, 20));
        btnThongKe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
        });
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        jToolBar1.add(btnThongKe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 21, Short.MAX_VALUE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tpBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbFormBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFormBanHangMouseClicked
        // TODO add your handling code here:
        lbFormBanHang.setForeground(Color.GREEN);
        lbFormNhanVien.setForeground(Color.white);
        lbFormThongke.setForeground(Color.WHITE);
        lbFormSanPham.setForeground(Color.WHITE);
        tpBanHang.removeAll();
        
        FormBanHang fbh = new FormBanHang();
        fbh.setTk(lbTenNV.getText());
        pnBanHang = fbh;
        tpBanHang.addTab("Quản lý bán hàng", pnBanHang);
        tpBanHang.setSelectedComponent(pnBanHang);
        webcam.close();
     
        
       
           
            
    }//GEN-LAST:event_lbFormBanHangMouseClicked

    private void lbFormSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFormSanPhamMouseClicked
        // TODO add your handling code here:
        lbFormBanHang.setForeground(Color.WHITE);
        lbFormNhanVien.setForeground(Color.white);
        lbFormThongke.setForeground(Color.WHITE);
        lbFormSanPham.setForeground(Color.GREEN);
        tpBanHang.removeAll();

        pnBanHang = new FormSanPham();
        tpBanHang.addTab("Quản lý sản phẩm", pnBanHang);
        tpBanHang.setSelectedComponent(pnBanHang);
    }//GEN-LAST:event_lbFormSanPhamMouseClicked

    private void lbFormNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFormNhanVienMouseClicked
        // TODO add your handling code here:
        lbFormBanHang.setForeground(Color.WHITE);
        lbFormThongke.setForeground(Color.WHITE);
        lbFormSanPham.setForeground(Color.white);
        lbFormNhanVien.setForeground(Color.GREEN);
        tpBanHang.removeAll();
        pnBanHang = new FormNhanVien();
        tpBanHang.addTab("Quản lý sản phẩm", pnBanHang);
        tpBanHang.setSelectedComponent(pnBanHang);

    }//GEN-LAST:event_lbFormNhanVienMouseClicked

    private void lbFormThongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFormThongkeMouseClicked
        // TODO add your handling code here:
        lbFormBanHang.setForeground(Color.WHITE);
        lbFormSanPham.setForeground(Color.WHITE);
        lbFormNhanVien.setForeground(Color.white);
        lbFormThongke.setForeground(Color.GREEN);
        tpBanHang.removeAll();
        pnBanHang = new FormKhuyenMai();
        tpBanHang.addTab("Quản lý khuyến mãi", pnBanHang);
        tpBanHang.setSelectedComponent(pnBanHang);
    }//GEN-LAST:event_lbFormThongkeMouseClicked

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        // TODO add your handling code here:
        FormChangePassword fChangeP = new FormChangePassword();
        fChangeP.setVisible(true);
    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        FormDangNhap fdn = new FormDangNhap();
        fdn.setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
       lbFormBanHang.setForeground(Color.WHITE);
        lbFormSanPham.setForeground(Color.WHITE);
        lbFormNhanVien.setForeground(Color.white);
        lbFormThongke.setForeground(Color.GREEN);
        tpBanHang.removeAll();
        pnBanHang = new NewJPanel();
        tpBanHang.addTab("Quản lý khuyến mãi", pnBanHang);
        tpBanHang.setSelectedComponent(pnBanHang);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        
    }//GEN-LAST:event_btnThongKeMouseClicked

    private void lbFormKhuyenMai1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbFormKhuyenMai1MouseClicked
     
    }//GEN-LAST:event_lbFormKhuyenMai1MouseClicked

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
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbChucVu;
    private javax.swing.JLabel lbFormBanHang;
    private javax.swing.JLabel lbFormKhuyenMai1;
    private javax.swing.JLabel lbFormNhanVien;
    private javax.swing.JLabel lbFormSanPham;
    private javax.swing.JLabel lbFormThongke;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbTenNV;
    private javax.swing.JPanel pnBanHang;
    private javax.swing.JTabbedPane tpBanHang;
    // End of variables declaration//GEN-END:variables
}
