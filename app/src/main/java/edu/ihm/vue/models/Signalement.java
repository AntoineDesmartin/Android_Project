package edu.ihm.vue.models;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        defaultImpl = DechetSignalement.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DechetSignalement.class, name = "dechet"),
        @JsonSubTypes.Type(value = EncombrementSignalement.class, name = "encombrement")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Signalement {
    @JsonProperty("title")
    protected String title;
    @JsonIgnore
    protected TypeSignalement typeSignalement;
    @JsonProperty("isBlockage")
    protected boolean isBlockage;
    @JsonIgnore
    protected Date dateIncident = null;
    @JsonIgnore
    protected Bitmap photo = null;
    @JsonProperty("address")
    protected String address;
    @JsonProperty("city")
    protected String city;
    @JsonProperty("zipCode")
    protected String zipCode;
    @JsonProperty("description")
    protected String description;
    @JsonIgnore
    protected int niveau = 0;
    @JsonIgnore
    protected String intervenant = "";
    protected String auteur = "";
    @JsonIgnore
    protected Date intervention = null;
    @JsonIgnore
    protected boolean completed = false;
    @JsonIgnore
    protected List<String> equipements;
    protected double lat = 0;
    protected double lon = 0;


    public Signalement() {

    }

    @JsonCreator
    public Signalement(@JsonProperty("title") String title, @JsonProperty("isBlockage") boolean isBlockage, @JsonProperty("address") String address, @JsonProperty("city") String city,
                       @JsonProperty("zipCode") String zipCode, @JsonProperty("description") String description, String auteur) {
        this.title = title;
        this.isBlockage = isBlockage;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.description = description;
        this.auteur = auteur;
    }

    public List<String> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<String> equipements) {
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

    public Date getInterventioninFull() {
        if (intervention != null) {
            return this.intervention;
        } else {
            return new Date();
        }


    }

    public void setIntervention(Date intervention) {
        this.intervention = intervention;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public TypeSignalement getTypeSignalement() {
        return typeSignalement;
    }

    public void setTypeSignalement(TypeSignalement typeSignalement) {
        this.typeSignalement = typeSignalement;
    }

    @JsonProperty("isBlockage")
    public boolean isBlockage() {
        return isBlockage;
    }

    @JsonProperty("isBlockage")
    public void setBlockage(boolean blockage) {
        isBlockage = blockage;
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
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }
    @JsonProperty("city")
    public String getCity() {
        return city;
    }
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }
    @JsonProperty("zipCode")
    public String getZipCode() {
        return zipCode;
    }
    @JsonProperty("zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }


    public enum TypeSignalement {DECHETS, ENCOMBREMENTS}
}