//package com.strath.mydairyfarm;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.design.widget.TextInputEditText;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import static android.content.ContentValues.TAG;
//
//
//public class SignupFragment extends Fragment {
//
//    private FirebaseAuth mAuth;
//    private Button buttoncreateaccount;
//    private TextInputEditText mEditEmail, mEditPassword, mEditName, mEditPhonenumber, mEditConfirmpass, mfarm;
//    private TextView loginlink;
////    private DatabaseReference databaseReference;
//
//    public SignupFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_signup, container, false);
//
//        mAuth = FirebaseAuth.getInstance();
//        buttoncreateaccount = view.findViewById(R.id.btn_createaccount);
//        mEditEmail = view.findViewById(R.id.email);
//        mEditPassword = view.findViewById(R.id.password);
//        mEditName = view.findViewById(R.id.name);
//        mEditPhonenumber = view.findViewById(R.id.phonenumber);
//        mEditConfirmpass = view.findViewById(R.id.confirmpassword);
//        mfarm = view.findViewById(R.id.farmname);
//        loginlink = view.findViewById(R.id.link_login);
//
////        Toolbar toolbar = view.findViewById(R.id.toolbar);
////        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
////        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
////        ((MainActivity)getActivity()).getSupportActionBar().setTitle("My Dairy Farm");
//
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        final DatabaseReference databaseReference = firebaseDatabase.getReference("message");
//        databaseReference.setValue("Hello World");
//
//        buttoncreateaccount.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//
//
//                if(mEditEmail.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "Email is required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(mEditPassword.getText().toString().isEmpty()){
//                    Toast.makeText(getActivity(), "Password is required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(mEditName.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "Full Name is required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(mEditPhonenumber.getText().toString().isEmpty()) {
//                    Toast.makeText(getActivity(), "Phone Number is required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(!mEditPassword.getText().toString().equals(mEditConfirmpass.getText().toString())){
//                    Toast.makeText(getActivity(), "The two passwords should be the same!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(mfarm.getText().toString().isEmpty()){
//                    Toast.makeText(getActivity(), "Farm name is required!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                String emailString = mEditEmail.getText().toString();
//                String passString = mEditPassword.getText().toString();
//                createAccount(emailString, passString);
//
//            }
//        });
//
//        loginlink.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view) {
////                ((MainActivity)getActivity()).replaceFragments(new LoginFragment(), true, "Login");
//                // Read from the database
//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//                        String value = dataSnapshot.getValue(String.class);
//                        Log.d(TAG, "Value is: " + value);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException());
//                    }
//                });
//            }
//        });
//
//        return view;
//    }
//
//    public void createAccount(String emailString, String passString)
//    {
//
//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setMessage("Logging in...");
//        progressDialog.show();
//
//        String emailStr = mEditEmail.getText().toString();
//        String nameStr = mEditName.getText().toString();
//        String phoneStr = mEditPhonenumber.getText().toString();
//        String farmNameStr = mfarm.getText().toString();
//
//        final User user = new User(nameStr, emailStr, phoneStr, farmNameStr);
//
//        mAuth.createUserWithEmailAndPassword(emailString, passString)
//                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful() ) {
//                            Log.d(TAG, "createUserWithEmail:success");
//
//                            FirebaseAuth mAuth = FirebaseAuth.getInstance();
//                            String userID = mAuth.getCurrentUser().getUid();
//                            FirebaseDatabase.getInstance().getReference("Users").child(userID)
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//
//                                    progressDialog.dismiss();
//                                    SharedPref sharedPref = new SharedPref(getContext());
//                                    User u = new User();
//                                    u.setEmail(user.getEmail());
//                                    u.setName(user.getName());
//                                    sharedPref.setUser(u);
////                                    FirebaseUser user = mAuth.getCurrentUser();
////                                    ((MainActivity)getActivity()).replaceFragments(new DashboardFragment(), true, "Signup");
//                                    Intent intent = new Intent(getContext(), Main2Activity.class);
//                                    startActivity(intent);
//                                }
//                            });
//
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Log.e(TAG, "onComplete: ", task.getException());
//                            progressDialog.dismiss();
//                            Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                });
//
//    }
//
//    public void saveUserDetails(){
//
//    }
//
//}
//
//
//
//
//
