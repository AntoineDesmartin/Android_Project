package edu.ihm.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textView;


    List<Dechet> baseDeDonnees = new ArrayList<>();
    Dechet d1 = new Dechet("Verre a Biot",new Date(12,4,2024),"dangereux","Biot","poub.jpg");
    Dechet d2 = new Dechet("Verre a Biot2",new Date(12,4,2024),"dangereux2","Biot2","poub.jpg");
    private RecyclerView recyclerView;
    private DechetAdapter adapter;


    public HomeFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DechetAdapter(baseDeDonnees);
        recyclerView.setAdapter(adapter);

        return view;
    }
}