package edu.ihm.vue.models;

import java.util.Date;

public class Signalement {
    private String titreSignalement;
    private TypeSignalement typeSignalement;
    private Date dateSignalement;
    private int[] photo;
    private String Adresse;
    private String ville;
    private int codePostal;

    public String getTitreSignalement() {
        return titreSignalement;
    }

    public void setTitreSignalement(String titreSignalement) {
        this.titreSignalement = titreSignalement;
    }

    public TypeSignalement getTypeSignalement() {
        return typeSignalement;
    }

    public void setTypeSignalement(TypeSignalement typeSignalement) {
        this.typeSignalement = typeSignalement;
    }

    public Date getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public int[] getPhoto() {
        return photo;
    }

    public void setPhoto(int[] photo) {
        this.photo = photo;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public enum TypeSignalement{DECHETS, ENCOMBREMENTS}
}
