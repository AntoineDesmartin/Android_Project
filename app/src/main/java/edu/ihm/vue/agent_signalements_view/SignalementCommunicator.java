package edu.ihm.vue.agent_signalements_view;

public interface SignalementCommunicator {
    void passerDonneeFragmentVersActivite(String donnee);
    void passerDonneeActiviteVersFragment(String donnee);
}
