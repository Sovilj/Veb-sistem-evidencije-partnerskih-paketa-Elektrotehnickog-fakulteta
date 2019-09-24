/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import database.*;
import java.util.List;
/**
 *
 * @author Stefan
 */
public class PaketStavka {
    private Paket paket;
    private List<Stavka> stavke;

    public PaketStavka() {
    }

    public PaketStavka(Paket paket, List<Stavka> stavke) {
        this.paket = paket;
        this.stavke = stavke;
    }

    
    
    public Paket getPaket() {
        return paket;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
    }

    public List<Stavka> getStavke() {
        return stavke;
    }

    public void setStavke(List<Stavka> stavke) {
        this.stavke = stavke;
    }
    
    
    
}
