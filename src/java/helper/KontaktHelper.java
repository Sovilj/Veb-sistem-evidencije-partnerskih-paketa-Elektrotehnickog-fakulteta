/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import beans.KompKontakt;
import database.HibernateUtil;
import database.Kompanija;
import database.Korisnik;
import database.UKontaktu;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Stefan
 */
public class KontaktHelper {
    
    Session session;
    
    public KontaktHelper(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    public List getKompKontakt(){
        List<KompKontakt> lista = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Query q = session.createQuery("from UKontaktu");
            List<UKontaktu> listaUK = (List<UKontaktu>) q.list();
            
            int i = 0;
            while(i < listaUK.size()){
                UKontaktu uk = listaUK.get(i++);
                
                q = session.createQuery("from Kompanija as k where k.idKomp ="+uk.getIdKomp());
                Kompanija k = (Kompanija) q.uniqueResult();
                
                q = session.createQuery("from Korisnik as k where k.username = '"+uk.getUsername()+"'");
                Korisnik kor = (Korisnik) q.uniqueResult();
                
                KompKontakt kk = new KompKontakt();
                kk.setKompanija(k);
                kk.setKorisnik(kor);
                lista.add(kk);
                
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
}
