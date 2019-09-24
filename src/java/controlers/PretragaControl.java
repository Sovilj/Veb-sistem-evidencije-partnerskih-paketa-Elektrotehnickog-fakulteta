/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.KompUgovor;
import helper.KompHelper;
import helper.PretragaHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import database.Kompanija;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Stefan
 */
@Named(value = "pretragaControl")
@SessionScoped
public class PretragaControl implements Serializable {

    private String paket = "";
    private String komp = "";
    private boolean vazeci = false;

    private PretragaHelper helper = null;
    private KompHelper kompHelper = null;

    private List<Kompanija> listaKomp = null;

    private Kompanija odabrana;
    private boolean izmena = true;

    private List<KompUgovor> listKompUgovor = null;

    private UIComponent component;

    public List<KompUgovor> getListKompUgovor() {
        return listKompUgovor;
    }

    public void setListKompUgovor(List<KompUgovor> listKompUgovor) {
        this.listKompUgovor = listKompUgovor;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public boolean isIzmena() {
        return izmena;
    }

    public void setIzmena(boolean izmena) {
        this.izmena = izmena;
    }

    public Kompanija getOdabrana() {
        return odabrana;
    }

    public void setOdabrana(Kompanija odabrana) {
        this.odabrana = odabrana;
    }

    public List<Kompanija> getListaKomp() {
        return listaKomp;
    }

    public void setListaKomp(List<Kompanija> listaKomp) {
        this.listaKomp = listaKomp;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getKomp() {
        return komp;
    }

    public void setKomp(String komp) {
        this.komp = komp;
    }

    public boolean isVazeci() {
        return vazeci;
    }

    public void setVazeci(boolean vazeci) {
        this.vazeci = vazeci;
    }

    public PretragaControl() {
        helper = new PretragaHelper();
        kompHelper = new KompHelper();
    }

    public String pretrazi() {
        listaKomp = helper.pretraga(paket, komp, vazeci);

        return null;
    }

    public String izmeniBool() {
        izmena = !izmena;
        return null;
    }

    public String spremiOdabranu() {
        if (odabrana != null) {
            listKompUgovor = helper.getKompUgovor(odabrana);
            return "dosije";
        } else {
            FacesContext  context = FacesContext.getCurrentInstance();
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING", "Niste odabrali kompaniju"));
            return null;
        }
       

    }

    public void snimi() {
        if (izmena == false) {
            kompHelper.updateKomp(odabrana);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno snimljeno"));
            izmena = true;

        }

    }

}
