package com.ajustinjames.akesobandcompanion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class InputActivity extends AppCompatActivity {

    public static String[] inputs;
    public static int usage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        inputs = new String[9];

    }

    public void startWizard(View v){
        Intent InputName= new Intent(this, InputName.class);
        startActivity(InputName);
    }

}
