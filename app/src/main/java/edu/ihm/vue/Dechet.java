package edu.ihm.vue;

import java.util.Date;

public class Dechet {

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


}
