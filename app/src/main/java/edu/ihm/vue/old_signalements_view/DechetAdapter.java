package edu.ihm.vue.old_signalements_view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.ihm.vue.R;

public class DechetAdapter extends BaseAdapter {
    private final String TAG = "tonio "+getClass().getSimpleName();

    private List<Dechet> dechets;
    private Context context;
    private LayoutInflater mInflater;
    private DechetListenerAdapter listener;
    private String agent_or_not;


    public DechetAdapter(Context context, List<Dechet> dechets, DechetListenerAdapter listener,String agent_or_not) {
        this.context = context;
        this.dechets = dechets;
        this.listener = listener;
        this.agent_or_not=agent_or_not;
        mInflater = LayoutInflater.from(this.context);

    }



    @Override
    public int getCount() {
        return dechets.size();
    }

    @Override
    public Object getItem(int i) {
        return dechets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View layoutItem;
        layoutItem = (convertView == null ? mInflater.inflate(R.layout.dechet_item, parent, false) : convertView);
        Log.d(TAG, "getView: fuck"+agent_or_not);

        if(agent_or_not.equals("agent")){
            layoutItem = (convertView == null ? mInflater.inflate(R.layout.dechet_item_agent, parent, false) : convertView);

            //on peut récup le button ici et faire un setOnclickListener pour assigner la tache
        }
        TextView title = (TextView) layoutItem.findViewById(R.id.nomDechetTextView);
        title.setText(dechets.get(i).getTitle());




        layoutItem.setOnClickListener((click)->{
            Log.d(TAG, "getView: "+dechets.get(i).getTitle());
            listener.onClickDechet(dechets.get(i));
        });

        return layoutItem;
    }


}
