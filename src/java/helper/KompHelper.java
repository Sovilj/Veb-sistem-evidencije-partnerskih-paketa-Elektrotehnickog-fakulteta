/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import database.HibernateUtil;
import database.Kompanija;
import database.Korisnik;
import database.UKontaktu;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stefan
 */
public class KompHelper {

    Session session = null;

    public KompHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void setKomp(Kompanija komp) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(komp);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setKontakt(Kompanija komp, Korisnik kor) {
        try {
            UKontaktu uk = new UKontaktu(kor.getUsername(), komp.getIdKomp());

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(uk);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateKomp(Kompanija komp) {
        try {
            

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.update(komp);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public List getKompanije(){
        List<Kompanija> lista = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            
            Query q = session.createQuery("from Kompanija");
            lista = (List<Kompanija>) q.list();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
