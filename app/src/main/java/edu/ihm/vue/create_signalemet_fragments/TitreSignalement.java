package edu.ihm.vue.create_signalemet_fragments;

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

public class TitreSignalement extends Fragment {


    private String titre;
    private SignalementListener mListener;

    public TitreSignalement() {
    }

    public TitreSignalement(String titre) {
        this.titre = titre;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_titre_signalement, container, false);
        EditText editText = rootView.findViewById(R.id.titresignalementinput);
        if (this.titre != null) {
            editText.setText(titre);
        }
        //BOUTTON SUIVANT
        Button button = rootView.findViewById(R.id.suivant);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToTypeSignalementFragment(editText.getText().toString());
                }
            }
        });
        //BUTTON ANNULER
        Button buttonAnnuler = rootView.findViewById(R.id.annuler);
        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.annulerSignalement();
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
