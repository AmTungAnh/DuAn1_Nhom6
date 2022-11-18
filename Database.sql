﻿CREATE DATABASE FINALASS_GROUP6_PRO1041
USE FINALASS_GROUP6_PRO1041
GO
-- TaiKhoan
CREATE TABLE TaiKhoan(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
HoTen NVARCHAR(50) DEFAULT NULL,
GioiTinh NVARCHAR(10) DEFAULT NULL,
NgaySinh DATE DEFAULT NULL,
DiaChi NVARCHAR(100) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
MatKhau VARCHAR(MAX) DEFAULT NULL,
email varchar(Max) DEFAULT null,
IdCV UNIQUEIDENTIFIER,
Status bit DEFAULT 0,
Create_at DATE DEFAULT NULL,
Update_at DATE DEFAULT NULL,
Deleted bit default 0,
)
GO

--HoaDon
CREATE TABLE HoaDon(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdTK UNIQUEIDENTIFIER,
IdVC UNIQUEIDENTIFIER,
IdTT UNIQUEIDENTIFIER,
Ma VARCHAR(20) UNIQUE,
MaKH VARCHAR(20) UNIQUE,
HoTen NVARCHAR(50) DEFAULT NULL,
Sdt VARCHAR(30) DEFAULT NULL,
TongTien DECIMAL(20,0) DEFAULT 0,
Create_at DATE DEFAULT NULL,
Update_at DATE DEFAULT NULL,
Deleted bit default 0,
)
GO
-- Voucher 
CREATE TABLE Voucher(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
NgayApDung DATE DEFAULT NULL,
NgayKetThuc DATE DEFAULT NULL,
PhanTram float default 0,
)
GO
-- TinhTrang
CREATE TABLE TinhTrang(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
TrangThai bit default 0,
)
GO
-- SanPham
CREATE TABLE SanPham(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
Create_at DATE DEFAULT NULL,
Update_at DATE DEFAULT NULL,
Deleted bit default 0,
)
GO
-- NSX
CREATE TABLE NSX(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
QuocGia NVARCHAR(30)
)
GO
-- Anh
CREATE TABLE Anh(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
Cover bit DEFAULT 0
)
GO
-- LoaiSP
CREATE TABLE LoaiSP(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
IdTP UNIQUEIDENTIFIER,
)
GO
-- ThanhPhan
CREATE TABLE ThanhPhan(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
-- ChucVu
CREATE TABLE ChucVu(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30)
)
GO
-- ChiTietSP
CREATE TABLE ChiTietSP(
Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
IdSP UNIQUEIDENTIFIER,
IdNsx UNIQUEIDENTIFIER,
IdAnh UNIQUEIDENTIFIER,
IdLoaiSP UNIQUEIDENTIFIER,
HSD DATE DEFAULT NULL,
SoLuongTon INT,
DonGia DECIMAL(20,0) DEFAULT 0,
)

GO
-- HoaDonChiTiet
CREATE TABLE HoaDonChiTiet(
IdHoaDon UNIQUEIDENTIFIER,
IdChiTietSP UNIQUEIDENTIFIER,
Ma VARCHAR(20) UNIQUE,
Ten NVARCHAR(30),
SoLuong INT,
DonGia DECIMAL(20,0) DEFAULT 0,
TongTienSP DECIMAL(20,0) DEFAULT 0,
CONSTRAINT PK_HoaDonCT PRIMARY KEY (IdHoaDon,IdChiTietSP),
CONSTRAINT FK1 FOREIGN KEY(IdHoaDon) REFERENCES HoaDon(ID),
CONSTRAINT FK2 FOREIGN KEY(IdChiTietSP) REFERENCES ChiTietSP(Id),
)
GO


--TẠO QUAN HỆ GIỮA CÁC BẢNG
--TaiKhoan - ChucVu
ALTER TABLE TaiKhoan ADD FOREIGN KEY (IdCV) REFERENCES ChucVu(Id)

-- HoaDon - NhanVien
ALTER TABLE HoaDon ADD FOREIGN KEY (IdTK) REFERENCES TaiKhoan(Id)
-- HoaDon - Voucher 
ALTER TABLE HoaDon ADD FOREIGN KEY (IdVC) REFERENCES Voucher(Id)
-- HoaDon - TinhTrang
ALTER TABLE HoaDon ADD FOREIGN KEY (IdTT) REFERENCES TinhTrang(Id)

-- LoaiSP - ThanhPhan
ALTER TABLE LoaiSP ADD FOREIGN KEY (IdTP) REFERENCES ThanhPhan(Id)

-- ChiTietSP - SanPham
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdSP) REFERENCES SanPham(Id)
-- ChiTietSP - NSX
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdNsx) REFERENCES Nsx(Id)
-- ChiTietSP - Anh
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdAnh) REFERENCES Anh(Id)
-- ChiTietSP - LoaiSP
ALTER TABLE ChiTietSP ADD FOREIGN KEY(IdLoaiSP) REFERENCES LoaiSP(Id)