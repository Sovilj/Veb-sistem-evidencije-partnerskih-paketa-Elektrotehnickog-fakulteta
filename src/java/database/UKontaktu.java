package database;
// Generated May 28, 2018 1:27:31 PM by Hibernate Tools 4.3.1



/**
 * UKontaktu generated by hbm2java
 */
public class UKontaktu  implements java.io.Serializable {


     private Integer idKontakt;
     private String username;
     private int idKomp;

    public UKontaktu() {
    }

    public UKontaktu(String username, int idKomp) {
       this.username = username;
       this.idKomp = idKomp;
    }
   
    public Integer getIdKontakt() {
        return this.idKontakt;
    }
    
    public void setIdKontakt(Integer idKontakt) {
        this.idKontakt = idKontakt;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public int getIdKomp() {
        return this.idKomp;
    }
    
    public void setIdKomp(int idKomp) {
        this.idKomp = idKomp;
    }




}


