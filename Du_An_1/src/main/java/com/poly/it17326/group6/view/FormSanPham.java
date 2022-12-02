/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.poly.it17326.group6.view;

import com.poly.it17326.group6.domainmodel.Anh;
import com.poly.it17326.group6.domainmodel.ChiTietSP;
import com.poly.it17326.group6.domainmodel.LoaiSP;
import com.poly.it17326.group6.domainmodel.NSX;
import com.poly.it17326.group6.domainmodel.SanPham;
import com.poly.it17326.group6.repository.ChiTietSpRepository;
import com.poly.it17326.group6.response.ChiTietSpResponse;
import com.poly.it17326.group6.response.ChiTietSpResponse_2;
import com.poly.it17326.group6.response.SanPhamResponse;
import com.poly.it17326.group6.service.ChiTietSPService;
import com.poly.it17326.group6.service.impl.ChiTietSPServiceImpl;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 123
 */
public class FormSanPham extends javax.swing.JPanel {

    private ChiTietSPService chiTietSPService = new ChiTietSPServiceImpl();
    private ChiTietSpRepository chiTietSpRepository = new ChiTietSpRepository();
    List<ChiTietSpResponse_2> listHD = chiTietSPService.getAllFSP();

    /**
     * Creates new form FormSanPham
     */
    public FormSanPham() {
        initComponents();
        loadCB();
        loadIcon();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void loadSP(List<ChiTietSpResponse_2> listS) {
        DefaultTableModel model = (DefaultTableModel) tbSanPham.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã SP", "Tên SP", "Loại SP", "HSD", "Số lượng tồn", "Đơn giá"});
        int i = 1;
        for (ChiTietSpResponse_2 chiTietSpResponse : listS) {
            model.addRow(new Object[]{chiTietSpResponse.getMa(), chiTietSpResponse.getTen(), chiTietSpResponse.getLoaiSP(), doiNgay(chiTietSpResponse.getHsd()), chiTietSpResponse.getSoLuongTon(), chiTietSpResponse.getDonGia()});
            System.out.println(chiTietSpResponse);
        }

    }

    private String doiNgay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        String ngayTao = format.format(d);
        return ngayTao;
    }

    private void loadCbLSP() {
        cbLoaiSP.removeAllItems();
        cbTimKiemLSP.removeAllItems();
        cbTimKiemLSP.addItem("Loại sản phẩm");
        for (LoaiSP lsp : chiTietSPService.getListLSp()) {
            cbLoaiSP.addItem(lsp.getTen());
            cbTimKiemLSP.addItem(lsp.getTen());
        }
    }

    private void loadCbNsx() {
        cbNsx.removeAllItems();
        for (NSX nsx : chiTietSPService.getListNsx()) {
            cbNsx.addItem(nsx.getTen());
        }
    }

    private void loadCbA() {
        cbAnh.removeAllItems();
        for (Anh A : chiTietSPService.getListA()) {
            cbAnh.addItem(A.getTen());
        }
    }

    private void loadCbSp() {
        cbSanPham.removeAllItems();
        for (SanPham sp : chiTietSPService.getListSp()) {
            cbSanPham.addItem(sp.getTen());
        }
    }

    private ChiTietSpResponse_2 getCtr() throws ParseException {
        ChiTietSpResponse_2 ctr = new ChiTietSpResponse_2();
        ctr.setTen(cbSanPham.getSelectedItem().toString());
        ctr.setAnh(cbAnh.getSelectedItem().toString());
        Double gia = Double.parseDouble(txtDonGia.getText());
        ctr.setDonGia(BigDecimal.valueOf(gia));
        Date hsd = jdcHSD.getDate();
        ctr.setHsd(hsd);
        ctr.setLoaiSP(cbLoaiSP.getSelectedItem().toString());
        ctr.setSoLuongTon(Integer.parseInt(txtSoLuong.getText()));
        ctr.setNsx(cbNsx.getSelectedItem().toString());
        return ctr;
    }

    private void loadCB() {
        loadCbA();
        loadCbLSP();
        loadCbNsx();
        loadCbSp();
    }

    private void loadTF(int i) {
        for (ChiTietSP ct : chiTietSpRepository.getAll()) {
            if (ct.getSanPham().getMa().equals(tbSanPham.getValueAt(i, 0))) {
                cbSanPham.setSelectedItem(ct.getSanPham().getTen());
                cbLoaiSP.setSelectedItem(ct.getLoaiSP().getTen());
                cbNsx.setSelectedItem(ct.getNsx().getTen());
                cbAnh.setSelectedItem(ct.getAnh().getTen());
                txtDonGia.setText(ct.getDonGia().toString());
                jdcHSD.setDate(ct.getHsd());
                txtSoLuong.setText(String.valueOf(ct.getSoLuongTon()));
                lbAnhSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\SanPham\\" + ct.getAnh().getLink()));
            }
        }
    }

    private int getSPIdFromSelectedRow() {
        int selectedRowIndex = tbSanPham.getSelectedRow();
        int id = 0;
        for (ChiTietSpResponse_2 ct : chiTietSPService.getAllFSP()) {
            if (ct.getMa().equals(tbSanPham.getValueAt(selectedRowIndex, 0))) {
                id = ct.getId();
            }
        }
        return id;
    }

    private void loadIcon() {
        btnThemSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add_form.png"));
        btnThemAnh.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add_form.png"));
        btnThemLSP.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add_form.png"));
        btnThemNsx.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add_form.png"));
        btnThem.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\add_form.png"));
        btnUpdate.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\exchange.png"));
        lbTimKiem.setIcon(new ImageIcon("D:\\Nhom6_PRO1041\\Anh\\search.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbAnhSP = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbSanPham = new javax.swing.JComboBox<>();
        cbNsx = new javax.swing.JComboBox<>();
        cbAnh = new javax.swing.JComboBox<>();
        cbLoaiSP = new javax.swing.JComboBox<>();
        btnThemSP = new javax.swing.JButton();
        btnThemNsx = new javax.swing.JButton();
        btnThemLSP = new javax.swing.JButton();
        btnThemAnh = new javax.swing.JButton();
        jdcHSD = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtTimKiemSP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbTimKiemLSP = new javax.swing.JComboBox<>();
        lbTimKiem = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        jPanel1.setBackground(new java.awt.Color(226, 215, 214));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel1.setForeground(new java.awt.Color(102, 0, 51));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setText("Loại sản phẩm:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 153));
        jLabel5.setText("Nhà sản xuất:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 153));
        jLabel6.setText("Đơn giá:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 153));
        jLabel7.setText("Hạn sử dụng:");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 102, 153));
        jLabel8.setText("Số lượng:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 153));
        jLabel11.setText("Ảnh:");

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 102, 153));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 102, 153));
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 153));
        jLabel12.setText("Sản phẩm:");

        cbSanPham.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbNsx.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbNsx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbAnh.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbAnh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnThemNsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNsxActionPerformed(evt);
            }
        });

        btnThemLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLSPActionPerformed(evt);
            }
        });

        btnThemAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemAnhActionPerformed(evt);
            }
        });

        jdcHSD.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(117, 117, 117))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(cbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtSoLuong)
                                    .addComponent(txtDonGia)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbAnh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcHSD, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(btnThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThemLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnThemNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(104, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThem, btnUpdate});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnThemAnh, btnThemLSP, btnThemNsx, btnThemSP});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lbAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(cbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnThemSP, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(cbNsx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbAnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemNsx, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jdcHSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnUpdate))
                .addContainerGap(229, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnThemAnh, btnThemLSP, btnThemNsx, btnThemSP});

        jPanel2.setBackground(new java.awt.Color(226, 215, 214));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jPanel2.setForeground(new java.awt.Color(0, 102, 153));

        tbSanPham.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 153));
        jLabel9.setText("Tên sản phẩm");

        txtTimKiemSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemSPKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 153));
        jLabel10.setText("Loại sản phẩm");

        cbTimKiemLSP.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cbTimKiemLSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTimKiemLSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTimKiemLSPItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(cbTimKiemLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(559, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTimKiemLSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        try {
            ChiTietSpResponse_2 ctr = getCtr();
            if (chiTietSPService.addSP(ctr) != null) {
                loadSP(chiTietSPService.getAllFSP());
                JOptionPane.showMessageDialog(this, "Suscess");
            } else {
                JOptionPane.showMessageDialog(this, "Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
        int index = tbSanPham.getSelectedRow();
        loadTF(index);
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void txtTimKiemSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSPKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ArrayList<ChiTietSpResponse_2> listTimKiem = chiTietSPService.getTimKiemFSP(txtTimKiemSP.getText().toString());
            loadSP(listTimKiem);
        }
    }//GEN-LAST:event_txtTimKiemSPKeyPressed

    private void cbTimKiemLSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTimKiemLSPItemStateChanged
        // TODO add your handling code here: 
        if (evt.getStateChange() == 1) {
            if (cbTimKiemLSP.getSelectedIndex() == 0) {
                loadSP(listHD);
            } else {
                List<ChiTietSpResponse_2> listTimKiem = chiTietSPService.getTimKiemLsp(cbTimKiemLSP.getSelectedItem().toString());
                loadSP(listTimKiem);
            }
        }
    }//GEN-LAST:event_cbTimKiemLSPItemStateChanged

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        ChiTietSpResponse_2 updatedProduct;
        try {
            updatedProduct = getCtr();

            int updatedProductId = getSPIdFromSelectedRow();
            updatedProduct.setId(updatedProductId);
            if (chiTietSPService.updateSP(updatedProduct) != null) {
                JOptionPane.showMessageDialog(this, "Thành công");
                loadSP(chiTietSPService.getAllFSP());
            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
         Window wd = SwingUtilities.windowForComponent(this);
        DialogSP dialogSp = new DialogSP((Frame) wd, true);
        dialogSp.setVisible(true);
        loadCB();
    }//GEN-LAST:event_btnThemSPActionPerformed

    private void btnThemNsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNsxActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogNsx dialogNsx = new DialogNsx((Frame) wd, true);
        dialogNsx.setVisible(true);
        loadCB();
    }//GEN-LAST:event_btnThemNsxActionPerformed

    private void btnThemLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLSPActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogLSP dialogLSP = new DialogLSP((Frame) wd, true);
        dialogLSP.setVisible(true);
        loadCB();
    }//GEN-LAST:event_btnThemLSPActionPerformed

    private void btnThemAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemAnhActionPerformed
        // TODO add your handling code here:
        Window wd = SwingUtilities.windowForComponent(this);
        DialogAnh dialogAnh = new DialogAnh((Frame) wd, true);
        dialogAnh.setVisible(true);
        loadCB();
    }//GEN-LAST:event_btnThemAnhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemAnh;
    private javax.swing.JButton btnThemLSP;
    private javax.swing.JButton btnThemNsx;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbAnh;
    private javax.swing.JComboBox<String> cbLoaiSP;
    private javax.swing.JComboBox<String> cbNsx;
    private javax.swing.JComboBox<String> cbSanPham;
    private javax.swing.JComboBox<String> cbTimKiemLSP;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcHSD;
    private javax.swing.JLabel lbAnhSP;
    private javax.swing.JLabel lbTimKiem;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiemSP;
    // End of variables declaration//GEN-END:variables
}
