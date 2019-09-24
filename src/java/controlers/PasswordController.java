/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import database.Korisnik;
import helper.LoginHelper;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.*;

/**
 *
 * @author Stefan
 */
@Named(value = "passwordController")
@SessionScoped
public class PasswordController implements Serializable {

    @Size(min = 3, message = "Korisnicko ime mora biti najmanje 3 karaktera")
    private String username;

    @Size(min = 8, max = 12, message = "Sifra mora biti dužine 8 do 12 karaktera!")
    @Pattern(regexp = "^[[A-Z]|[a-z]][a-zA-Z0-9]*$", message = "Sifra mora pocinjati slovom.")
    private String oldPass;

    @Size(min = 8, max = 12, message = "Sifra ime mora biti dužine 8 do 12 karaktera!")
    @Pattern(regexp = "^[[A-Z]|[a-z]][a-zA-Z0-9]*$", message = "Sifra mora pocinjati slovom.")
    private String newPass;

    private String poruka;
    private LoginHelper helper;
    private boolean uspeh = false;
    private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }

    public PasswordController() {
        this.helper = new LoginHelper();
    }

    public boolean isUspeh() {
        return uspeh;
    }

    public void setUspeh(boolean uspeh) {
        this.uspeh = uspeh;
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

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String change() {
        FacesContext context = FacesContext.getCurrentInstance();
        Korisnik k = helper.getUser(username);
        if (k != null) {
            String user = k.getUsername();
            String pass = k.getPassword();
            String oldHash = null;
            String newHash = null;

            if (user.equals(username)) {
                try {
                    byte[] bytes = oldPass.getBytes("UTF-8");
                    MessageDigest md;
                    md = MessageDigest.getInstance("MD5");

                    byte[] digest = md.digest(bytes);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < digest.length; ++i) {
                        sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
                    }

                    oldHash = sb.toString();

                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (oldHash.equals(pass)) {

                    try {
                        byte[] bytes = newPass.getBytes("UTF-8");
                        MessageDigest md;
                        md = MessageDigest.getInstance("MD5");

                        byte[] digest = md.digest(bytes);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < digest.length; ++i) {
                            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
                        }

                        newHash = sb.toString();

                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (oldHash.equals(newHash)) {
                        poruka = "Nova sifra ne moze biti kao stara";
                        context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN, "WARNING", poruka));
                    } else {

                        helper.setPass(username, newHash);

                        poruka = null;
                        uspeh = true;

                        return "index";
                    }

                } else {
                    poruka = "Pogresna lozinka";
                     context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));
                }

            } else {
                poruka = "Pogresno korisnicko ime";
                 context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));
            }

        } else {
            poruka = "Pogresno Korisnicko ime";
             context.addMessage(component.getClientId(), new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", poruka));
        }

        return null;
    }

}
