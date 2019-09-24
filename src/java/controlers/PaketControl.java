/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.PaketStavka;
import beans.KompPaket;
import database.Ima;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import database.Paket;
import helper.PaketHelper;
import database.Stavka;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Stefan
 */
@Named(value = "paketControl")
@SessionScoped
public class PaketControl implements Serializable {

    private List<Paket> paketi;
    private List<Paket> paketiVO;
    private String sort = "vo";
    private PaketHelper helper;
    private List<Ima> listaIma;
    List<PaketStavka> paketStavka;

    private String poruka;

    private List<KompPaket> kompPaketi;

    @Size(min = 1, message = "Morate uneti naziv")
    private String naziv;

    @Size(min = 1, message = "Morate uneti vrednost")
    @Pattern(regexp = "[0-9]*", message = "Morate uneti brojeve")
    private String vrednost;

    @Size(min = 1, message = "Morate uneti trajanje")
    @Pattern(regexp = "[0-9]*", message = "Morate uneti brojeve")
    private String trajanje;

    @Size(min = 1, message = "Morate uneti max")
    @Pattern(regexp = "[0-9]*", message = "Morate uneti brojeve")
    private String max;

    @NotNull(message = "Odaberite bar jednu stavku")
    private DualListModel<String> statusi;

    private List<String> source;
    private List<String> target;
    List<Stavka> listaStavki;
    List<Stavka> listaOdabranih;

    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public DualListModel<String> getStatusi() {
        return statusi;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVrednost() {
        return vrednost;
    }

    public List<Paket> getPaketiVO() {
        return paketiVO;
    }

    public void setPaketiVO(List<Paket> paketiVO) {
        this.paketiVO = paketiVO;
    }

    public void setVrednost(String vrednost) {
        this.vrednost = vrednost;
    }

    public String getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(String trajanje) {
        this.trajanje = trajanje;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public void setStatusi(DualListModel<String> statusi) {
        this.statusi = statusi;
    }

    public List<String> getSource() {
        return source;
    }

    public void setSource(List<String> source) {
        this.source = source;
    }

    public List<String> getTarget() {
        return target;
    }

    public void setTarget(List<String> target) {
        this.target = target;
    }

    public List<KompPaket> getKompPaketi() {
        return kompPaketi;
    }

    public void setKompPaketi(List<KompPaket> kompPaketi) {
        this.kompPaketi = kompPaketi;
    }

    public List<Ima> getListaIma() {
        return listaIma;
    }

    public void setListaIma(List<Ima> listaIma) {
        this.listaIma = listaIma;
    }

    public List<PaketStavka> getPaketStavka() {
        return paketStavka;
    }

    public void setPaketStavka(List<PaketStavka> paketStavka) {
        this.paketStavka = paketStavka;
    }

    public List<Stavka> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<Stavka> listaStavki) {
        this.listaStavki = listaStavki;
    }

    public List<Stavka> getListaOdabranih() {
        return listaOdabranih;
    }

    public void setListaOdabranih(List<Stavka> listaOdabranih) {
        this.listaOdabranih = listaOdabranih;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }

    public PaketControl() {
        helper = new PaketHelper();
        paketi = helper.getLista4();
        paketiVO = helper.getLista4();
        prikazi();

        dodajKompPaket();

        init();
    }

    public void prikazi() {

        
        listaStavki = helper.getListaStavki();
        listaIma = helper.getListaIma();

        paketStavka = new ArrayList<>();
        
        int i = 0;
        while (i < paketi.size()) {
            List<Stavka> ls = new ArrayList<>();
            PaketStavka ps = new PaketStavka();
            ps.setPaket(paketi.get(i));
            int j = 0;
            while (j < listaIma.size() ) {
                if (listaIma.get(j).getIdPaket() == paketi.get(i).getIdPaket()) {
                    int r = 0;
                    while (r < listaStavki.size()) {
                        if (listaStavki.get(r).getIdStavka() == listaIma.get(j).getIdStavka()) {
                            ls.add(listaStavki.get(r));
                            break;
                        }
                        r++;
                    }

                }
                j++;
            }
            ps.setStavke(ls);
           
            paketStavka.add(ps);
            i++;

        }

    }

    public void sortiraj() {

        if (sort.equals("ar")) {
            paketi = helper.getLista1();
        }
        if (sort.equals("ao")) {
            paketi = helper.getLista2();
        }
        if (sort.equals("vr")) {
            paketi = helper.getLista3();
        }
        if (sort.equals("vo")) {
            paketi = helper.getLista4();
        }
        
        prikazi();

        poruka = " prosao sort";
    }

    public void dodajKompPaket() {
        kompPaketi = helper.nadjiKompPaket(paketi);
        if (kompPaketi.isEmpty()) {
            poruka = "prazno";
        }
    }

    public void init() {
        source = new ArrayList<>();
        listaStavki = helper.getListaStavki();
        int i = 0;
        while (i < listaStavki.size()) {
            source.add(listaStavki.get(i++).getNaziv());
        }

        target = new ArrayList<>();
        statusi = new DualListModel<>(source, target);
        listaOdabranih = new ArrayList<>();

    }

    public String dodajPaket() {
        int i = 0;
        while (i < statusi.getTarget().size()) {
            String s = statusi.getTarget().get(i++);
            int j = 0;
            while (!listaStavki.get(j).getNaziv().equals(s)) {
                j++;
            }
            listaOdabranih.add(listaStavki.get(j));

        }
        Paket p = new Paket();
        p.setMaxBroj(Integer.parseInt(max));
        p.setNaziv(naziv);
        p.setTrajanjeUgovora(Integer.parseInt(trajanje));
        p.setVrednost(Integer.parseInt(vrednost));

        helper.savePaket(p);

        int r = 0;
        while (r < listaOdabranih.size()) {
            Stavka st = listaOdabranih.get(r++);
            Ima ima = new Ima();
            ima.setIdPaket(p.getIdPaket());
            ima.setIdStavka(st.getIdStavka());
            helper.saveIma(ima);

        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno dodat paket"));

        listaOdabranih.clear();
        target.clear();

        return null;
    }
}
