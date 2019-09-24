/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import helper.UgovorHelper;
import beans.KompUgovor;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import database.*;
import database.DonatorskiUgovor;
import database.HibernateUtil;
import database.Kompanija;
import database.NovcaniUgovor;
import database.Paket;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Stefan
 */
public class PretragaHelper {

    Session session;

    public PretragaHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List pretraga(String paket, String komp, Boolean vazeci) {

        List<Kompanija> lista = null;

        if (!vazeci) {

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Kompanija as k where k.naziv like '%" + komp + "%' "
                    + "and ( k.idKomp in (select nu.idKomp from NovcaniUgovor as nu where nu.idPaket"
                    + " in (select p.idPaket from Paket as p where p.naziv like '%" + paket + "%') ) "
                    + "or k.idKomp in (select du.idKomp from DonatorskiUgovor as du where du.idPaket"
                    + " in (select pa.idPaket from Paket as pa where pa.naziv like '%" + paket + "%') ))");

            lista = (List<Kompanija>) q.list();

        } else {

            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from Kompanija as k where k.naziv like '%" + komp + "%' and "
                    + "( k.idKomp in (select nu.idKomp from NovcaniUgovor as nu where nu.datumIsticanja > '" + format.format(date) + "' "
                    + "and nu.status = 'potpisane obe' and nu.idPaket in (select p.idPaket from Paket as p where p.naziv like '%" + paket + "%') ) "
                    + "or k.idKomp in (select du.idKomp from DonatorskiUgovor as du where du.datumIsticanja > '" + format.format(date) + "' "
                    + "and du.status = 'potpisane obe' and du.idPaket in (select pa.idPaket from Paket as pa where pa.naziv like '%" + paket + "%') ))");

            lista = (List<Kompanija>) q.list();

        }

        return lista;
    }

    public List getKompUgovor(Kompanija k) {
        List<KompUgovor> lista = new ArrayList<>();
        List<NovcaniUgovor> listaNU = null;
        List<DonatorskiUgovor> listaDU = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from NovcaniUgovor as nu where nu.idKomp = " + k.getIdKomp());
            listaNU = (List<NovcaniUgovor>) q.list();

            q = session.createQuery("from DonatorskiUgovor as du where du.idKomp = " + k.getIdKomp());
            listaDU = (List<DonatorskiUgovor>) q.list();

            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            int i = 0;
            while (i < listaNU.size()) {
                KompUgovor ku = new KompUgovor();
                NovcaniUgovor nu = listaNU.get(i++);
                if (nu.getDatumIsticanja().before(date)) {
                    ku.setIstekao(true);
                } else {
                    ku.setIstekao(false);
                }
                ku.setDatumIsticanja(nu.getDatumIsticanja());
                ku.setDatumSklapanja(nu.getDatumUgovora());
                ku.setKompanija(k);

                q = session.createQuery("from Paket as p where p.idPaket in (select nu.idPaket from NovcaniUgovor nu where nu.idNUgov = " + nu.getIdNUgov() + ")");
                Paket p = (Paket) q.uniqueResult();

                ku.setTipUgovora("Novcani Ugovor");
                ku.setTipPaketa(p.getNaziv());

                lista.add(ku);
            }
            i = 0;
            while (i < listaDU.size()) {
                KompUgovor ku = new KompUgovor();
                DonatorskiUgovor du = listaDU.get(i++);
                if (du.getDatumIsticanja().before(date)) {
                    ku.setIstekao(true);
                } else {
                    ku.setIstekao(false);
                }
                ku.setDatumIsticanja(du.getDatumIsticanja());
                ku.setDatumSklapanja(du.getDatumUgovora());
                ku.setKompanija(k);

                q = session.createQuery("from Paket as p where p.idPaket in (select du.idPaket from DonatorskiUgovor du where du.idDUgov = " + du.getIdDUgov() + ")");
                Paket p = (Paket) q.uniqueResult();

                ku.setTipUgovora("Donatorski Ugovor");
                ku.setTipPaketa(p.getNaziv());

                lista.add(ku);
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

    public List getKompIsticu() {
        List<Kompanija> kompIsticu = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date danasnji = new Date(System.currentTimeMillis());
            Date zaSestMeseci = new Date(danasnji.getTime() + 6 * UgovorHelper.MILLISECONDS_IN_MONTH);

            Query q = session.createQuery("from Kompanija as k where "
                    + "k.idKomp in (select nu.idKomp from NovcaniUgovor as nu where nu.datumIsticanja > '"+format.format(danasnji)+"' and nu.datumIsticanja < '"+format.format(zaSestMeseci)+"') "
                    + "or k.idKomp in (select du.idKomp from DonatorskiUgovor as du where du.datumIsticanja > '"+format.format(danasnji)+"' and du.datumIsticanja < '"+format.format(zaSestMeseci)+"') ");

            kompIsticu = (List<Kompanija>) q.list();

        } catch (Exception e) {
        }

        return kompIsticu;
    }

    public List getKompIstekle() {
        List<Kompanija> kompIstekle = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date danasnji = new Date(System.currentTimeMillis());
            Date preSestMeseci = new Date(danasnji.getTime() - 6 * UgovorHelper.MILLISECONDS_IN_MONTH);

            Query q = session.createQuery("from Kompanija as k where (k.idKomp in "
                    + "(select nu.idKomp from NovcaniUgovor as nu where nu.datumIsticanja < '"+format.format(danasnji)+"' and nu.datumIsticanja > '"+format.format(preSestMeseci)+"') "
                    + "or k.idKomp in (select du.idKomp from DonatorskiUgovor as du where du.datumIsticanja < '"+format.format(danasnji)+"' and du.datumIsticanja > '"+format.format(preSestMeseci)+"') )"
                    + " and (k.idKomp not in  (select nu.idKomp from NovcaniUgovor as nu where nu.datumIsticanja > '"+format.format(danasnji)+"' ) or "
                    + "k.idKomp in (select du.idKomp from DonatorskiUgovor as du where du.datumIsticanja > '"+format.format(danasnji)+"' ))");

            kompIstekle = (List<Kompanija>) q.list();

        } catch (Exception e) {
        }

        return kompIstekle;
    }

}
