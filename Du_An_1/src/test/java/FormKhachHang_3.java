/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */


import com.poly.it17326.group6.domainmodel.HoaDon;
import com.poly.it17326.group6.domainmodel.KhachHang;
import com.poly.it17326.group6.response.HoaDonresponse;
import com.poly.it17326.group6.service.HoaDonService;
import com.poly.it17326.group6.service.KhachHangService;
import com.poly.it17326.group6.service.impl.HoaDonServecieIplm;
import com.poly.it17326.group6.service.impl.KhachHangServiceImpl;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 123
 */
public class FormKhachHang_3 extends javax.swing.JDialog {

    private List<String> listRank = new ArrayList<>();
    private HoaDonService hoaDonService = new HoaDonServecieIplm();
    private KhachHangService khachHangService = new KhachHangServiceImpl();

    /**
     * Creates new form DialogKH
     */
    public FormKhachHang_3(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(null);
        loadTable(khachHangService.getList());
        loadTable1(khachHangService.getList());
        //  loadTableLS(khachHangService.getListLS());
        auto();
    }

    private void auto() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    updateRankAll();
                    loadTable(khachHangService.getList());
                    if (getIdKHByMa() != -1) {
                        lbRank1.setText(khachHangService.getOne(getIdKHByMa()).Rank());
                        System.out.println(khachHangService.getOne(getIdKHByMa()).Rank());
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        //  Logger.getLogger(KhachHangJpanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }.start();
    }

    private int getIdKHByMa() {
        khachHangService = new KhachHangServiceImpl();

        for (KhachHang kh : khachHangService.getList()) {
            if (kh.getMa().equals(txtMaKH.getText())) {
                return kh.getId();
            }
        }
        return -1;
    }

    public void updateRankAll() {
        khachHangService = new KhachHangServiceImpl();
        for (KhachHang kh : khachHangService.getList()) {
            updateRankOne(kh.getId());
        }

    }

    private void updateRankOne(int id) {
        khachHangService = new KhachHangServiceImpl();
        KhachHang kh = khachHangService.getOne(id);
        if (kh.getMa().equalsIgnoreCase("KH0")) {
            kh.setRank(0);
        } else if (tongTienMua(id) < 3000000) {
            kh.setRank(0);
        } else if (tongTienMua(id) < 5000000) {
            kh.setRank(1);
        } else if (tongTienMua(id) < 1000000) {
            kh.setRank(2);
        } else {
            kh.setRank(3);
        }

        khachHangService.updateKH(kh);
    }

    private int tongTienMua(int id) {
        hoaDonService = new HoaDonServecieIplm();

        int tong = 0;
        for (HoaDon hoaDon : hoaDonService.getALLHD()) {
            if (hoaDon.getKhachHang().getId() == id && hoaDon.getTrangThai() == 1) {
                // tt 1 la  đã thanh toán
                tong += hoaDon.getTongTien().intValue();
            }
        }
        return tong;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void loadTable(List<KhachHang> listH) {
        DefaultTableModel model = (DefaultTableModel) tbKH.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã", "Họ tên", "SDT", "Ngày sinh", "Địa chỉ", "Trạng thái", "Rank"});
        int i = 1;
        for (KhachHang kh : listH) {
            model.addRow(new Object[]{kh.getMa(), kh.getTen(), kh.getSdt(), doiNgay(kh.getNgaySinh()), kh.getDiaChi(), kh.trangThai(), kh.Rank()});
        }
    }

    public void loadTableLS(List<HoaDonresponse> listH) {
        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã HD", "Mã KH", "Tổng tiền hóa đơn", "Ngày mua"});
        int i = 1;
        for (HoaDonresponse kh : listH) {
            model.addRow(new Object[]{kh.getMaHD(), kh.getMaKH(), kh.getTongTien(), doiNgay(kh.getNgayTao())});
        }
    }

    public void loadTable1(List<KhachHang> listH) {
        DefaultTableModel model = (DefaultTableModel) tblThongTin.getModel();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Mã", "Họ tên", "SDT", "Ngày sinh", "Địa chỉ", "Trạng thái", "Rank"});
        int i = 1;
        for (KhachHang kh : listH) {
            model.addRow(new Object[]{kh.getMa(), kh.getTen(), kh.getSdt(), doiNgay(kh.getNgaySinh()), kh.getDiaChi(), kh.trangThai(), kh.Rank()});

        }
    }
    String ma = null;

    public String getKH() {
        return ma;
    }

    private String doiNgay(Date d) {
        String ngayTao = null;
        if (d == null) {
            ngayTao = "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            ngayTao = format.format(d);
        }
        return ngayTao;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        btnXuat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKH = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();
        lbRank = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jdcNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaDiaCHi = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThongTin = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbRank1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnXuat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXuat.setText("Xuât file excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        tbKH.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbKH);

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Tìm kiếm:");

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton2.setText("Show danh sach");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Danh Sách Khách Hàng", jPanel5);

        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tblLichSu);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Lịch Sử Giao Dịch", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Danh sách khách hàng", jPanel1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Mã khách hàng:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Tên khách hàng:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Ngày sinh:");

        jdcNgaySinh.setDateFormatString("yyyy-MM-dd");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Địa chỉ:");

        txtaDiaCHi.setColumns(20);
        txtaDiaCHi.setRows(5);
        jScrollPane2.setViewportView(txtaDiaCHi);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        tblThongTin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThongTinMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThongTin);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Rank :");

        lbRank1.setText("jLabel14");

        javax.swing.GroupLayout lbRankLayout = new javax.swing.GroupLayout(lbRank);
        lbRank.setLayout(lbRankLayout);
        lbRankLayout.setHorizontalGroup(
            lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbRankLayout.createSequentialGroup()
                .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lbRankLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(lbRankLayout.createSequentialGroup()
                                .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(lbRankLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(btnThem)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnLamMoi))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jdcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                    .addComponent(txtMaKH)
                                    .addComponent(lbRank1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(lbRankLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(jLabel1)))
                .addGap(39, 39, 39))
        );

        lbRankLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jdcNgaySinh, txtMaKH, txtSdt, txtTenKH});

        lbRankLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnLamMoi, btnThem});

        lbRankLayout.setVerticalGroup(
            lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lbRankLayout.createSequentialGroup()
                .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lbRankLayout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, lbRankLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbRank1))
                        .addGap(12, 12, 12)
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lbRankLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4))
                            .addGroup(lbRankLayout.createSequentialGroup()
                                .addComponent(jdcNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lbRankLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel7))
                            .addGroup(lbRankLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lbRankLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLamMoi)
                            .addComponent(btnThem))))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Cập nhật khách hàng ", lbRank);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("QUẢN LÍ KHÁCH HÀNG");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(245, 245, 245)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(201, 201, 201)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("HoaDon");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã KH");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Họ tên");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("SĐT");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày sinh");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Địa chỉ");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("trạng thái");

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("rank");

            for (int i = 0; i < tblThongTin.getRowCount(); i++) {

                row = sheet.createRow(5 + i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i + 1);

                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(tblThongTin.getValueAt(i, 0).toString());

                cell = row.createCell(2, CellType.NUMERIC);
                cell.setCellValue(tblThongTin.getValueAt(i, 1).toString());

                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(tblThongTin.getValueAt(i, 2).toString());

                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(tblThongTin.getValueAt(i, 3).toString());

                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(tblThongTin.getValueAt(i, 4).toString());

                cell = row.createCell(6, CellType.NUMERIC);
                cell.setCellValue(tblThongTin.getValueAt(i, 5).toString());

                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(tblThongTin.getValueAt(i, 6).toString());

            }

            File file = new File("D:\\Nhom6_PRO1041\\Export_KH\\KhachHang.xlsx");
            try {
                FileOutputStream fis = new FileOutputStream(file);
                workbook.write(fis);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Xuat thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Xuat that bai");
        }


    }//GEN-LAST:event_btnXuatActionPerformed

    private void tbKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKHMouseClicked
        // TODO add your handling code here:
        int index = tbKH.getSelectedRow();
        ma = tbKH.getValueAt(index, 0).toString();
        loadTableLS(khachHangService.getListLS(ma));
    }//GEN-LAST:event_tbKHMouseClicked

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            List<KhachHang> list = khachHangService.getCheckKH(txtTimKiem.getText());
            loadTable(list);
        }
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaKH.setText("");
        txtSdt.setText("");
        txtaDiaCHi.setText("");
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse("0000-00-00");
            jdcNgaySinh.setDate(d);
            jdcNgaySinh.removeAll();
        } catch (ParseException ex) {
            Logger.getLogger(FormKhachHang_3.class.getName()).log(Level.SEVERE, null, ex);
        }
        buttonGroup1.clearSelection();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        KhachHang kh = new KhachHang();
        kh.setMa(txtMaKH.getText());
        kh.setTen(txtTenKH.getText());
        kh.setNgaySinh(jdcNgaySinh.getDate());
        kh.setDiaChi(txtaDiaCHi.getText());
        kh.setSdt(txtSdt.getText());
        if (khachHangService.addKH(kh)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadTable(khachHangService.getList());
        } else {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
        }
        loadTable(khachHangService.getList());
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblThongTinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThongTinMouseClicked
        // TODO add your handling code here:
        int index = tblThongTin.getSelectedRow();
        txtMaKH.setText(tblThongTin.getValueAt(index, 0).toString());
        txtTenKH.setText(tblThongTin.getValueAt(index, 1).toString());
        txtSdt.setText(tblThongTin.getValueAt(index, 2).toString());
        lbRank1.setText(tblThongTin.getValueAt(index, 6).toString());
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd").parse(tblThongTin.getValueAt(index, 3).toString());
            jdcNgaySinh.setDate(d);
        } catch (ParseException ex) {
            Logger.getLogger(FormKhachHang_3.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtaDiaCHi.setText(tblThongTin.getValueAt(index, 4).toString());
    }//GEN-LAST:event_tblThongTinMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadTable(khachHangService.getList());
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(FormKhachHang_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormKhachHang_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormKhachHang_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormKhachHang_3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormKhachHang_3 dialog = new FormKhachHang_3(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private com.toedter.calendar.JDateChooser jdcNgaySinh;
    private javax.swing.JPanel lbRank;
    private javax.swing.JLabel lbRank1;
    private javax.swing.JTable tbKH;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JTable tblThongTin;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextArea txtaDiaCHi;
    // End of variables declaration//GEN-END:variables
}