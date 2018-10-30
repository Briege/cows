package com.strath.mydairyfarm;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DashboardFragment extends Fragment {

    TextInputLayout date, amount;
    Spinner cowname;
    Button register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        date = view.findViewById(R.id.date);
        cowname = view.findViewById(R.id.cowname);
        amount = view.findViewById(R.id.amount);
        cowname = view.findViewById(R.id.cowname);
        register = view.findViewById(R.id.register);

        final ArrayList<String> cows = new ArrayList<>();
        final ArrayList<Cow> cowsList = new ArrayList<>();
        cows.add("Select Cow");

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, cows);

        cowname.setAdapter(adapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = DashboardFragment.this.date.getEditText().getText().toString().trim();
                String cow = cowsList.get(cowname.getSelectedItemPosition()).getId();
                String amount = DashboardFragment.this.amount.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(date) || TextUtils.isEmpty(cow) || TextUtils.isEmpty(amount)) {
                    Toast.makeText(getContext(), "Fill details", Toast.LENGTH_SHORT).show();
                }else {
                    Milk milk = new Milk();
                    milk.setAmount(amount);
                    milk.setCow(cow);
                    milk.setDate(date);

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    String key = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid())
                            .child("Cows").child(cow).child("milk").push().getKey();

                    milk.setId(key);

                    FirebaseDatabase.getInstance().getReference("Users").child(user.getUid())
                            .child("Cows").child(cow).child("milk").child(key).setValue(milk);

                }

            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.child("Users").child(user.getUid()).child("Cows").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cows.clear();
                cows.add("Select Cow");
                for (DataSnapshot data :
                        dataSnapshot.getChildren()) {
                    Cow cow = data.getValue(Cow.class);
                    cowsList.add(cow);
                    cows.add(cow.getCname());
                }
                cowname.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        date.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = String.format("%s-%s-%s", year, month + 1, dayOfMonth);
                        DashboardFragment.this.date.getEditText().setText(date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();

            }

        });

        return view;
    }
}
