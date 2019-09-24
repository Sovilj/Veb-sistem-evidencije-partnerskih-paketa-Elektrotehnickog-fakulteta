/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.OglasLogo;
import helper.KompHelper;
import database.Kompanija;
import database.Korisnik;
import database.Oglas;
import helper.OglasHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Stefan
 */
@Named(value = "oglasControl")
@SessionScoped
public class OglasControl implements Serializable {

    @Size(min = 3, message = "Unesite naziv min 3 karaktera")
    private String naziv;

    @Size(min = 1, message = "Morate uneti opis")
    private String opis;

    @Size(min = 1, message = "Morate odabrati bar 1")
    private String[] ponuda;

    private Kompanija odabrana;

    private List<Kompanija> kompanije;

    private List<OglasLogo> oglasLogo;

    private OglasHelper helper;

    private String poruka;
    private UIComponent component;

    private List<Oglas> oglasi;

    public List<Oglas> getOglasi() {
        return oglasi;
    }

    public List<OglasLogo> getOglasLogo() {
        return oglasLogo;
    }

    public void setOglasLogo(List<OglasLogo> oglasLogo) {
        this.oglasLogo = oglasLogo;
    }

    public void setOglasi(List<Oglas> oglasi) {
        this.oglasi = oglasi;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public String[] getPonuda() {
        return ponuda;
    }

    public void setPonuda(String[] ponuda) {
        this.ponuda = ponuda;
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

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
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

    public OglasControl() {
        helper = new OglasHelper();
        // pronadjiKomp();

        pronadjiOglase();

    }

    public void pronadjiKomp() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Korisnik kor = (Korisnik) session.getAttribute("user");

        kompanije = helper.nadjiKomp(kor);

    }

    public void pronadjiOglase() {

        oglasi = helper.nadjiOglase();
        KompHelper kompHelper = new KompHelper();

        List<Kompanija> listaKomp = kompHelper.getKompanije();
        oglasLogo = new ArrayList<>();
        int i = 0;
        while (i < oglasi.size()) {
            OglasLogo ol = new OglasLogo();
            ol.setOglas(oglasi.get(i));
            int j = 0;
            while (j < listaKomp.size()) {
                if (oglasi.get(i).getIdKomp() == listaKomp.get(j).getIdKomp()) {
                    ol.setLogo(listaKomp.get(j).getLogoLink());
                    break;
                }
                j++;
            }
            oglasLogo.add(ol);
            i++;

        }

    }

    public String dodaj() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (odabrana == null) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Niste odabrali kompaniju"));
            return null;
        }
        Date datum = new Date(System.currentTimeMillis());
        Time time = new Time(System.currentTimeMillis());

        boolean ugovor = helper.provera(odabrana, datum);

        if (ugovor == true) {
            Oglas o = new Oglas();
            o.setDatum(datum);
            o.setIdKomp(odabrana.getIdKomp());
            o.setNaslov(naziv);
            o.setOpis(opis);

            String po = "";
            for (int i = 0; i < ponuda.length; i++) {
                po += ponuda[i];
                if (ponuda[i].equals("praksa")) {
                    o.setStrucnaPraksa(1);
                }
                if (ponuda[i].equals("posao")) {
                    o.setZaposlenje(1);
                }

            }

            o.setVreme(time);

            helper.postaviOglas(o);
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno postavljen oglas"));

        } else {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING!", "Kompanija nema aktivan ugovor"));

        }

        return null;

    }

    public void inicClan() {
        pronadjiKomp();
    }

}
