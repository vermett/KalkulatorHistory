package com.example.kalkulatorhistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kalkulatorhistory.History;
import com.example.kalkulatorhistory.R;

import java.util.ArrayList;

public class HAdapter extends RecyclerView.Adapter<HAdapter.ViewHolder> {
    public ArrayList<History> mHistory;

    public HAdapter(ArrayList<History> mHistory) {
        this.mHistory = mHistory;
    }

    @NonNull
    @Override
    public HAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder =new ViewHolder(inflater.inflate(R.layout.acticity_history, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = mHistory.get(position);
        holder.txtAngka1.setText(history.getAngka1());
        holder.txtAngka2.setText(history.getAngka2());
        holder.txtHasil.setText(history.getHasil());
        holder.txtOperasi.setText(history.getOperasi());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int newPosition = holder.getAdapterPosition();
                mHistory.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeChanged(newPosition, mHistory.size());

                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtAngka1, txtAngka2, txtHasil, txtOperasi;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtAngka1 =itemView.findViewById(R.id.txtAngka1);
            txtAngka2 = itemView.findViewById(R.id.txtAngka2);
            txtHasil = itemView.findViewById(R.id.txtHasil);
            txtOperasi = itemView.findViewById(R.id.txtOperasi);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.main_layout);
        }
    }
}
