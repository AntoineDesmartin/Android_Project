package edu.ihm.vue.old_signalements_view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import edu.ihm.vue.R;


public class DechetListFragment extends Fragment implements DechetListenerAdapter {
    private final String TAG = "fredrallo "+getClass().getSimpleName();

    List<Dechet> baseDeDonnees = new ArrayList<>();
    private ListView listview;
    private DechetAdapter adapter;

    public DechetListFragment() {
        // Required empty public constructor
    }


    public static DechetListFragment newInstance(String param1, String param2) {
        DechetListFragment fragment = new DechetListFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_dechet_list, container, false);

        Dechet d1 = new Dechet("Verre a Biot", new Date(2024, 4, 12), "dangereux", "Biot", "poub.jpg");
        Dechet d2 = new Dechet("Verre a Biot2", new Date(2024, 4, 12), "dangereux2", "Biot2", "poub.jpg");

        // Ajoutez les objets à la liste baseDeDonnees
        baseDeDonnees.add(d1);
        baseDeDonnees.add(d2);

        listview = view.findViewById(R.id.dechetListAgent);
        adapter = new DechetAdapter(getContext(),baseDeDonnees, this,"agent");
        listview.setAdapter(adapter);

        return view;
    }
    @Override
    public void onClickDechet(Dechet item) {
        Intent intent = new Intent(getContext(), DechetActivity.class);
        intent.putExtra("dechet",(Parcelable) item);
        startActivity(intent);
    }
}