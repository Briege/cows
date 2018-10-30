package com.strath.mydairyfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CowDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_detail);

        String cow = getIntent().getStringExtra("cow");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference("Users").child(user.getUid()).child("Cows").child(cow).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Cow cow1 = dataSnapshot.getValue(Cow.class);
                Log.e("cow", cow1.getCname());
                Log.e("cow", cow1.getCsex());
                Log.e("cow", cow1.getDob());
                Log.e("cow", cow1.getFarm());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
