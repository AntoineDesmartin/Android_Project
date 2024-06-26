package edu.ihm.vue.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EncombrementSignalement extends Signalement implements Parcelable {
    EncombrementSignalement(){
        this.equipements=new ArrayList<>(Arrays.asList("Chariot élévateur","un camion", "équipemnts de protection"));
        this.typeSignalement=TypeSignalement.ENCOMBREMENTS;
        this.isBlockage = true;
        this.niveau=1;
    }

    public EncombrementSignalement(String titre,  Date d, Bitmap b, String adr, String vi, String co,
                       String com,  String auteur,String interv) {
        this();
        this.title = titre;
        this.dateIncident = d;
        this.photo = b;
        this.address = adr;
        this.city = vi;
        this.zipCode = co;
        this.description = com;
        this.auteur=auteur;
        this.intervenant=interv;
    }
    protected EncombrementSignalement(Parcel in) {
        title = in.readString();
        typeSignalement = TypeSignalement.valueOf(in.readString());
        address = in.readString();
        city = in.readString();
        zipCode = in.readString();
        description = in.readString();
        niveau = in.readInt();
        intervenant = in.readString();
        completed = in.readByte() != 0;
        long dateMillis = in.readLong();
        if (dateMillis != -1) {
            dateIncident = new Date(dateMillis);
        }
        long interventionMillis = in.readLong();
        if (interventionMillis != -1) {
            intervention = new Date(interventionMillis);
        }
        byte[] photoBytes = in.createByteArray();
        if (photoBytes != null) {
            photo = BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
        }
        equipements = in.createStringArrayList();
        auteur = in.readString();
        lon=in.readDouble();
        lat=in.readDouble();

    }

    public static final Creator<Signalement> CREATOR = new Creator<Signalement>() {
        @Override
        public Signalement createFromParcel(Parcel in) {
            return new EncombrementSignalement(in);
        }

        @Override
        public Signalement[] newArray(int size) {
            return new EncombrementSignalement[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(typeSignalement.name());
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(zipCode);
        dest.writeString(description);
        dest.writeInt(niveau);
        dest.writeString(intervenant);
        dest.writeByte((byte) (completed ? 1 : 0));
        dest.writeLong(dateIncident != null ? dateIncident.getTime() : -1);
        dest.writeLong(intervention != null ? intervention.getTime() : -1);
        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            dest.writeByteArray(byteArray);
        } else {
            dest.writeByteArray(null);
        }
        dest.writeStringList(equipements);
        dest.writeString(auteur);
        dest.writeDouble(lon);
        dest.writeDouble(lat);
    }
    @Override
    public int describeContents() {
        return 0;
    }
}


