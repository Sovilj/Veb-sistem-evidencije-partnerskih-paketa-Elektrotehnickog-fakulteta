/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import database.*;
/**
 *
 * @author Stefan
 */
public class OglasLogo {
    
    private Oglas oglas;
    private String logo;

    public OglasLogo() {
    }

    
    
    public OglasLogo(Oglas oglas, String logo) {
        this.oglas = oglas;
        this.logo = logo;
    }

    
    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
}
