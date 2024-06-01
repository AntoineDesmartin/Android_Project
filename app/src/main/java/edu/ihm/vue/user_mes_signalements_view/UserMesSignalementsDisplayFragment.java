package edu.ihm.vue.user_mes_signalements_view;

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

public class UserMesSignalementsDisplayFragment extends Fragment implements Clickable {

    public UserMesSignalementsDisplayFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_mes_signalements_display, container, false);
        ListView listview = view.findViewById(R.id.listView);
        MonSignalementForUserAdapter adapter = new MonSignalementForUserAdapter(this, getContext());
        listview.setAdapter(adapter);
        return view;
    }
    @Override
    public void onClickButton(int position) {
        Intent intent = new Intent(getContext(), UserSignalementInfoDisplayActivity.class);
        intent.putExtra("signalement",(Parcelable) MainActivity.mesSignalements.get(position));
        startActivity(intent);
    }
}