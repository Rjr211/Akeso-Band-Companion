package com.ajustinjames.akesobandcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InputInsurance extends AppCompatActivity {


    EditText edit;
    EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_insurance);
        edit = findViewById(R.id.editText);
        edit2 = findViewById(R.id.editText2);
    }

    public void nextStep(View v){
        if (edit.getText().toString().length() == 0 || edit2.getText().toString().length() == 0){
            Toast.makeText(this, "Cannot leave any field empty, try again.", Toast.LENGTH_LONG).show();
            return;
        } else {
            InputActivity.inputs[6] = edit.getText().toString();
            InputActivity.inputs[7] = edit2.getText().toString();
        }

        Intent NextActivity = new Intent(this, InputNotes.class);
        startActivity(NextActivity);
    }

    public void skip(View v){
        Intent NextActivity = new Intent(this, InputNotes.class);
        startActivity(NextActivity);
    }
}
