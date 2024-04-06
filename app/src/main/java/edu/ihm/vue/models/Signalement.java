package edu.ihm.vue.models;

import android.graphics.Bitmap;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Signalement {
    private String titreSignalement;
    private TypeSignalement typeSignalement;
    private Date dateSignalement=null;
    private Bitmap photo=null;
    private String Adresse;
    private String ville;
    private int codePostal;
    private String commentaire;

    public String getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

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

    public String getDateSignalement() {
        if (dateSignalement != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(dateSignalement);
        } else {
            return "";
        }
    }

    public void setDateSignalement(Date dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public Bitmap getPhoto() {
        return photo;
    }
    public void setPhoto(Bitmap photo) {
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
