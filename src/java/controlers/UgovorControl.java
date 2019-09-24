/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import helper.UgovorHelper;
import helper.PaketHelper;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import database.*;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Stefan
 */
@Named(value = "ugovorControl")
@SessionScoped
public class UgovorControl implements Serializable {

    private List<Kompanija> kompanije;
    private Kompanija odabranaKomp;

    private List<Paket> paketi;
    private Paket odabranPaket;

    @NotNull(message = "Odaberite datum")
    private Date datumUgovora;
    @NotNull(message = "Odaberite datum")
    private Date datumUplate;
    @NotNull(message = "Odaberite datum")
    private Date datumIsporuke;

    @Size(min = 1, message = "Morate uneti vrednost")
    @Pattern(regexp = "[0-9]*", message = "Morate uneti brojeve")
    private String vrednost;

    
    private String status;
    private List<String> statusi;

    @Size(min = 1, message = "Morate uneti opis")
    private String opis;

    @Pattern(regexp = "[0-9]*", message = "Morate uneti brojeve")
    private String kolicina;

    private boolean faktura = false;
    private boolean uplata = false;

    private UIComponent component;

    private UgovorHelper ugovorHelper;
    private PaketHelper paketHelper;

    public String getVrednost() {
        return vrednost;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public List<Kompanija> getKompanije() {
        return kompanije;
    }

    public Date getDatumIsporuke() {
        return datumIsporuke;
    }

    public void setDatumIsporuke(Date datumIsporuke) {
        this.datumIsporuke = datumIsporuke;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setKompanije(List<Kompanija> kompanije) {
        this.kompanije = kompanije;
    }

    public Kompanija getOdabranaKomp() {
        return odabranaKomp;
    }

    public void setOdabranaKomp(Kompanija odabranaKomp) {
        this.odabranaKomp = odabranaKomp;
    }

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }

    public Paket getOdabranPaket() {
        return odabranPaket;
    }

    public void setOdabranPaket(Paket odabranPaket) {
        this.odabranPaket = odabranPaket;
    }

    public Date getDatumUgovora() {
        return datumUgovora;
    }

    public void setDatumUgovora(Date datumUgovora) {
        this.datumUgovora = datumUgovora;
    }

    public Date getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(Date datumUplate) {
        this.datumUplate = datumUplate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getStatusi() {
        return statusi;
    }

    public void setStatusi(List<String> statusi) {
        this.statusi = statusi;
    }

    public boolean isFaktura() {
        return faktura;
    }

    public void setFaktura(boolean faktura) {
        this.faktura = faktura;
    }

    public boolean isUplata() {
        return uplata;
    }

    public void setUplata(boolean uplata) {
        this.uplata = uplata;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public UgovorControl() {
        ugovorHelper = new UgovorHelper();
        paketHelper = new PaketHelper();

        inic();

    }

    public void inic() {

        kompanije = ugovorHelper.getKompanije();
        paketi = paketHelper.getLista4();
        statusi = ugovorHelper.getStatusi();
    }

    public String dodajNovcani() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) {
            String poruka = "aldasld";
        }
        if (odabranaKomp == null) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Niste odabrali kompaniju"));
            return null;
        }
        

        NovcaniUgovor nu = new NovcaniUgovor();

        nu.setDatumUgovora(datumUgovora);
        nu.setDatumUplate(datumUplate);
        nu.setIdKomp(odabranaKomp.getIdKomp());
        if (uplata) {
            nu.setIzvrsenaUplata(1);
        } else {
            nu.setIzvrsenaUplata(0);
        }
        if (faktura) {
            nu.setPoslataFaktura(1);
        } else {
            nu.setPoslataFaktura(0);
        }
        nu.setStatus(status);
        int vr = Integer.parseInt(vrednost);
        nu.setVrednost(vr);
        int i = 0;
        while (i < paketi.size()) {
            Paket p = paketi.get(i++);
            if (vr >= p.getVrednost()) {
                nu.setIdPaket(p.getIdPaket());
                break;
            }
        }
        if (i == paketi.size()) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Vrednost mora biti veca od" + paketi.get(i - 1).getVrednost()));
            return null;
        }

        ugovorHelper.dodajNovcani(nu);
        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno dodat ugovor"));

        odabranaKomp = null;
        odabranPaket = null;
        datumUgovora = null;
        datumUplate = null;
        status = null;

        return null;
    }

    public String dodajDonatorski() {

        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) {
            String poruka = "aldasld";
        }
        if (odabranaKomp == null) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Niste odabrali kompaniju"));
            return null;
        }
        if (odabranPaket == null) {
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Niste odabrali paket"));
            return null;
        }

        DonatorskiUgovor du = new DonatorskiUgovor();

        du.setDatumIsporuke(datumIsporuke);
        du.setDatumUgovora(datumUgovora);
        du.setIdKomp(odabranaKomp.getIdKomp());
        du.setIdPaket(odabranPaket.getIdPaket());
        du.setOpis(opis);
        if (kolicina.isEmpty()) {
            du.setKolicina(0);
        } else {
            du.setKolicina(Integer.parseInt(kolicina));
        }
        
        du.setProcVrednost(Integer.parseInt(vrednost));
        du.setStatus(status);

        Date sum = new Date(du.getDatumUgovora().getTime() + (long) (odabranPaket.getTrajanjeUgovora() * UgovorHelper.MILLISECONDS_IN_YEAR));

        du.setDatumIsticanja(sum);

        ugovorHelper.dodajDonatorski(du);
       
        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno dodat ugovor"));

        odabranaKomp = null;
        odabranPaket = null;
        datumUgovora = null;
        datumUplate = null;
        status = null;

        return null;
    }

}
