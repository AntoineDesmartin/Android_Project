package edu.ihm.vue.agent_mes_signalements_view;

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
import edu.ihm.vue.main_activities.AgentActivity;

public class MesSignalementsForAgentAdapter extends BaseAdapter {
    private Clickable activity;
    private LayoutInflater mInflater;

    public MesSignalementsForAgentAdapter(Clickable activity, Context context){
        this.activity = activity;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount(){
        return AgentActivity.mesSignalements.size();
    }

    @Override
    public Object getItem(int position) {
        return AgentActivity.mesSignalements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layoutItem = convertView == null ? mInflater.inflate(R.layout.agent_incident_listview_item, parent, false) : convertView;
        TextView titreSignalement=layoutItem.findViewById(R.id.titre_signalement);
        TextView etatSignalement=layoutItem.findViewById(R.id.etat_signalement);
        ImageView imageSignalement=layoutItem.findViewById(R.id.image_signalement);
        TextView niveauDanger=layoutItem.findViewById(R.id.niveau_danger);
        Button buttonVisualiser= layoutItem.findViewById(R.id.visualiser);

        titreSignalement.setText(AgentActivity.mesSignalements.get(position).getTitle());
        String dateIntervention = AgentActivity.mesSignalements.get(position).getIntervention();
        etatSignalement.setText(dateIntervention.isEmpty()?"Nouveau":"En Cours");
        niveauDanger.setText(Integer.toString(AgentActivity.mesSignalements.get(position).getNiveau()));
        imageSignalement.setImageBitmap(AgentActivity.mesSignalements.get(position).getPhoto());
        buttonVisualiser.setOnClickListener(v -> activity.onClickButton(position));
        return layoutItem;
    }

}






