package com.example.androidtut;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //begin the transaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //Replace the contents of this container with new fragment
        fragmentTransaction.replace(R.id.frame_layout, new HotelSearchFragment());

        //Complete changes added above
        fragmentTransaction.commit();
    }
}