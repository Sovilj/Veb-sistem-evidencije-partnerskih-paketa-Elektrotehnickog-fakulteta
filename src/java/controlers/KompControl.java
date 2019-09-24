/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import helper.KompHelper;
import database.Kompanija;
import database.Korisnik;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Stefan
 */
@Named(value = "kompControl")
@SessionScoped
public class KompControl implements Serializable {
    @Size(min = 1, message = "Obavezno polje")
    private String naziv;
    
    @Size(min = 1, message = "Obavezno polje")
    private String adresa;
    
    @Size(min = 1, message = "Obavezno polje")
    private String grad;
    
    @Size(min = 1, message = "Obavezno polje")
    @Pattern(regexp = "[0-9]*" , message="Morate uneti brojeve")
    private String posta;
    
    @Size(min = 1, message = "Obavezno polje")
    private String drzava;
    
    @Size(min = 1, message = "Obavezno polje")
    @Pattern(regexp = "[0-9-]*" , message="Morate uneti brojeve")
    private String racun;
    
    private String valuta = "RSD";
    
    @Size(min = 1, message = "Obavezno polje")
    @Pattern(regexp = "[0-9]*" , message="Morate uneti brojeve")
    private String PIB;
    
    @Size(min = 1, message = "Obavezno polje")
    @Pattern(regexp = "^([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,3})|([0-9-/ ]*)$", message = "Unesite email ili broj telefona")
    private String kontakt1;
    
    @Pattern(regexp = "^([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,3})|([0-9-/ ]*)$", message = "Unesite email ili broj telefona")
    private String kontakt2;
    
    @Pattern(regexp = "^([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,3})|([0-9-/ ]*)$", message = "Unesite email ili broj telefona")
    private String kontakt3;
    
    @Pattern(regexp = "^([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,3})|([0-9-/ ]*)$", message = "Unesite email ili broj telefona") 
    private String kontakt4;
    
    @Pattern(regexp = "^([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,3})|([0-9-/ ]*)$", message = "Unesite email ili broj telefona")
    private String kontakt5;
    
    @Size(min = 1, message = "Obavezno polje")
    private String kontOsoba;
    
    @Size(min = 1, message = "Obavezno polje")
    @Pattern(regexp = "^[0-9-/ ]*$", message = "Unesite broj telefona")
    private String kontOsobaTel;
    
    @Size(min = 1, message = "Obavezno polje")
    @Pattern(regexp = "^([a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,3})$", message = "Unesite email ")
    private String kontOsobaEmail;
    
    @Size(min = 1, message = "Obavezno polje")
    private String slikaLink;
   
    
    private String opis;
    
    @Size(min = 1, message = "Obavezno polje")
    private String webLink;
    
    
    
    private KompHelper helper ;
    private String poruka;
    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    
    
    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getPosta() {
        return posta;
    }

    public void setPosta(String posta) {
        this.posta = posta;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getRacun() {
        return racun;
    }

    public void setRacun(String racun) {
        this.racun = racun;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public String getKontakt1() {
        return kontakt1;
    }

    public void setKontakt1(String kontakt1) {
        this.kontakt1 = kontakt1;
    }

    public String getKontakt2() {
        return kontakt2;
    }

    public void setKontakt2(String kontakt2) {
        this.kontakt2 = kontakt2;
    }

    public String getKontakt3() {
        return kontakt3;
    }

    public void setKontakt3(String kontakt3) {
        this.kontakt3 = kontakt3;
    }

    public String getKontakt4() {
        return kontakt4;
    }

    public void setKontakt4(String kontakt4) {
        this.kontakt4 = kontakt4;
    }

    public String getKontakt5() {
        return kontakt5;
    }

    public void setKontakt5(String kontakt5) {
        this.kontakt5 = kontakt5;
    }

    public String getKontOsoba() {
        return kontOsoba;
    }

    public void setKontOsoba(String kontOsoba) {
        this.kontOsoba = kontOsoba;
    }

    public String getKontOsobaTel() {
        return kontOsobaTel;
    }

    public void setKontOsobaTel(String kontOsobaTel) {
        this.kontOsobaTel = kontOsobaTel;
    }

    public String getKontOsobaEmail() {
        return kontOsobaEmail;
    }

    public void setKontOsobaEmail(String kontOsobaEmail) {
        this.kontOsobaEmail = kontOsobaEmail;
    }

    public String getSlikaLink() {
        return slikaLink;
    }

    public void setSlikaLink(String slikaLink) {
        this.slikaLink = slikaLink;
    }
    
    
            
            
    public KompControl() {
        
        helper = new KompHelper();
    }
    
    public String dodaj(){
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        Korisnik kor =(Korisnik)session.getAttribute("user");
        
        Kompanija komp = new Kompanija();
        komp.setNaziv(naziv);
        komp.setAdresa(adresa);
        komp.setGrad(grad);
        komp.setPostanskiBroj(posta);
        komp.setDrzava(drzava);
        komp.setRacun(racun);
        komp.setValuta(valuta);
        komp.setPib(PIB);
        komp.setKontakt1(kontakt1);
        komp.setKontakt2(kontakt2);
        komp.setKontakt3(kontakt3);
        komp.setKontakt4(kontakt4);
        komp.setKontakt5(kontakt5);
        komp.setKontOsoba(kontOsoba);
        komp.setKontTelef(kontOsobaTel);
        komp.setKontEmail(kontOsobaEmail);
        komp.setLogoLink(slikaLink);
        komp.setOpis(opis);
        komp.setWebLink(webLink);
        
        helper.setKomp(komp);
        helper.setKontakt(komp,kor);
        
        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno dodata kompanija"));
       
        
        return null;
    }
    
}
