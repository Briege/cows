package com.strath.mydairyfarm;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oirere on 10/07/2018.
 */

public class CowAdapter extends RecyclerView.Adapter<CowAdapter.ViewHolder> {

    private ArrayList<Cow> cows;
    private Context context;

    public CowAdapter(Context context, ArrayList<Cow> cows) {
        this.context = context;
        this.cows = cows;
    }

    @NonNull
    @Override
    public CowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cow_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CowAdapter.ViewHolder holder, int position) {
        Cow cow = cows.get(position);
        holder.id.setText(cow.getId());
        holder.type.setText(cow.getType());
    }

    @Override
    public int getItemCount() {
        return cows.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id)
        TextView id;

        @BindView(R.id.type)
        TextView type;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.close)
        public void close() {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("cows").child(cows.get(getAdapterPosition()).getId()).removeValue();
        }


    }


}
