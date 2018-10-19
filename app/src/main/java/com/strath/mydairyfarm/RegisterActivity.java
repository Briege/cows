package com.strath.mydairyfarm;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.dob)
    TextInputLayout dob;

    @BindView(R.id.type)
    TextInputLayout type;

    @BindView(R.id.cowname)
    TextInputLayout cowname;

    @BindView(R.id.gender)
    TextInputLayout gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.name)
    public void openDatePicker() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = String.format("%s-%s-%s", year, month + 1, dayOfMonth);
                dob.getEditText().setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }

    @OnClick(R.id.title)
    public void back() {
        finish();
    }

    @OnClick(R.id.register)
    public void register() {
        String cowType = type.getEditText().getText().toString().trim();
        String dobText = dob.getEditText().getText().toString().trim();
        String cowgender = gender.getEditText().getText().toString().trim();
        String name = cowname.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(cowType) || TextUtils.isEmpty(dobText)) {
            Toast.makeText(this, "All details are required", Toast.LENGTH_SHORT).show();
        } else {
            Cow cow = new Cow();
            cow.setDob(dobText);
            cow.setType(cowType);
            cow.setCsex(cowgender);
            cow.setCname(name);

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            String key = databaseReference.child("cows").push().getKey();

            cow.setId(key);

            databaseReference.child("cows").child(key).setValue(cow);

            Toast.makeText(this, "Cow added successfully", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(RegisterActivity.this, CowActivity.class));

            finish();


        }
    }

}
