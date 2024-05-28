package edu.ihm.vue.agent_equipements_view.mvc;

import edu.ihm.vue.agent_equipements_view.IController;
import edu.ihm.vue.agent_equipements_view.IModel;
import edu.ihm.vue.agent_equipements_view.IView;

public class Controller_Equipements implements IController {
    private final IView view;
    private final IModel model;

    public Controller_Equipements(IView view, IModel model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void userActionAddClick() {
        String equipement_name = view.getInputEquipement().getText().toString();
        if (!equipement_name.equals("")) {
            model.add(equipement_name);
        }
    }
    @Override
    public void onClickItem(int position) {
        if (model.size() > 0) {
            model.remove(position);
        }
    }
}
