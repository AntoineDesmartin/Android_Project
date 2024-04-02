package edu.ihm.vue;

import edu.ihm.vue.models.Signalement;

public interface SignalementListener {
   void goToTypeSignalementFragment(String titre);
   void BackToTitreSignalementFragment();
   void goToDateFragement(Signalement.TypeSignalement type);
   void annulerSignalement();
   void BackToTypeSignalementFragment();
}
