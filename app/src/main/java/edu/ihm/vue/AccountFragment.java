package edu.ihm.vue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.ihm.vue.main_activities.LoginActivity;
import edu.ihm.vue.models.User;

public class AccountFragment extends Fragment {

    private User user;
    public AccountFragment() {
    }

    public AccountFragment(User user) {
        this();
        this.user=user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TextView username= view.findViewById(R.id.nom_prenom);
        TextView role= view.findViewById(R.id.role);
        username.setText(user.getNom()+" "+user.getPrenom());
        role.setText(user.getRole().toString());
        Button btn = view.findViewById(R.id.disconnect);
        btn.setOnClickListener((click)->{
            assert this.getContext() != null;
            SharedPreferences sharedPreferences = this.getContext().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            String token = sharedPreferences.getString("token", null);
            if (token != null)
                sharedPreferences.edit().remove("token").commit();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });
        return view;
    }
}