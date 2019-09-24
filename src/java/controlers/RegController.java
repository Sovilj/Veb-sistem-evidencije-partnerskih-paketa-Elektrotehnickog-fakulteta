/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import database.Korisnik;
import helper.LoginHelper;
import helper.RegHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "regController")
@SessionScoped
public class RegController implements Serializable {

    @Size(min = 1, message = "Unesite ime")
    private String ime;

    @Size(min = 1, message = "Unesite ime")
    private String prezime;

    @Size(min = 1, message = "Unesite email")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,3}$", message = "Unesite email pravilno")
    private String email;

    @Size(min = 1, message = "Unesite instituciju")
    private String institucija;

    @Size(min = 3, message = "Unesite korisnicko ime")
    private String username;

    @Size(min = 8, max = 12, message = "Unesite lozinku od 8 do 12 karaktera")
    @Pattern(regexp = "^[[A-Z]|[a-z]][a-zA-Z0-9]*$", message = "Sifra mora pocinjati slovom.")
    private String password1;

    @Size(min = 8, max = 12, message = "Unesite lozinku od 8 do 12 karaktera")
    @Pattern(regexp = "^[[A-Z]|[a-z]][a-zA-Z0-9]*$", message = "Sifra mora pocinjati slovom.")
    private String password2;

    private String pol = "muski";

    private Date rodjendan = new Date();
    private int tip = 3;

    private String poruka;
    private LoginHelper logHelper;
    private RegHelper regHelper;
    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public RegController() {
        this.logHelper = new LoginHelper();
        this.regHelper = new RegHelper();
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Date getRodjendan() {
        return rodjendan;
    }

    public void setRodjendan(Date rodjendan) {
        this.rodjendan = rodjendan;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public String getHash(String pass) {
        String hash = null;
        try {
            byte[] bytes = pass.getBytes("UTF-8");
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");

            byte[] digest = md.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; ++i) {
                sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
            }

            hash = sb.toString();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;

    }

    public String registracija() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (password1.equals(password2)) {
            String pass = getHash(password1);
            Korisnik k = logHelper.getUser(username);
            if (k == null) {
                k = new Korisnik();
                k.setIme(ime);
                k.setPrezime(prezime);
                k.setEmail(email);
                k.setInstitucija(institucija);
                k.setUsername(username);
                k.setDatumRodjenja(rodjendan);
                k.setPol(pol);
                k.setPassword(pass);
                k.setTip(tip);

                Korisnik user = (Korisnik) session.getAttribute("user");
                if (user != null && user.getTip() == 1) {
                    k.setZahtev(0);
                } else {
                    k.setZahtev(1);
                }

                regHelper.setZahtev(k);

                if (user != null && user.getTip() == 1) {
                    context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno dodat korisnik"));

                } else {
                    context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "Uspesno poslat zahtev"));
                }

            } else {
                poruka = "Korisnicko ime vec postoji";
                context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));
            }
        } else {
            poruka = "Sifre moraju biti iste";
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING", poruka));
        }

        return null;
    }

}
