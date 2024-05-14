package edu.ihm.vue.signalemet_fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import edu.ihm.vue.R;
import edu.ihm.vue.SignalementListener;

import edu.ihm.vue.models.Signalement;

public class TypeSignalement extends Fragment {

    private String titre;
    SignalementListener mListener;

    public TypeSignalement() {
        // Required empty public constructor
    }

    public TypeSignalement(String titre) {
        this.titre = titre;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_type_signalement, container, false);
        TextView titreTextView = rootView.findViewById(R.id.titresignalementdisplay);
        if (this.titre != null)
            titreTextView.setText(this.titre);
        Button back = rootView.findViewById(R.id.retour);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.backToTitreSignalementFragment();
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
        //BUTTON DECHET
        ImageButton dechetButton = rootView.findViewById(R.id.dechetButton);
        dechetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToDateSignalementFragment(Signalement.TypeSignalement.DECHETS);
                }
            }
        });
        //BUTTON ENCOMBREMENT
        ImageButton encombrementButton = rootView.findViewById(R.id.encombrementButton);
        encombrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToDateSignalementFragment(Signalement.TypeSignalement.ENCOMBREMENTS);
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