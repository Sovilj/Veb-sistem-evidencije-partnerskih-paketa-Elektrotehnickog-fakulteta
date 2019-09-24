/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import database.HibernateUtil;
import database.Korisnik;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stefan
 */
public class RegHelper {

    Session session = null;

    public RegHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void setZahtev(Korisnik k) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(k);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List getKorisnik() {
        List<Korisnik> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Korisnik where zahtev = 1");
            lista =(List<Korisnik>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    public void prihvati(Korisnik k) {
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.update(k);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }

}
