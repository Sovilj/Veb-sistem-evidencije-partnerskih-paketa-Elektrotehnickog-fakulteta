/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.KompanijaBool;
import database.DonatorskiUgovor;
import database.HibernateUtil;
import database.Korisnik;
import helper.PrikazHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import database.Kompanija;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Stefan
 */
@Named(value = "prikazControl")
@SessionScoped
public class PrikazControl implements Serializable {

    private List<KompanijaBool> kompanije1;
    private List<KompanijaBool> kompanije2;
    private PrikazHelper helper;
    private String poruka;

    
    
    public List<KompanijaBool> getKompanije2() {
        return kompanije2;
    }

    public void setKompanije2(List<KompanijaBool> kompanije2) {
        this.kompanije2 = kompanije2;
    }
    
    
    

    public List<KompanijaBool> getKompanije1() {
        return kompanije1;
    }

    public void setKompanije1(List<KompanijaBool> kompanije1) {
        this.kompanije1 = kompanije1;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public PrikazControl() {
        helper = new PrikazHelper();
        prikazi1();
        prikazi2();

    }

    public void prikazi1() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Korisnik kor = (Korisnik) session.getAttribute("user");

        List<Integer> listaID = null;
        listaID = helper.getFreshKomp();

        kompanije1 = helper.getLista1(kor, listaID);
        
       // poruka = "Lista:";
      //  poruka += "br: " + listaID.get(0) + listaID.get(1) + listaID.get(2) + listaID.get(3)+listaID.get(4);

        
       
    }
    
     public void prikazi2() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Korisnik kor = (Korisnik) session.getAttribute("user");

        List<Integer> listaIDD = null;
        listaIDD = helper.getSoonKomp();

        kompanije2 = helper.getLista2(kor, listaIDD);
        
       // poruka += "Lista:";
      //  poruka += "br: " + listaIDD.get(0) +listaIDD.get(1)+listaIDD.get(2)+listaIDD.get(3) ;

    }
     
      
}
