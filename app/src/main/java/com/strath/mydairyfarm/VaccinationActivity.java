package com.strath.mydairyfarm;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VaccinationActivity extends AppCompatActivity {

    TextInputLayout dvaccination, cowname, type, description;
    Button save;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);

        dvaccination = findViewById(R.id.dvaccination);
        cowname = findViewById(R.id.cowname);
        type = findViewById(R.id.type);
        description = findViewById(R.id.description);

        save = findViewById(R.id.save);
        title = findViewById(R.id.title);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

//        databaseReference.child("vaccinations").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                ArrayList<Vaccination> vaccinations = new ArrayList<>();
//                for (DataSnapshot data :
//                        dataSnapshot.getChildren()) {
//                    Vaccination vaccination = data.getValue(Vaccination.class);
//                    vaccinations.add(vaccination);
//                }
//                VaccinationAdapter vaccinationAdapter = new VaccinationAdapter(VaccinationActivity.this, vaccinations);
//                vaccinationsList.setAdapter(vaccinationAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }

    //    @OnClick(R.id.title)
//    public void back() {
//        finish();
//    }
    public void save() {
        String vaccinationdate = dvaccination.getEditText().getText().toString().trim();
        String name = cowname.getEditText().getText().toString().trim();
        String vaccinetype = type.getEditText().getText().toString().trim();
        String vdescription = description.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(vaccinationdate) || TextUtils.isEmpty(name) || TextUtils.isEmpty(vaccinetype)) {
            Toast.makeText(this, "All details are required", Toast.LENGTH_SHORT).show();
        } else {
            Vaccination vaccination = new Vaccination();
            vaccination.setVaccinationdate(vaccinationdate);
            vaccination.setCowname(name);
            vaccination.setType(vaccinetype);
            vaccination.setDescription(vdescription);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            String key = databaseReference.child("Users").child(user.getUid()).child("Vaccinations").push().getKey();

            vaccination.setId(key);

            FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Vaccinations").child(key).setValue(vaccination);

            Toast.makeText(this, "Vaccination added successfully", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}