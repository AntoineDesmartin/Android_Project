package edu.ihm.vue.signalements_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import edu.ihm.vue.R;

public class SignalementForAgentAdapter extends BaseAdapter {
    private Clickable activity;
    private LayoutInflater mInflater;

    public SignalementForAgentAdapter(Clickable activity, Context context){
        this.activity = activity;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount(){
        return AgentSignalementsDisplayFragment.signalementsToDisplay.size();
    }

    @Override
    public Object getItem(int position) {
        return AgentSignalementsDisplayFragment.signalementsToDisplay.get(position);
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

        titreSignalement.setText(AgentSignalementsDisplayFragment.signalementsToDisplay.get(position).getTitreSignalement());
        String dateIntervention = AgentSignalementsDisplayFragment.signalementsToDisplay.get(position).getIntervention();
        etatSignalement.setText(dateIntervention.isEmpty()?"Nouveau":"En Cours");
        niveauDanger.setText(Integer.toString(AgentSignalementsDisplayFragment.signalementsToDisplay.get(position).getNiveau()));
        imageSignalement.setImageBitmap(AgentSignalementsDisplayFragment.signalementsToDisplay.get(position).getPhoto());
        buttonVisualiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onClickButton(position);
            }
        });
        return layoutItem;
    }

}
