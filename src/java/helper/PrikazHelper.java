/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import beans.KompanijaBool;
import database.DonatorskiUgovor;
import database.HibernateUtil;
import database.Kompanija;
import database.Korisnik;
import database.NovcaniUgovor;
import database.UKontaktu;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stefan
 */
public class PrikazHelper {

    Session session = null;

    List<NovcaniUgovor> listaNU = null;
    List<DonatorskiUgovor> listaDU = null;
    List<KompanijaBool> lista1 = new ArrayList<KompanijaBool>();
    List<KompanijaBool> lista2 = new ArrayList<KompanijaBool>();
    List<Integer> kompanije1 = new ArrayList<Integer>();
    List<Integer> kompanije2 = new ArrayList<Integer>();
    Kompanija k = null;
    UKontaktu uk = null;

    public PrikazHelper() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List getSoonKomp() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from NovcaniUgovor as nu order by nu.datumIsticanja asc");
            listaNU = (List<NovcaniUgovor>) q.list();

            q = session.createQuery("from DonatorskiUgovor as du order by du.datumIsticanja asc");
            listaDU = (List<DonatorskiUgovor>) q.list();

            int du = 0, nu = 0;
            Date datum = new Date();
            java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
            for (int i = 0; i < 5; i++) {
                
                while(listaDU.get(du).getDatumIsticanja().before(sqlDate)){
                    du++;
                }
                while(listaNU.get(nu).getDatumIsticanja().before(sqlDate)){
                    nu++;
                }
                if (du < listaDU.size() && nu < listaNU.size()
                        && listaDU.get(du).getDatumIsticanja().after(listaNU.get(nu).getDatumIsticanja())) {

                        kompanije2.add(listaNU.get(nu).getIdKomp());
                        nu++;
                    
                } else {
                        kompanije2.add(listaDU.get(du).getIdKomp());
                        du++;                   
                }
                if (du >= listaDU.size() && nu < listaNU.size()) {
                        kompanije2.add(listaNU.get(nu).getIdKomp());
                        nu++;
                }
                if (nu >= listaNU.size() && du < listaDU.size()) {
                   
                        kompanije2.add(listaDU.get(du).getIdKomp());
                        du++;                 
                }
            }
            }catch (Exception e) {
            e.printStackTrace();
        }
            return kompanije2;
        }

    

    public List getFreshKomp() {

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery("from NovcaniUgovor as nu order by nu.datumUgovora desc");
            listaNU = (List<NovcaniUgovor>) q.list();

            q = session.createQuery("from DonatorskiUgovor as du order by du.datumUgovora desc");
            listaDU = (List<DonatorskiUgovor>) q.list();

            int du = 0, nu = 0;

            for (int i = 0; i < 5; i++) {
                if (du < listaDU.size() && nu < listaNU.size()
                        && listaDU.get(du).getDatumUgovora().after(listaNU.get(nu).getDatumUgovora())) {
                    kompanije1.add(listaDU.get(du).getIdKomp());
                    du++;
                } else {
                    kompanije1.add(listaNU.get(nu).getIdKomp());
                    nu++;
                }
                if (du >= listaDU.size() && nu < listaNU.size()) {
                    kompanije1.add(listaNU.get(nu).getIdKomp());
                    nu++;
                }
                if (nu >= listaNU.size() && du < listaDU.size()) {
                    kompanije1.add(listaDU.get(du).getIdKomp());
                    du++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kompanije1;
    }

    public List getLista1(Korisnik kor, List listaID) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("from UKontaktu as uk where uk.idKomp = " + listaID.get(0));
        uk = (UKontaktu) q.uniqueResult();

        q = session.createQuery("from Kompanija as k where k.idKomp = " + listaID.get(0));
        k = (Kompanija) q.uniqueResult();

        KompanijaBool kb = new KompanijaBool();
        if (uk != null && uk.getUsername().equals(kor.getUsername())) {
            kb.setBool(true);
            kb.setKompanija(k);
        } else {
            kb.setBool(false);
            kb.setKompanija(k);
        }

        lista1.add(kb);
        for (int i = 1; i < 5; i++) {
            q = session.createQuery("from UKontaktu as uk where uk.idKomp = " + listaID.get(i));
            uk = (UKontaktu) q.uniqueResult();

            q = session.createQuery("from Kompanija as k where k.idKomp = " + listaID.get(i));
            k = (Kompanija) q.uniqueResult();

            kb = new KompanijaBool();
            if (uk != null && uk.getUsername().equals(kor.getUsername())) {
                kb.setBool(true);
                kb.setKompanija(k);
            } else {
                kb.setBool(false);
                kb.setKompanija(k);
            }

            lista1.add(kb);

        }
        return lista1;
    }
    
    public List getLista2(Korisnik kor,List listaID){
        
        
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query q = session.createQuery("from UKontaktu as uk where uk.idKomp = " + listaID.get(0));
        uk = (UKontaktu) q.uniqueResult();

        q = session.createQuery("from Kompanija as k where k.idKomp = " + listaID.get(0));
        k = (Kompanija) q.uniqueResult();

        KompanijaBool kb = new KompanijaBool();
        if (uk != null && uk.getUsername().equals(kor.getUsername())) {
            kb.setBool(true);
            kb.setKompanija(k);
        } else {
            kb.setBool(false);
            kb.setKompanija(k);
        }

        lista2.add(kb);
        for (int i = 1; ((i < listaID.size()) && (i<5)); i++) {
            q = session.createQuery("from UKontaktu as uk where uk.idKomp = " + listaID.get(i));
            uk = (UKontaktu) q.uniqueResult();

            q = session.createQuery("from Kompanija as k where k.idKomp = " + listaID.get(i));
            k = (Kompanija) q.uniqueResult();

            kb = new KompanijaBool();
            if (uk != null && uk.getUsername().equals(kor.getUsername())) {
                kb.setBool(true);
                kb.setKompanija(k);
            } else {
                kb.setBool(false);
                kb.setKompanija(k);
            }

            lista2.add(kb);

        }
        return lista2;
    }
}
