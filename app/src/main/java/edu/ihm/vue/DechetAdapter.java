package edu.ihm.vue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DechetAdapter extends RecyclerView.Adapter<DechetAdapter.ViewHolder> {
    private List<Dechet> dechets;

    public DechetAdapter(List<Dechet> dechets) {
        this.dechets = dechets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dechet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dechet dechet = dechets.get(position);
        holder.nomDechetTextView.setText(dechet.getTitle());
        holder.dateTextView.setText(dechet.getDate().toString());
        
        // Ajoutez d'autres attributs de Dechet à lier avec les vues ici
    }

    @Override
    public int getItemCount() {
        return dechets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomDechetTextView;
        TextView dateTextView;
        TextView typeTextView;
        TextView adresseTextView;
        TextView photoTextView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomDechetTextView = itemView.findViewById(R.id.nomDechetTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);

            // Initialisez d'autres vues ici si nécessaire
        }
    }
}
