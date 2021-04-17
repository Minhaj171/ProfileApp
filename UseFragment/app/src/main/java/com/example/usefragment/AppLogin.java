package com.example.usefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AppLogin extends Fragment {

    Button loginButton;
    EditText editTextEmail, editTextPassword;
    TextView textView;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    public AppLogin() {
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
        View view = inflater.inflate(R.layout.fragment_app_login, container, false);

        mAuth = FirebaseAuth.getInstance();

        loginButton = view.findViewById(R.id.apploginButton);
        textView = view.findViewById(R.id.apploginSignupLink);
        progressBar = view.findViewById(R.id.apploginProgressBar);
        editTextEmail = view.findViewById(R.id.apploginEditTextEmail);
        editTextPassword = view.findViewById(R.id.apploginEditTextPassword);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_appLogin_to_appSignup);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }

            private void ClearText(){
                editTextEmail.setText("");
                editTextPassword.setText("");
            }

            private void userLogin() {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    editTextEmail.setError("Eamil is required!");
                    editTextEmail.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    editTextPassword.setError("Password is required!");
                    editTextPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    editTextPassword.setError("Min Password is 6 charecter");
                    editTextPassword.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    if (user.isEmailVerified()){
                                        //redirect to user dashboard
                                        final NavController navController = Navigation.findNavController(view);
                                        Toast.makeText(getContext(), "Login is Successfull", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        ClearText();
                                        navController.navigate(R.id.action_appLogin_to_appDashBoard);
                                    }
                                    else {
                                        user.sendEmailVerification();
                                        Toast.makeText(getContext(), "Check YOur email to verify", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                } else {
                                    Toast.makeText(getContext(), "Failed to loign! Try again", Toast.LENGTH_LONG).show();
                                    ClearText();
                                    progressBar.setVisibility(View.GONE);
                                }

                            }
                        });
            }
        });

        return view;
    }
    }

//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
////        final NavController navController = Navigation.findNavController(view);
////
////
////        textView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                navController.navigate(R.id.action_appLogin_to_appSignup);
////            }
////        });
////
////        loginButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                navController.navigate(R.id.action_appLogin_to_appDashBoard);
////            }
////        });
////    }
//}