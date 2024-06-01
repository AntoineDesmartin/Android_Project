package edu.ihm.vue.agent_equipements_view;

import java.util.List;

import edu.ihm.vue.models.Signalement;

public interface IModel {
    void add(String equipement);
    void remove(int index);
    Object get(int position);
    int size();

    void setEquipements(List<String> equipements_list);
    List<String> getEquipements();
}
