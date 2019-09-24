/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import beans.KompKontakt;
import helper.KontaktHelper;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Stefan
 */
@Named(value = "kontaktControl")
@Dependent
public class KontaktControl {

    private List<KompKontakt> listaKompKontakt;
    
    private KontaktHelper helper = null;

    public List<KompKontakt> getListaKompKontakt() {
        return listaKompKontakt;
    }

    public void setListaKompKontakt(List<KompKontakt> listaKompKontakt) {
        this.listaKompKontakt = listaKompKontakt;
    }
    
    
    
    
    
    public KontaktControl() {
        helper = new KontaktHelper();
        pronadji();
    }
    public void pronadji(){
    
        listaKompKontakt = helper.getKompKontakt();
    }
    
}
