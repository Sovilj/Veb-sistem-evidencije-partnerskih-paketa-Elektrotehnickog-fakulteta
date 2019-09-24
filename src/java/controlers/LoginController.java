/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import database.Korisnik;
import helper.LoginHelper;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.*;
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
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @Size(min = 3, message = "Korisnisko ime mora biti minimalno 3 karaktera")
    private String username;

    @Size(min = 8, max = 12, message = "Sifra mora biti dužine 8 do 12 karaktera!")
    @Pattern(regexp = "^[[A-Z]|[a-z]][a-zA-Z0-9]*[0-9]+[a-zA-Z0-9]*$", message = "Sifra mora pocinjati slovom i sadrzati bar jedan broj.")
    private String password;

    private Korisnik k;

    private String poruka;
    private String poruka2;
    private LoginHelper helper;
    private UIComponent component;

    public LoginController() {
        this.helper = new LoginHelper();
        k = null;
        poruka = null;
    }

    public String getPoruka2() {
        return poruka2;
    }

    public Korisnik getK() {
        return k;
    }

    public void setK(Korisnik k) {
        this.k = k;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public void setPoruka2(String poruka2) {
        this.poruka2 = poruka2;
    }

    public LoginHelper getHelper() {
        return helper;
    }

    public void setHelper(LoginHelper helper) {
        this.helper = helper;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean jednoVeliko = false, triMala = false, numerik = false, spec = false, uzastopni = false;
        int mala = 0;

        /*       for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) > 'A' && password.charAt(i) < 'Z') {
                jednoVeliko = true;
                break;
            }
        }*/
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) > 'a' && password.charAt(i) < 'z') {
                mala++;
                if (mala >= 3) {
                    triMala = true;
                    break;
                }

            }
        }
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                uzastopni = true;
                break;
            }
        }

        /*    for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == '_' || password.charAt(i) == '?' || password.charAt(i)== '!') {
                spec = true;
                break;
            }
        }*/
        if (!triMala) {
            poruka = "Sifra mora sadrzati bar tri mala slova";
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));
            return null;
        }

        if (uzastopni) {
            poruka = "Sifra ne sme imati ponavljanja veca od 2";
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));
            return null;
        }

        k = helper.getUser(username);

        if (k != null) {

            String user = k.getUsername();
            String hash = null;
            String pass = k.getPassword();

            if (user.equals(username) && k.getZahtev() == 0) {
                try {
                    byte[] bytes = password.getBytes("UTF-8");
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
                if (hash.equals(pass)) {
                    poruka = null;

                    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
                    session.setAttribute("user", k);

                    switch (k.getTip()) {
                        case 1:
                            return "admin";

                        case 2:
                            return "menadzer";

                        case 3:
                            return "clan";
                        default:
                            return null;
                    }

                } else {
                    poruka = "Pogresna sifra";
                    context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));

                    password = null;
                    return null;
                }
            } else {
                poruka = "Jos uvek nije odobren zahtev";
                context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));

                return null;
            }
        } else {
            poruka = "Pogresno korisnicko ime";
            context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));
            return null;
        }

    }

    public String logOut() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        poruka = "";
        k = null;
        return "/index";

    }

}
