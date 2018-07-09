package com.strath.mydairyfarm;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.auth.FirebaseAuth;


public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }

    private DrawerLayout drawer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Dashboard");

        drawer = view.findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            // Dialog
            ((MainActivity)getActivity()).runOnUiThread(new Runnable() {
                public void run() {

                    new MaterialDialog.Builder(getActivity())
                            .title("Confirm Log Out")
                            .cancelable(false)
                            .content("Are you sure you want to Log Out?")
                            .positiveText("OK")
                            .negativeText("Cancel")
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
//                                    Settings settings = new Settings();
//                                    settings.setIsloggedin(false, getContext());
//                                    settings.setUser(null, getContext());
                                    FirebaseAuth.getInstance().signOut();
                                    ((MainActivity)getActivity()).replaceFragments(new LoginFragment(), false, "Login");

                                }

                                @Override
                                public void onNegative(MaterialDialog dialog) {
                                    dialog.cancel();
                                }

                            }).show();
                }
            });

        }else if(id == R.id.action_changepassword){
            ((MainActivity)getActivity()).replaceFragments(new ChangePasswordFragment(), true, "Change Password");
        }

        return super.onOptionsItemSelected(item);
    }

}
