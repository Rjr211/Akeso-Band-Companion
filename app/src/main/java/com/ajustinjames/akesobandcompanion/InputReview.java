package com.ajustinjames.akesobandcompanion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.Charset;

public class InputReview extends AppCompatActivity {
    int size;
    TextView[] displays;
    TextView sizeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_review);
        size = 0;
        //calculate size used up by all the fields
        for (int i=0; i<InputActivity.inputs.length; i++){
            if (InputActivity.inputs[i] != null) {
                size = size + InputActivity.inputs[i].getBytes(Charset.forName("UTF-8")).length;
            }
        }

        displays = new TextView[10];
        displays[0] = (TextView) findViewById(R.id.name1);
        displays[1] = (TextView) findViewById(R.id.allergies1);
        displays[2] = (TextView) findViewById(R.id.medications1);
        displays[3] = (TextView) findViewById(R.id.conditions1);
        displays[4] = (TextView) findViewById(R.id.contact1);
        displays[5] = (TextView) findViewById(R.id.contact2);
        displays[6] = (TextView) findViewById(R.id.insurance1);
        displays[7] = (TextView) findViewById(R.id.insurance2);
        displays[8] = (TextView) findViewById(R.id.notes1);
        sizeView = (TextView) findViewById(R.id.sizeit);

        for (int i=0; i<displays.length-1; i++){
            displays[i].setText(InputActivity.inputs[i]);
        }
        String s = "Space used: "+ Integer.toString(size) +"/888";
        sizeView.setText(s);

    }

    public void startWrite(View v){
        if (size > 888){
            Toast.makeText(this, "Space quota used, please remove some information and try again", Toast.LENGTH_LONG).show();
            return;
        }

        Intent InputName= new Intent(this, WriteActivity.class);
        startActivity(InputName);
    }
}
