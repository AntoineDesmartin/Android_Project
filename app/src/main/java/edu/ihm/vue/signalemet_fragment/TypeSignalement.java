package edu.ihm.vue.signalemet_fragment;

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

import edu.ihm.vue.SignalementListener;
import edu.ihm.vue.models.Signalement;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypeSignalement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypeSignalement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String titre;
    SignalementListener mListener;

    public TypeSignalement() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TypeSignalement.
     */
    // TODO: Rename and change types and number of parameters
    public static TypeSignalement newInstance(String param1) {
        TypeSignalement fragment = new TypeSignalement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titre = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_type_signalement, container, false);
        TextView titreTextView = rootView.findViewById(R.id.titresignalementdisplay);
        titreTextView.setText(this.titre);
        Button back = rootView.findViewById(R.id.retour);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.BackToTitreSignalementFragment();
                }
            }

        });
        Button buttonAnnuler=rootView.findViewById(R.id.annuler);
        buttonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.annulerSignalement();
                }
            }
        });
        ImageButton dechetButton=rootView.findViewById(R.id.dechetButton);
        dechetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToDateFragement(Signalement.TypeSignalement.DECHETS);
                }
            }
        });

        ImageButton encombrementButton=rootView.findViewById(R.id.encombrementButton);
        encombrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToDateFragement(Signalement.TypeSignalement.ENCOMBREMENTS);
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