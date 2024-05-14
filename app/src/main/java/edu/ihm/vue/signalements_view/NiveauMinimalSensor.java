package edu.ihm.vue.signalements_view;

import android.util.Log;

import java.util.Observable;

class NiveauMinimalSensor extends Observable {

    private int niveauMinimal;

    public NiveauMinimalSensor() {
        this.niveauMinimal = 0;
    }

    public void setNiveauMinimal(int newLevel) {
        Log.d("radhi", ""+newLevel);
        niveauMinimal = newLevel;
        setChanged();
        notifyObservers(niveauMinimal);
    }

    public int getNiveauMinimal() {
        return niveauMinimal;
    }
}
