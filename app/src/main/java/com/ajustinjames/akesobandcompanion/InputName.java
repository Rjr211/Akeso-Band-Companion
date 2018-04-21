package com.ajustinjames.akesobandcompanion;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.ajustinjames.akesobandcompanion.InputActivity;

public class InputName extends AppCompatActivity {
    private String name;
    EditText edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        edit = findViewById(R.id.editText);
    }

    public void nextStep(View v){
        if (edit.getText().toString().length() == 0){
            Toast.makeText(this, "Cannot leave field empty, try again.", Toast.LENGTH_LONG).show();
            return;
        } else {
            name = edit.getText().toString();
            InputActivity.inputs[0] = name;
        }

        Intent NextActivity = new Intent(this, InputAllergies.class);
        startActivity(NextActivity);
    }
}
