package database;
// Generated May 28, 2018 1:27:31 PM by Hibernate Tools 4.3.1



/**
 * Paket generated by hbm2java
 */
public class Paket  implements java.io.Serializable {


     private Integer idPaket;
     private String naziv;
     private int vrednost;
     private int trajanjeUgovora;
     private int maxBroj;

    public Paket() {
    }

    public Paket(String naziv, int vrednost, int trajanjeUgovora, int maxBroj) {
       this.naziv = naziv;
       this.vrednost = vrednost;
       this.trajanjeUgovora = trajanjeUgovora;
       this.maxBroj = maxBroj;
    }
   
    public Integer getIdPaket() {
        return this.idPaket;
    }
    
    public void setIdPaket(Integer idPaket) {
        this.idPaket = idPaket;
    }
    public String getNaziv() {
        return this.naziv;
    }
    
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public int getVrednost() {
        return this.vrednost;
    }
    
    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }
    public int getTrajanjeUgovora() {
        return this.trajanjeUgovora;
    }
    
    public void setTrajanjeUgovora(int trajanjeUgovora) {
        this.trajanjeUgovora = trajanjeUgovora;
    }
    public int getMaxBroj() {
        return this.maxBroj;
    }
    
    public void setMaxBroj(int maxBroj) {
        this.maxBroj = maxBroj;
    }




}

