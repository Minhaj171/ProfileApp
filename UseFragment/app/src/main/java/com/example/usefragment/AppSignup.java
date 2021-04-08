package com.example.usefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AppSignup extends Fragment {


    EditText editTextName, editTextNumber, editTextEmail, editTextPassword;


    public AppSignup() {
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

        View view = inflater.inflate(R.layout.fragment_app_signup, container, false);

         editTextName = view.findViewById(R.id.appSignupEnterName);
         editTextNumber = view.findViewById(R.id.appSignupEnterMobile);
         editTextEmail = view.findViewById(R.id.appSignupEnterEmail);
         editTextPassword = view.findViewById(R.id.appSignupEnterPassword);

        Button button = view.findViewById(R.id.appSignupBtnConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("keyName",editTextName.getText().toString());
                bundle.putString("keyNumber",editTextNumber.getText().toString());
                bundle.putString("keyEmail",editTextEmail.getText().toString());
                bundle.putString("keyPassword",editTextPassword.getText().toString());

                UserProfile userProfile = new UserProfile();
                userProfile.setArguments(bundle);

//                FragmentManager fragmentManager =  getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.Appfragment, new AppSignup());
//                fragmentTransaction.commit();
//
               getFragmentManager().beginTransaction().replace(R.id.Appfragment, userProfile).commit();


            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}