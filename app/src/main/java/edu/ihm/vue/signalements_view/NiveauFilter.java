package edu.ihm.vue.signalements_view;

import java.util.Observable;
import java.util.Observer;

public class NiveauFilter implements Observer {

    private IDisplay controller;

    public NiveauFilter(IDisplay controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        //nothing else to do but ask controller to change displayed value of the level
        int currentLevel = (Integer)arg;
        controller.updateSignalementsDisplay(currentLevel);
    }
}
