package edu.ihm.vue;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DechetAdapter extends BaseAdapter {
    private final String TAG = "fredrallo "+getClass().getSimpleName();

    private List<Dechet> dechets;
    private Context context;
    private LayoutInflater mInflater;
    private DechetListenerAdapter listener;


    public DechetAdapter(Context context, List<Dechet> dechets, DechetListenerAdapter listener) {
        this.context = context;
        this.dechets = dechets;
        this.listener = listener;
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
        TextView title = (TextView) layoutItem.findViewById(R.id.nomDechetTextView);
        title.setText(dechets.get(i).getTitle());

        layoutItem.setOnClickListener((click)->{
            Log.d(TAG, "getView: "+dechets.get(i).getTitle());
            listener.onClickDechet(dechets.get(i));
        });

        return layoutItem;
    }


}
