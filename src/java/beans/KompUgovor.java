/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import database.*;
import java.util.Date;

/**
 *
 * @author Stefan
 */
public class KompUgovor {
    
    private Kompanija kompanija;
    private String tipUgovora;
    private Date datumSklapanja;
    private Date datumIsticanja;
    private String tipPaketa;
    private boolean istekao;

    public Kompanija getKompanija() {
        return kompanija;
    }

    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    public String getTipUgovora() {
        return tipUgovora;
    }

    public void setTipUgovora(String tipUgovora) {
        this.tipUgovora = tipUgovora;
    }

    public Date getDatumSklapanja() {
        return datumSklapanja;
    }

    public void setDatumSklapanja(Date datumSklapanja) {
        this.datumSklapanja = datumSklapanja;
    }

    public Date getDatumIsticanja() {
        return datumIsticanja;
    }

    public void setDatumIsticanja(Date datumIsticanja) {
        this.datumIsticanja = datumIsticanja;
    }

    public String getTipPaketa() {
        return tipPaketa;
    }

    public void setTipPaketa(String tipPaketa) {
        this.tipPaketa = tipPaketa;
    }

    
   
    public boolean isIstekao() {
        return istekao;
    }

    public void setIstekao(boolean istekao) {
        this.istekao = istekao;
    }
    
    
    
    
}
