package com.strath.mydairyfarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by oirere on 09/07/2018.
 */

public class RegisterFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_cow, container, false);

        Button register = view.findViewById(R.id.register);

        final TextInputLayout type = view.findViewById(R.id.type);
        final TextInputLayout dob = view.findViewById(R.id.dob);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cowType = type.getEditText().getText().toString().trim();
                String dobText = dob.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(cowType) || TextUtils.isEmpty(dobText)) {
                    Toast.makeText(getContext(), "All details are required", Toast.LENGTH_SHORT).show();
                }else {
                    Cow cow = new Cow();
                    cow.setDob(dobText);
                    cow.setType(cowType);

                    DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference();

                    String key = databaseReference.child("cows").push().getKey();

                    cow.setId(key);

                    databaseReference.child(key).setValue(cow);

                    Toast.makeText(getContext(), "Cow added successfully", Toast.LENGTH_SHORT).show();

                    DashboardFragment dashboardFragment = new DashboardFragment();
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_holder, dashboardFragment);
                    fragmentTransaction.commit();


                }
            }
        });

        return view;

    }
}
