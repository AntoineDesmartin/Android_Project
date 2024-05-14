package edu.ihm.vue.models;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Signalement {
    private String title;
    @JsonIgnore
    private TypeSignalement typeSignalement;

    public void setBlockage() {
        isBlockage = (typeSignalement==TypeSignalement.ENCOMBREMENTS);
    }

    @JsonProperty(value = "isBlockage")
    public boolean isBlockage() {
        return isBlockage;
    }

    @JsonProperty(value = "isBlockage")
    public void setBlockage(boolean blockage) {
        isBlockage = blockage;
    }

    boolean isBlockage;
    @JsonIgnore
    private Date date = null;
    @JsonIgnore
    private Bitmap photo = null;
    private String address;
    private String city;
    private String zipCode;
    private String description;

    @JsonProperty(value = "description")
    public String getDescription() {
        return description;
    }
    @JsonProperty(value = "description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty(value = "title")
    public String getTitle() {
        return title;
    }

    @JsonProperty(value = "title")
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
    @JsonProperty(value = "address")
    public String getAddress() {
        return address;
    }

    @JsonProperty(value = "address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty(value = "city")
    public String getCity() {
        return city;
    }

    @JsonProperty(value = "city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty(value = "zipCode")
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty(value = "zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public enum TypeSignalement{DECHETS, ENCOMBREMENTS}
}
