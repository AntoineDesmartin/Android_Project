package edu.ihm.vue.signalemet_fragment;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TitreSignalement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TitreSignalement extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String titre;

    private SignalementListener mListener;

    public TitreSignalement() {
        // Required empty public constructor
    }

    public static TitreSignalement newInstance(String param1) {
        TitreSignalement fragment = new TitreSignalement();
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
        View rootView = inflater.inflate(R.layout.fragment_titre_signalement, container, false);
        Button button = rootView.findViewById(R.id.suivant);
        EditText editText = rootView.findViewById(R.id.titresignalementinput);
        editText.setText(titre);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.goToTypeSignalementFragment(editText.getText().toString());
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
