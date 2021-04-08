package com.example.usefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class UserProfile extends Fragment {

    TextView textViewName, textViewNumber, textViewEmail, textViewPassword;

    public UserProfile() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile, container, false);

        textViewName = v.findViewById(R.id.appProfileName);
        textViewNumber = v.findViewById(R.id.appProfileMobileNumber);
        textViewEmail = v.findViewById(R.id.appProfileEmail);
        textViewPassword = v.findViewById(R.id.appProfilePassword);

        Bundle bundle = this.getArguments();

        String Name = bundle.getString("keyName");
        textViewName.setText(Name);

        String Number = bundle.getString("keyNumber");
        textViewNumber.setText(Number);

        String Email = bundle.getString("keyEmail");
        textViewEmail.setText(Email);

        String Password = bundle.getString("keyPassword");
        textViewPassword.setText(Password);


        return v;
    }
}