package fr.epsi.applimspr;

import org.json.JSONObject;

import java.io.Serializable;

public class Promotion implements Serializable {

    private String pctPromo="";
    private String libelle = "";
    private String dateExpiration="";


    public Promotion(){

    }



    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getPctPromo() {
        return pctPromo;
    }

    public void setPctPromo(String pctPromo) {
        this.pctPromo = pctPromo;
    }
}
