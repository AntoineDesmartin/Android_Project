package edu.ihm.vue.agent_equipements_view.mvc;

import android.database.Observable;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;


import java.util.Observer;

import edu.ihm.vue.R;
import edu.ihm.vue.agent_equipements_view.Equipement_adapter;
import edu.ihm.vue.agent_equipements_view.IView;
import edu.ihm.vue.agent_equipements_view.IController;


public class View_Equipements implements IView, Observer {
    private Equipement_adapter adapter;
    private final ViewGroup layout;
    private  boolean modelIsReady = false;
    private IController IController;


    public View_Equipements(ViewGroup layout) {
        this.layout = layout;
        layout.findViewById(R.id.ajouter).setOnClickListener( click ->  IController.userActionAddClick());
    }
    @Override
    public EditText getInputEquipement(){
        return layout.findViewById(R.id.new_item);
    }
    public void setAdapter(Equipement_adapter adapter) {
        this.adapter = adapter;
        modelIsReady = true;
        ((ListView)layout.findViewById(R.id.listView)).setAdapter(adapter);
        adapter.refresh();
    }
    public void setController(IController IController) {
        this.IController = IController;
    }
    @Override
    public void update(java.util.Observable o, Object arg) {
        if(modelIsReady) {
            adapter.refresh();
        }
    }
}
