/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import database.HibernateUtil;
import database.Kompanija;
import database.Korisnik;
import database.Oglas;
import java.sql.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stefan
 */
public class OglasHelper {

    Session session = null;

    public OglasHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List nadjiKomp(Korisnik kor) {
        List<Kompanija> lista = null;

        try {
            if (kor.getTip() == 3) {
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();

                Query q = session.createQuery("from Kompanija as komp where komp.idKomp in (select uk.idKomp from UKontaktu as uk where uk.username = '" + kor.getUsername() + "')");

                lista = (List<Kompanija>) q.list();
            }else if((kor.getTip() == 2) || (kor.getTip() == 1)){
                
                session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();

                Query q = session.createQuery("from Kompanija");

                lista = (List<Kompanija>) q.list();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean provera(Kompanija k, Date datum) {
        boolean b = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Kompanija as k where k.idKomp =" + k.getIdKomp() + " and k.idKomp in (select nu.idKomp from NovcaniUgovor as nu where nu.status = 'potpisane obe' and nu.datumIsticanja > '" + datum + "' )");

            if (q.uniqueResult() != null) {
                b = true;
            }
            q = session.createQuery("from Kompanija as k where k.idKomp =" + k.getIdKomp() + " and k.idKomp in (select du.idKomp from DonatorskiUgovor as du where du.status = 'potpisane obe' and du.datumIsticanja > '" + datum + "' )");
            if (q.uniqueResult() != null) {
                b = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    public void postaviOglas(Oglas oglas) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(oglas);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List nadjiOglase() {
        List<Oglas> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Oglas as o order by o.datum desc , o.vreme desc");
            lista = (List<Oglas>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
