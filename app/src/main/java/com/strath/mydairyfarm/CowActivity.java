package com.strath.mydairyfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CowActivity extends AppCompatActivity {

    @BindView(R.id.cows)
    RecyclerView cowsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow);
        ButterKnife.bind(this);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("cows").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Cow> cows = new ArrayList<>();
                for (DataSnapshot data :
                        dataSnapshot.getChildren()) {
                    Cow cow = data.getValue(Cow.class);
                    cows.add(cow);
                }
                CowAdapter cowAdapter = new CowAdapter(CowActivity.this, cows);
                cowsList.setAdapter(cowAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @OnClick(R.id.title)
    public void back() {
        finish();
    }

}
