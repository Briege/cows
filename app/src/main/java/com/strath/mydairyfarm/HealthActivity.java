package com.strath.mydairyfarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class HealthActivity extends AppCompatActivity {


    CardView cardTreatment, cardVaccine, cardDeworming;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Health");

        cardDeworming = findViewById(R.id.carddeworming);
        cardVaccine = findViewById(R.id.cardvaccination);
        cardTreatment = findViewById(R.id.cardillness);

        updateUI();
    }

    public void updateUI(){
        cardDeworming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HealthActivity.this, DewormingActivity.class));
            }
        });

        cardVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HealthActivity.this, VaccinationActivity.class));
            }
        });

        cardTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HealthActivity.this, TreatmentActivity.class));
            }
        });
    }
}
