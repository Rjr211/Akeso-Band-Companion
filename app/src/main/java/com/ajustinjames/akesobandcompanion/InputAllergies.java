package com.ajustinjames.akesobandcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class InputAllergies extends AppCompatActivity {
    private ArrayList<String> allergies;
    EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        edit = findViewById(R.id.editText);
    }

    public void remover(View v){

    }

    public void adder(View v){

    }

    public void nextStep(View v){


        Intent NextActivity = new Intent(this, InputAllergies.class);
        startActivity(NextActivity);
    }
}
