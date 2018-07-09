package com.strath.mydairyfarm;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Button resetpassword;
    private TextInputEditText mEditEmail, mEditOldPassword, mEditConfirmpass, mEditNewPassword;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);

        mAuth = FirebaseAuth.getInstance();
        resetpassword = view.findViewById(R.id.reset);
        mEditEmail = view.findViewById(R.id.email);
        mEditOldPassword = view.findViewById(R.id.oldpassword);
        mEditConfirmpass = view.findViewById(R.id.password);
        mEditNewPassword = view.findViewById(R.id.confirmpassword);
        return view;
    }


}
