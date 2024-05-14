package edu.ihm.vue.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Signalement implements Parcelable {
    private String titreSignalement;
    private TypeSignalement typeSignalement;
    private Date dateSignalement = null;
    private Bitmap photo = null;
    private String adresse;
    private String ville;
    private int codePostal;
    private String commentaire;
    private int niveau = 0;
    private String intervenant = "";
    private Date intervention = null;
    private boolean completed = false;
    private String equipements="";

    public String getEquipements() {
        return equipements;
    }

    public void setEquipements(String equipements) {
        this.equipements = equipements;
    }



    public Signalement(){

    }
    public Signalement(String titre,TypeSignalement ty,Date d,Bitmap b,String adr,String vi,int co,
                       String com){
        this.titreSignalement=titre;
        this.dateSignalement=d;
        this.typeSignalement=ty;
        this.photo=b;
        this.adresse=adr;
        this.ville=vi;
        this.codePostal=co;
        this.commentaire=com;
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

    protected Signalement(Parcel in) {
        titreSignalement = in.readString();
        typeSignalement = TypeSignalement.valueOf(in.readString());
        adresse = in.readString();
        ville = in.readString();
        codePostal = in.readInt();
        commentaire = in.readString();
        niveau = in.readInt();
        intervenant = in.readString();
        completed = in.readByte() != 0;
        long dateMillis = in.readLong();
        if (dateMillis != -1) {
            dateSignalement = new Date(dateMillis);
        }
        long interventionMillis = in.readLong();
        if (interventionMillis != -1) {
            intervention = new Date(interventionMillis);
        }
        byte[] photoBytes = in.createByteArray();
        if (photoBytes != null) {
            photo = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
        }
        equipements = in.readString();
    }

    public static final Creator<Signalement> CREATOR = new Creator<Signalement>() {
        @Override
        public Signalement createFromParcel(Parcel in) {
            return new Signalement(in);
        }

        @Override
        public Signalement[] newArray(int size) {
            return new Signalement[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titreSignalement);
        dest.writeString(typeSignalement.name());
        dest.writeString(adresse);
        dest.writeString(ville);
        dest.writeInt(codePostal);
        dest.writeString(commentaire);
        dest.writeInt(niveau);
        dest.writeString(intervenant);
        dest.writeByte((byte) (completed ? 1 : 0));
        dest.writeLong(dateSignalement != null ? dateSignalement.getTime() : -1);
        dest.writeLong(intervention != null ? intervention.getTime() : -1);
        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            dest.writeByteArray(byteArray);
        } else {
            dest.writeByteArray(null);
        }
        dest.writeString(equipements);
    }
    @Override
    public int describeContents() {
        return 0;
    }
}
