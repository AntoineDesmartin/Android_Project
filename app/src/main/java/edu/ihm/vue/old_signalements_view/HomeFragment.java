package edu.ihm.vue.old_signalements_view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ihm.vue.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements DechetListenerAdapter {

    private TextView textView;

    List<Dechet> baseDeDonnees = new ArrayList<>();
    Dechet d1 = new Dechet("Verre a Biot",new Date(12,4,2024),"dangereux","Biot","poub.jpg");
    Dechet d2 = new Dechet("Verre a Biot2",new Date(12,4,2024),"dangereux2","Biot2","poub.jpg");
    private ListView listview;
    private DechetAdapter adapter;


    public HomeFragment() {
        // Required empty public constructor

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Créez des objets Dechet
        Dechet d1 = new Dechet("Verre a Biot", new Date(2024, 4, 12), "dangereux", "Biot", "poub.jpg");
        Dechet d2 = new Dechet("Verre a Biot2", new Date(2024, 4, 12), "dangereux2", "Biot2", "poub.jpg");

        // Ajoutez les objets à la liste baseDeDonnees
        baseDeDonnees.add(d1);
        baseDeDonnees.add(d2);

        listview = view.findViewById(R.id.listView);
        adapter = new DechetAdapter(getContext(),baseDeDonnees, this);
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