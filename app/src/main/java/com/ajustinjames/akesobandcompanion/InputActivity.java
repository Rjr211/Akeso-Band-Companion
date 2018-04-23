package com.ajustinjames.akesobandcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class InputActivity extends AppCompatActivity {

    public static String[] inputs;
    public static int usage;
    public static ArrayList<String> allergies;
    public static ArrayList<String> conditions;
    public static ArrayList<String> medications;
    public static ArrayList<String> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Update Akeso Band");
        }
        inputs = new String[9];
    }

    public void startWizard(View v){
        Intent InputName= new Intent(this, InputName.class);
        startActivity(InputName);
    }

}
