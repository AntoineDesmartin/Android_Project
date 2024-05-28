package edu.ihm.vue.agent_equipements_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import edu.ihm.vue.R;

public class Equipement_adapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private final IModel IModel;
    private final IView view;
    private final IController IController;



    public Equipement_adapter(Context context, IController IController, IModel IModel, IView view) {
        inflater = LayoutInflater.from(context);
        this.IController = IController;
        this.IModel = IModel;
        this.view = view;
    }



    public int getCount() {
        return IModel.size();
    }
    public Object getItem(int position) {
        return IModel.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ConstraintLayout layoutItem;

        layoutItem = (ConstraintLayout) (view == null ? inflater.inflate(R.layout.equipement_list_item, parent, false) : view);
        TextView name = layoutItem.findViewById(R.id.equipement_name);
        name.setText( IModel.get(position)+"");
        layoutItem.setOnClickListener( clic ->  IController.onClickItem(position) );
        return layoutItem;
    }


    public void refresh() {
        notifyDataSetChanged();
    }
}
