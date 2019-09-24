/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import beans.KompPaket;
import database.HibernateUtil;
import database.Ima;
import database.Kompanija;
import database.Paket;
import database.Stavka;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stefan
 */
public class PaketHelper {

    Session session = null;

    public PaketHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List nadjiKompPaket(List<Paket> paketi) {
        List<KompPaket> lista = new ArrayList<>();

        List<Kompanija> kLista = null;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int i = 0;
            while (i < paketi.size()) {
                Paket p = paketi.get(i++);
                Query q = session.createQuery("from Kompanija as k where k.idKomp in  "
                        + "(select nu.idKomp from NovcaniUgovor as nu where nu.idPaket = "+p.getIdPaket()+" and"
                        + " nu.datumIsticanja > '"+format.format(date)+"' ) or k.idKomp in "
                        + "(select du.idKomp from DonatorskiUgovor as du where du.idPaket = "+p.getIdPaket()+" and "
                                + "du.datumIsticanja > '"+format.format(date)+"')");
                
                
                kLista = (List<Kompanija>) q.list();
                KompPaket kp = new KompPaket();

                q = session.createQuery("from Ima as i where i.idPaket = " + p.getIdPaket() + " and i.idStavka = 1");
                // stavka 1 je opis
                if (q.uniqueResult() != null) {
                    kp.setOpis(true);
                }
                q = session.createQuery("from Ima as i where i.idPaket = " + p.getIdPaket() + " and i.idStavka = 2");
                // stavka 2 je baner
                if (q.uniqueResult() != null) {
                    kp.setBaner(true);
                }

                q = session.createQuery("from Ima as i where i.idPaket = " + p.getIdPaket() + " and i.idStavka = 3");
                // stavka 3 je logo
                if (q.uniqueResult() != null) {
                    kp.setLogo(true);
                }

                if (kLista != null) {
                    kp.setKompanije(kLista);
                    kp.setPaket(p);
                    lista.add(kp);

                } else {
                    kp.setKompanije(null);
                    kp.setPaket(p);
                    lista.add(kp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List getLista1() {
        List<Paket> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Paket order by naziv asc");
            lista = (List<Paket>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List getLista2() {
        List<Paket> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Paket order by naziv desc");
            lista = (List<Paket>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List getLista3() {
        List<Paket> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Paket order by vrednost asc");
            lista = (List<Paket>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List getLista4() {
        List<Paket> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Paket order by vrednost desc");
            lista = (List<Paket>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List getListaStavki() {
        List<Stavka> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Stavka");
            lista = (List<Stavka>) q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void savePaket(Paket p) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(p);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveIma(Ima ima) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(ima);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List getListaIma() {
        List<Ima> lista = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();

            Query q = session.createQuery("from Ima");
            lista = q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

}
