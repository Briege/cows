package com.strath.mydairyfarm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button buttoncreateaccount;
    private TextInputEditText mEditEmail, mEditPassword, mEditName, mEditPhonenumber, mEditConfirmpass, mfarm;
    private TextView loginlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        buttoncreateaccount = findViewById(R.id.btn_createaccount);
        mEditEmail = findViewById(R.id.email);
        mEditPassword = findViewById(R.id.password);
        mEditName = findViewById(R.id.name);
        mEditPhonenumber = findViewById(R.id.phonenumber);
        mEditConfirmpass = findViewById(R.id.confirmpassword);
        mfarm = findViewById(R.id.farmname);
        loginlink = findViewById(R.id.link_login);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("message");
        databaseReference.setValue("Hello World");

        buttoncreateaccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                if(mEditEmail.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mEditPassword.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mEditName.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Full Name is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mEditPhonenumber.getText().toString().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Phone Number is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!mEditPassword.getText().toString().equals(mEditConfirmpass.getText().toString())){
                    Toast.makeText(SignUpActivity.this, "The two passwords should be the same!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mfarm.getText().toString().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Farm name is required!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailString = mEditEmail.getText().toString();
                String passString = mEditPassword.getText().toString();
                createAccount(emailString, passString);

            }
        });

        loginlink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

    }

    public void createAccount(String emailString, String passString)
    {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        String emailStr = mEditEmail.getText().toString();
        String nameStr = mEditName.getText().toString();
        String phoneStr = mEditPhonenumber.getText().toString();
        String farmNameStr = mfarm.getText().toString();

        final User user = new User(nameStr, emailStr, phoneStr, farmNameStr);

        mAuth.createUserWithEmailAndPassword(emailString, passString)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() ) {
                            Log.d(TAG, "createUserWithEmail:success");

                            FirebaseAuth mAuth = FirebaseAuth.getInstance();
                            String userID = mAuth.getCurrentUser().getUid();
                            // Users
                            // user1, user2, user3
                            // user1 -> details, cows
                            FirebaseDatabase.getInstance().getReference("Users").child(userID)
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // Sign in success, update UI with the signed-in user's information
                                    progressDialog.dismiss();
                                    SharedPref sharedPref = new SharedPref(SignUpActivity.this);
                                    User u = new User();
                                    u.setEmail(user.getEmail());
                                    u.setName(user.getName());
                                    sharedPref.setUser(u);
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                    ((MainActivity)getActivity()).replaceFragments(new DashboardFragment(), true, "Signup");
                                    Intent intent = new Intent(SignUpActivity.this, Main2Activity.class);
                                    startActivity(intent);
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Log.e(TAG, "onComplete: ", task.getException());
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }

}
