/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import database.Kompanija;
import database.Korisnik;
import helper.OglasHelper;
import helper.PredHelper;
import database.Predavanje;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Stefan
 */
@Named(value = "predControl")
@SessionScoped
public class PredControl implements Serializable {

    @Size(min = 1, message = "Unesite naziv")
    private String naziv;

    @Size(min = 1, message = "Unesite opis")
    private String opis;

    @NotNull(message = "Unesite datum")
    private Date datum;

    @NotNull(message = "Unesite vreme")
    private Date vreme;

    @Size(min = 1, message = "Unesite salu")
    private String sala;

    @Size(min = 1, message = "Unesite predavaca")
    private String predavac;

    private String biografija;

    private Kompanija odabrana;

    private List<Kompanija> kompanije;
    private PredHelper helper;

    private String poruka;
    private UIComponent component;

    private List<Predavanje> predFresh;
    private List<Predavanje> predOld;

    public List<Predavanje> getPredFresh() {
        return predFresh;
    }

    public void setPredFresh(List<Predavanje> predFresh) {
        this.predFresh = predFresh;
    }

    public List<Predavanje> getPredOld() {
        return predOld;
    }

    public void setPredOld(List<Predavanje> predOld) {
        this.predOld = predOld;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getPredavac() {
        return predavac;
    }

    public void setPredavac(String predavac) {
        this.predavac = predavac;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public Kompanija getOdabrana() {
        return odabrana;
    }

    public void setOdabrana(Kompanija odabrana) {
        this.odabrana = odabrana;
    }

    public List<Kompanija> getKompanije() {
        return kompanije;
    }

    public void setKompanije(List<Kompanija> kompanije) {
        this.kompanije = kompanije;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public PredControl() {
        helper = new PredHelper();
       // pronadji();
        nadjiPredavanja();
    }

    public void pronadji() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Korisnik kor = (Korisnik) session.getAttribute("user");

        kompanije = helper.nadjiKomp(kor);

    }

    public void nadjiPredavanja() {
        predFresh = helper.nadjiFreshPred();
        predOld = helper.nadjiOldPred();

    }

    public String dodaj() {

        FacesContext context = FacesContext.getCurrentInstance();
        if (odabrana == null) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Niste odabrali kompaniju"));
            return null;
        }

        java.sql.Date sqlDatum = new java.sql.Date(datum.getTime());
        java.sql.Time sqlTime = new java.sql.Time(vreme.getTime());

        boolean ugovor = helper.provera(odabrana, sqlDatum);

        if (ugovor == true) {
            Predavanje p = new Predavanje();
            p.setBiografija(biografija);
            p.setDatum(sqlDatum);
            p.setIdKomp(odabrana.getIdKomp());
            p.setNaslovRs(naziv);
            p.setOpisRs(opis);
            p.setPredavac(predavac);
            p.setProslo(0);
            p.setSala(sala);
            p.setVreme(sqlTime);
            p.setNaslovEn("");
            p.setOpisEn("");

            helper.dodajPred(p);

            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno dodato predavanje"));

        } else {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Kompanija nema aktivan ugovor"));
        }

        return null;
    }

    public void inicClan() {
        pronadji();
    }

}
