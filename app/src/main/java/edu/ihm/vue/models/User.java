package edu.ihm.vue.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Parcelable {
    private String id = "";
    private String nom = "";
    private String prenom = "";
    private Role role;
    private String username;
    private boolean isAgent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @JsonCreator
    public User(@JsonProperty("id") String id, @JsonProperty("surname") String nom, @JsonProperty("firstName") String prenom, @JsonProperty("username") String username, @JsonProperty("isAgent") boolean isAgent) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.isAgent = isAgent;
        this.role = this.isAgent ? Role.FONCTIONNAIRE : Role.PARTICULIER;
        this.username = username;
    }

    // Parcelable implementation
    protected User(Parcel in) {
        id = in.readString();
        nom = in.readString();
        prenom = in.readString();
        role = Role.valueOf(in.readString());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(role.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public enum Role {
        PARTICULIER, FONCTIONNAIRE
    }
}
