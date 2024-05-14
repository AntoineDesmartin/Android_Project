package edu.ihm.vue;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.MalformedURLException;

import edu.ihm.vue.models.Signalement;

public interface SignalementListener {
   void goToTypeSignalementFragment(String titre);
   void backToTitreSignalementFragment();
   void goToDateSignalementFragment(Signalement.TypeSignalement type);
   void annulerSignalement();
   void backToTypeSignalementFragment(String date);
   void goToCameraSignalementFragment(String date);
   void backToDateSignalementFragment();
   void goToAdresseSignalementFragment();
   void backToCameraSignalementFragment(String adr, String vil, String code);
   void goToCommentaireSignalementFragment(String adr, String vil, String code);
   void backToAdresseSignalementFragment(String comm);
   void finishSignalement(String comm) throws IOException;
}
