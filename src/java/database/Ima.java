package database;
// Generated May 28, 2018 1:27:31 PM by Hibernate Tools 4.3.1



/**
 * Ima generated by hbm2java
 */
public class Ima  implements java.io.Serializable {


     private Integer idIma;
     private int idStavka;
     private int idPaket;

    public Ima() {
    }

    public Ima(int idStavka, int idPaket) {
       this.idStavka = idStavka;
       this.idPaket = idPaket;
    }
   
    public Integer getIdIma() {
        return this.idIma;
    }
    
    public void setIdIma(Integer idIma) {
        this.idIma = idIma;
    }
    public int getIdStavka() {
        return this.idStavka;
    }
    
    public void setIdStavka(int idStavka) {
        this.idStavka = idStavka;
    }
    public int getIdPaket() {
        return this.idPaket;
    }
    
    public void setIdPaket(int idPaket) {
        this.idPaket = idPaket;
    }




}


