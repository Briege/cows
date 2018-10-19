package com.strath.mydairyfarm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.OnClick;

public class VaccinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination);
    }

    @OnClick(R.id.title)
    public void back() {
        finish();
    }
}
