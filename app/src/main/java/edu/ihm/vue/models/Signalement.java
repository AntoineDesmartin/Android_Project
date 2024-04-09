package edu.ihm.vue.models;

import android.graphics.Bitmap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Signalement {
    private String title;
    private TypeSignalement typeSignalement;

    public void setBlockage() {
        isBlockage = (typeSignalement==TypeSignalement.ENCOMBREMENTS);
    }

    public boolean isBlockage() {
        return isBlockage;
    }

    public void setBlockage(boolean blockage) {
        isBlockage = blockage;
    }

    boolean isBlockage;
    private Date date =null;
    private Bitmap photo=null;
    private String address;
    private String city;
    private int zipCode;
    private String description;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public TypeSignalement getTypeSignalement() {
        return typeSignalement;
    }

    public void setTypeSignalement(TypeSignalement typeSignalement) {
        this.typeSignalement = typeSignalement;
    }

    public String getDate() {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
        } else {
            return "";
        }
    }

    public void setDate(Date dateSignalement) {
        this.date = dateSignalement;
    }

    public Bitmap getPhoto() {
        return photo;
    }
    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public enum TypeSignalement{DECHETS, ENCOMBREMENTS}
}
