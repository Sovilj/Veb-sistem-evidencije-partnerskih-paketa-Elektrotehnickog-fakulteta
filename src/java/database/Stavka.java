package database;
// Generated May 28, 2018 1:27:31 PM by Hibernate Tools 4.3.1



/**
 * Stavka generated by hbm2java
 */
public class Stavka  implements java.io.Serializable {


     private Integer idStavka;
     private String naziv;
     private String opis;

    public Stavka() {
    }

    public Stavka(String naziv, String opis) {
       this.naziv = naziv;
       this.opis = opis;
    }
   
    public Integer getIdStavka() {
        return this.idStavka;
    }
    
    public void setIdStavka(Integer idStavka) {
        this.idStavka = idStavka;
    }
    public String getNaziv() {
        return this.naziv;
    }
    
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public String getOpis() {
        return this.opis;
    }
    
    public void setOpis(String opis) {
        this.opis = opis;
    }




}


