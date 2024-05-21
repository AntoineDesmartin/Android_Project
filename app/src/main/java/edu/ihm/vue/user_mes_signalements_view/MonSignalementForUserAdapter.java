package edu.ihm.vue.user_mes_signalements_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ihm.vue.R;
import edu.ihm.vue.agent_signalements_view.AgentSignalementsDisplayFragment;
import edu.ihm.vue.agent_signalements_view.Clickable;
import edu.ihm.vue.main_activities.MainActivity;
import edu.ihm.vue.user_signalements_view.UserSignalementsDisplayFragment;

public class MonSignalementForUserAdapter extends BaseAdapter {
    private Clickable activity;
    private LayoutInflater mInflater;

    public MonSignalementForUserAdapter(Clickable activity, Context context){
        this.activity = activity;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount(){
        return MainActivity.mesSignalements.size();
    }

    @Override
    public Object getItem(int position) {
        return MainActivity.mesSignalements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layoutItem = convertView == null ? mInflater.inflate(R.layout.user_incident_listview_item, parent, false) : convertView;
        TextView titreSignalement=layoutItem.findViewById(R.id.titre_signalement);
        TextView etatSignalement=layoutItem.findViewById(R.id.etat_signalement);
        ImageView imageSignalement=layoutItem.findViewById(R.id.image_signalement);
        Button buttonVisualiser= layoutItem.findViewById(R.id.visualiser);

        titreSignalement.setText(MainActivity.mesSignalements.get(position).getTitreSignalement());
        String dateIntervention = MainActivity.mesSignalements.get(position).getIntervention();
        etatSignalement.setText(dateIntervention.isEmpty()?"Nouveau":"En Cours");
        imageSignalement.setImageBitmap(MainActivity.mesSignalements.get(position).getPhoto());
        buttonVisualiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onClickButton(position);
            }
        });
        return layoutItem;
    }
}
