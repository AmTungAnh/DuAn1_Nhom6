/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.domainmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "HoaDon")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class HoaDon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Ma")
    private String MaHD;
    @Column(name = "MaKH")
    private String MaKH;
    @Column(name = "HoTen")
    private String HoTenkh;
    @Column(name = "Sdt")
    private String Sdt;
    @Column(name = "TongTien")
    private BigDecimal tongTien;
    @Column(name = "Create_at")
    private Date createAt;
    @Column(name = "Update_at")
    private Date updateAt;
    @Column(name = "Deleted")
    private boolean delete;
    @Column(name = "TrangThai")
    private int TrangThai;


// khoa ngoai voi banng tai khoan
    @ManyToOne(targetEntity = com.poly.it17326.group6.domainmodel.TaiKhoan.class)
    @JoinColumn(name = "IdTK",referencedColumnName = "Id")
    private TaiKhoan taiKhoan;

 

    // chua maping voi vocher
    @ManyToOne(targetEntity = com.poly.it17326.group6.domainmodel.Voucher.class)
    @JoinColumn(name = "IdVC",referencedColumnName = "Id")
    private Voucher voucher;
}
