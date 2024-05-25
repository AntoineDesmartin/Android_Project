package edu.ihm.vue.agent_equipements_view.mvc;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import edu.ihm.vue.agent_equipements_view.IModel;
import edu.ihm.vue.agent_equipements_view.IController;

public class Model_Equipements extends Observable implements IModel {
    private List<String> equipements;
    private IController IController;
    public Model_Equipements(){
        super();
        equipements = new ArrayList<>();
    }
    public void setController(IController IController) {
        this.IController = IController;
    }
    @Override
    public Object get(int position) {
        return equipements.get(position);
    }

    @Override
    public int size() {
        return equipements.size();
    }

    @Override
    public void setEquipements(List<String> equipements_list) {
        equipements=equipements_list;
    }

    @Override
    public void add(String equipement) {
        if ( !equipements.contains(equipement) ) {
            equipements.add(equipement);
            setChanged();
            notifyObservers();
        }
    }
    @Override
    public void remove(int index) {
        if (index<equipements.size()) {
            equipements.remove(index);
            setChanged();
            notifyObservers();
        }
    }

}
