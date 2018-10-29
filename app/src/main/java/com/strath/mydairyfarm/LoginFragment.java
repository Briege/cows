package com.strath.mydairyfarm;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.design.widget.TextInputEditText;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;


public class LoginFragment extends Fragment {


    private Button button;
    private TextView signuplink;
    private TextInputEditText mEditEmail;
    private EditText mEditPassword;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        button = view.findViewById(R.id.btn_login);
        mEditEmail = view.findViewById(R.id.email);
        mEditPassword = view.findViewById(R.id.password);
        signuplink = view.findViewById(R.id.link_signup);
        mAuth = FirebaseAuth.getInstance();

//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
//        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
//        ((MainActivity)getActivity()).getSupportActionBar().setTitle("My Dairy Farm");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEditEmail.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mEditPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailString = mEditEmail.getText().toString();
                String passString = mEditPassword.getText().toString();

                attemptLogin(emailString, passString);
            }
        });

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragments(new SignupFragment(), true, "Signup");
            }
        });
        return view;
    }

    private void attemptLogin(final String emailString, String passString) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(emailString, passString)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            progressDialog.dismiss();
                            SharedPref sharedPref = new SharedPref(getContext());
                            User u = new User();
                            u.setEmail(user.getEmail());
                            u.setName(user.getDisplayName());
                            sharedPref.setUser(u);

                            ((MainActivity) getActivity()).replaceFragments(new DashboardFragment(), false, "");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Log.e("Unsuccessful", "=" );
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


}
