package com.strath.mydairyfarm;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LoginFragment loginFragment = new LoginFragment();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
////        fragmentTransaction.add(dashboardFragment, DashboardFragment.class.getSimpleName());
//        fragmentTransaction.add(R.id.fragment_holder, loginFragment);

//        fragmentTransaction.commit();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            //Go to log in page
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            replaceFragments(new DashboardFragment(), false, "");
        }
    }

    public void replaceFragments(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if(tag == null) {
            transaction.replace(R.id.fragment_holder, fragment);
        }else {
            transaction.replace(R.id.fragment_holder, fragment, tag);
        }

        if(addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
