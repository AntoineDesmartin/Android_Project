package edu.ihm.vue.models;

import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Signalement {
    protected String titreSignalement;
    protected TypeSignalement typeSignalement;
    protected Date dateIncident = null;
    protected Bitmap photo = null;

    protected String adresse;
    protected String ville;
    protected int codePostal;
    protected String commentaire;
    protected int niveau = 0;
    protected String intervenant = "";
    protected Date intervention = null;
    protected boolean completed = false;
    protected String equipements = "";


    public Signalement() {

    }

    public Signalement(String titre, TypeSignalement ty, Date d, Bitmap b, String adr, String vi, int co,
                       String com) {
        this.titreSignalement = titre;
        this.dateIncident = d;
        this.typeSignalement = ty;
        this.photo = b;
        this.adresse = adr;
        this.ville = vi;
        this.codePostal = co;
        this.commentaire = com;
    }

    public String getEquipements() {
        return equipements;
    }

    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(String intervenant) {
        this.intervenant = intervenant;
    }

    public String getIntervention() {
        if (intervention != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(intervention);
        } else {
            return "";
        }
    }

    public void setIntervention(Date intervention) {
        this.intervention = intervention;
    }

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

    public String getDateIncident() {
        if (dateIncident != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(dateIncident);
        } else {
            return "";
        }
    }

    public void setDateIncident(Date dateIncident) {
        this.dateIncident = dateIncident;
    }
    public Bitmap getPhoto() {
        return photo;
    }
    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
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
    public enum TypeSignalement {DECHETS, ENCOMBREMENTS}

}