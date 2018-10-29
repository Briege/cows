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

public class VaccinationAdapter extends RecyclerView.Adapter<VaccinationAdapter.ViewHolder> {

    private ArrayList<Vaccination> vaccinations;
    private Context context;

    public VaccinationAdapter(Context context, ArrayList<Vaccination> vaccinations) {
        this.context = context;
        this.vaccinations = vaccinations;
    }

    @NonNull
    @Override
    public VaccinationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cow_item, parent, false);
        return new VaccinationAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vaccination vaccination = vaccinations.get(position);
        holder.id.setText(vaccination.getType());
        holder.type.setText(vaccination.getVaccinationdate());
    }

    @Override
    public int getItemCount() {
        return vaccinations.size();
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
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//            databaseReference.child("vaccinations").child(vaccinations.get(getAdapterPosition()).getType()).removeValue();


        }



    }
}
