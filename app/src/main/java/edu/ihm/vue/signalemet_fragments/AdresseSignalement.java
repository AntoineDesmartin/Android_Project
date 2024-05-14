package edu.ihm.vue.signalemet_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import edu.ihm.vue.R;
import edu.ihm.vue.SignalementListener;

public class AdresseSignalement extends Fragment {

    private String adresse;
    private String ville;
    private int codePostal;
    private SignalementListener mListener;
    public AdresseSignalement() {

    }

    public AdresseSignalement(String adresse, String ville, int codePostal) {
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_adresse_signalement, container, false);

        EditText adresse = rootView.findViewById(R.id.adresseEditText);
        if (this.adresse != null)
            adresse.setText(this.adresse);
        EditText ville = rootView.findViewById(R.id.villeEditText);
        if (this.ville != null)
            ville.setText(this.ville);
        EditText codepostal = rootView.findViewById(R.id.codepostalEditText);
        if (this.codePostal !=0) {
            codepostal.setText(String.valueOf(this.codePostal));
        }

        Button buttonAnnuler = rootView.findViewById(R.id.annuler);
        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.annulerSignalement();
                }
            }
        });

        Button back = rootView.findViewById(R.id.retour);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.backToCameraSignalementFragment(adresse.getText().toString(), ville.getText().toString(), codepostal.getText().toString());
                }
            }
        });

        Button suivant = rootView.findViewById(R.id.suivant);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToCommentaireSignalementFragment(adresse.getText().toString(), ville.getText().toString(), codepostal.getText().toString());
                }
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SignalementListener) {
            mListener = (SignalementListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SignalementListener");
        }
    }

}