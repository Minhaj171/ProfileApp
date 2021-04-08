package com.example.usefragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppDashBoard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppDashBoard extends Fragment {

    CardView cardView;


    public AppDashBoard() {
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
        View view = inflater.inflate(R.layout.fragment_app_dash_board, container, false);

//        final NavController navController = Navigation.findNavController(view);
//
//        cardView = view.findViewById(R.id.appCardviewProfile);
//
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                navController.navigate(R.id.action_appDashBoard_to_userProfile);
//            }
//        });

        return view;
    }
}