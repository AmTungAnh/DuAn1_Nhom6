/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.it17326.group6.repository;

import com.poly.it17326.group6.config.HibernateConfig;
import com.poly.it17326.group6.domainmodel.KhoiLuong;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author OS
 */
public class AnhRepository {

    public List<KhoiLuong> getAll() {
        Session session = HibernateConfig.getFACTORY().openSession();
        String sql = "from KhoiLuong a order by cast (SUBSTRING(a.ma,3,3) as int) desc";
        Query query = session.createQuery(sql);
        List<KhoiLuong> listA = (List<KhoiLuong>) query.getResultList();
        session.close();
        return listA;
    }

    public boolean addAnh(KhoiLuong anh) {
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(anh);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateAnh(String Ma, String Ten) {
        Transaction tran = null;

        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "update KhoiLuong set Ten = : Ten where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("Ten", Ten);
            query.setParameter("Ma", Ma);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean XoaAnh(String ma) {
        Transaction tran = null;

        try (Session session = HibernateConfig.getFACTORY().openSession();) {
            tran = session.beginTransaction();
            String sql = "  delete from KhoiLuong where Ma = :Ma";
            Query query = session.createQuery(sql);
            query.setParameter("Ma", ma);
            query.executeUpdate();
            tran.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

//    public static void main(String[] args) {
//        AnhRepository anhRepository = new AnhRepository();
//        anhRepository.updateAnh("A001", "Canxiproo_950g");
//    }
}
