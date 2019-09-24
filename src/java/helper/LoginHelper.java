/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import database.HibernateUtil;
import database.Korisnik;
import org.hibernate.HibernateError;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Stefan
 */
public class LoginHelper {

    Session session = null;

    public LoginHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public Korisnik getUser(String user) {
        this.session = HibernateUtil.getSessionFactory().openSession();

        Korisnik k = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Korisnik as k where k.username='" + user + "'");
            k = (Korisnik) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    public void setPass(String username, String password) {

        this.session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Korisnik k = (Korisnik) session.get(Korisnik.class, username);
            k.setPassword(password);
            session.update(k);
            tx.commit();

        } catch (HibernateError e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }


}
