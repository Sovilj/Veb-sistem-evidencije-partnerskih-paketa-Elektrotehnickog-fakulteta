/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import database.DonatorskiUgovor;
import database.HibernateUtil;
import database.Kompanija;
import database.NovcaniUgovor;
import database.Paket;
import database.Status;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stefan
 */
public class UgovorHelper {

    Session session;
    public static final int MILLIS_IN_SECOND = 1000;
    public static final int SECONDS_IN_MINUTE = 60;
    public static final int MINUTES_IN_HOUR = 60;
    public static final int HOURS_IN_DAY = 24;
    public static final int DAYS_IN_YEAR = 365;
    public static final long MILLISECONDS_IN_YEAR
            = (long) MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR
            * HOURS_IN_DAY * DAYS_IN_YEAR;
    public static final long MILLISECONDS_IN_MONTH
            = (long)MILLIS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR
            * HOURS_IN_DAY * 30;

    public UgovorHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getStatusi() {
        List<String> lista = new ArrayList<>();
        List<Status> listaST = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Status");
            listaST = (List<Status>) q.list();

            int i = 0;
            while (i < listaST.size()) {

                Status s = listaST.get(i++);
                lista.add(s.getNaziv());
            }

        } catch (Exception e) {
        }
        return lista;
    }

    public List getKompanije() {
        List<Kompanija> lista = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Kompanija");
            lista = (List<Kompanija>) q.list();

        } catch (Exception e) {
        }
        return lista;
    }

    public void dodajNovcani(NovcaniUgovor nu) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Paket as p where p.idPaket = " + nu.getIdPaket());
            Paket p = (Paket) q.uniqueResult();

            Date sum = new Date(nu.getDatumUgovora().getTime() + (long) (p.getTrajanjeUgovora() * MILLISECONDS_IN_YEAR));
            nu.setDatumIsticanja(sum);

            session.save(nu);
            session.getTransaction().commit();

        } catch (Exception e) {
        }

    }

    public void dodajDonatorski(DonatorskiUgovor du) {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(du);
            session.getTransaction().commit();

        } catch (Exception e) {
        }

    }
}
