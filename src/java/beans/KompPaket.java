/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import database.*;
import java.util.ArrayList;
/**
 *
 * @author Stefan
 */
public class KompPaket {
    
    private Paket paket;
    private boolean logo = false;
    private boolean baner = false;
    private boolean opis = false;
    private List<Kompanija> kompanije = new ArrayList<>();

    public boolean isOpis() {
        return opis;
    }

    public void setOpis(boolean opis) {
        this.opis = opis;
    }

    public Paket getPaket() {
        return paket;
    }

    public boolean isLogo() {
        return logo;
    }

    public void setLogo(boolean logo) {
        this.logo = logo;
    }

    public boolean isBaner() {
        return baner;
    }

    public void setBaner(boolean baner) {
        this.baner = baner;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public List<Kompanija> getKompanije() {
        return kompanije;
    }

    public void setKompanije(List<Kompanija> kompanije) {
        this.kompanije = kompanije;
    }
    
    
    
    
}
