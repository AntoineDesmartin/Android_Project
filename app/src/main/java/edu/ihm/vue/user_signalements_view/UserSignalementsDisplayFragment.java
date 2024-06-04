package edu.ihm.vue.user_signalements_view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.ihm.vue.R;
import edu.ihm.vue.UserSignalementInfoDisplayActivity;
import edu.ihm.vue.agent_signalements_view.Clickable;
import edu.ihm.vue.main_activities.MainActivity;

public class UserSignalementsDisplayFragment extends Fragment implements Clickable {

    private ListView listview;
    private SignalementForUserAdapter adapter;

    public UserSignalementsDisplayFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_signalements_display, container, false);
        listview = view.findViewById(R.id.listView);
        adapter = new SignalementForUserAdapter(this, getContext());
        listview.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClickButton(int position) {
        Intent intent = new Intent(getContext(), UserSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement",(Parcelable) MainActivity.signalements.get(position));
        startActivity(intent);
    }
}