/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import database.Kompanija;
/**
 *
 * @author Stefan
 */
public class KompanijaBool {
    
    private Kompanija kompanija;
    private boolean bool;

    
    public KompanijaBool() {
    }

    
    public KompanijaBool(Kompanija kompanija, boolean bool) {
        this.kompanija = kompanija;
        this.bool = bool;
    }
    
    

    public Kompanija getKompanija() {
        return kompanija;
    }

    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
    
}
