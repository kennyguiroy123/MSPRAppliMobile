package fr.epsi.applimspr;

import org.json.JSONObject;

import java.io.Serializable;

public class Promotion implements Serializable {

    private String id="";
    private String libelle = "";
    private String dateExpiration="";


    public Promotion(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
