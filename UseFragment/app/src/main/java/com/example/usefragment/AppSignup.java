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

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class AppSignup extends Fragment {


    EditText editTextName, editTextNumber, editTextEmail, editTextPassword;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    public AppSignup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mAuth = FirebaseAuth.getInstance();


        View view = inflater.inflate(R.layout.fragment_app_signup, container, false);

         editTextName = view.findViewById(R.id.appSignupEnterName);
         editTextNumber = view.findViewById(R.id.appSignupEnterMobile);
         editTextEmail = view.findViewById(R.id.appSignupEnterEmail);
         editTextPassword = view.findViewById(R.id.appSignupEnterPassword);
         progressBar = view.findViewById(R.id.appSignupProgressBar);


        Button button = view.findViewById(R.id.appSignupBtnConfirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    register();
                }

            private void register() {
                String name = editTextName.getText().toString().trim();
                String number = editTextNumber.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(name.isEmpty()){
                   editTextName.setError("Name is required!");
                   editTextName.requestFocus();
                   return;
                }

                if (number.isEmpty()){
                    editTextNumber.setError("Enter Valid Phone Number");
                    editTextNumber.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmail.setError("Please provide valid email!");
                    editTextEmail.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();
                    return;
                }

                if (password.length() < 6){
                    editTextPassword.setError("Min Password length should be 6 Character");
                    editTextPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                  User user = new User(name, number, email);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                           if (task.isSuccessful()){
                                               Toast.makeText(getContext(), "User has been registered Succesfully!", Toast.LENGTH_LONG).show();
                                               progressBar.setVisibility(View.GONE);
                                           }
                                           else {
                                               Toast.makeText(getContext(), "Failed to Signup! Try again", Toast.LENGTH_LONG).show();
                                               progressBar.setVisibility(View.GONE);
                                           }
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(getContext(), "Failed to Signup! Try again", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });

            }
        });
        return view;
    }

}


//This codes belongs to Data Passing from one fragment to another Fragment using bundle
//                Bundle bundle = new Bundle();
//                bundle.putString("keyName",editTextName.getText().toString());
//                bundle.putString("keyNumber",editTextNumber.getText().toString());
//                bundle.putString("keyEmail",editTextEmail.getText().toString());
//                bundle.putString("keyPassword",editTextPassword.getText().toString());

//                UserProfile userProfile = new UserProfile();
//                userProfile.setArguments(bundle);

//                FragmentManager fragmentManager =  getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.Appfragment, new AppSignup());
//                fragmentTransaction.commit();
//
//               getFragmentManager().beginTransaction().replace(R.id.Appfragment, userProfile).commit();
// End Data passing Code
