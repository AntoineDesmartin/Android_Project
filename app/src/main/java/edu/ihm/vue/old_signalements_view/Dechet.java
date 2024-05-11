package edu.ihm.vue.old_signalements_view;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Dechet implements Parcelable {

    private String title;
    private Date date;
    private String type;
    private String adress;
    private String photo;


    public Dechet(String title, Date date, String type, String adress, String photo) {
        this.title = title;
        this.date = date;
        this.type = type;
        this.adress = adress;
        this.photo = photo;
    }

    public Dechet(String title, Date date, String type, String adress) {
        this(title,date,type,adress,null);
    }


    protected Dechet(Parcel in) {
        title = in.readString();
        type = in.readString();
        adress = in.readString();
        photo = in.readString();
    }

    public static final Creator<Dechet> CREATOR = new Creator<Dechet>() {
        @Override
        public Dechet createFromParcel(Parcel in) {
            return new Dechet(in);
        }

        @Override
        public Dechet[] newArray(int size) {
            return new Dechet[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhoto() {
        return photo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(type);
        parcel.writeString(adress);
        parcel.writeString(photo);

    }
}
