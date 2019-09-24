/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import helper.RegHelper;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import database.*;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Stefan
 */
@Named(value = "prihvatiControl")
@SessionScoped
public class PrihvatiControl implements Serializable {

    private List<Korisnik> korisnici;               //korisnici sa zahtevom = 1
    private Korisnik odabran;
    private RegHelper helper;

    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public List<Korisnik> getKorisnici() {
        return korisnici;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    public Korisnik getOdabran() {
        return odabran;
    }

    public void setOdabran(Korisnik odabran) {
        this.odabran = odabran;
    }

    public PrihvatiControl() {
        helper = new RegHelper();
        korisnici = helper.getKorisnik();
    }

    public String prihvatiZ() {

        odabran.setZahtev(0);

        helper.prihvati(odabran);
        korisnici = helper.getKorisnik();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno prihvaceno"));
        return null;

    }

}
