package com.ajustinjames.akesobandcompanion;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InputMedications extends AppCompatActivity {

    private ArrayList<String> medications;
    EditText edit;
    ListView list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_medications);
        edit = findViewById(R.id.editText);
        list = findViewById(R.id.list);
        medications = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medications);
        list.setAdapter(adapter);
    }

    public void remover(View v){
        if (list.getAdapter().getCount() == 0){
            Toast.makeText(this,"No medications have been added, cannot remove one.", Toast.LENGTH_SHORT).show();
            return;
        }

        //build alert to select item to remove
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder.setTitle("Select an item to remove");

        String[] strArr = strALToA(medications);

        //set dialog items
        builder.setItems(strArr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //record selected removal
                final int remove = which;
                builder1.setTitle("Are you sure?");
                builder1.setMessage("Please confirm that you want to remove " + medications.get(which) + "?");
                builder1.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        medications.remove(remove);
                        update();
                    }
                });
                builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ignore
                    }
                });
                builder1.show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //user cancelled
            }
        });

        builder.show();
    }

    public void adder(View v){
        //show an alert to ask the user what they want to add
        final EditText in = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a medication");
        builder.setView(in);

        //button setup
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (in.getText().toString().length() != 0) {
                    medications.add(in.getText().toString());
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //user cancelled
            }
        });
        builder.show();
        update();
    }

    public String[] strALToA(ArrayList<String> input){
        if (input.size() == 0){
            return null;
        }
        //build array
        String[] strArr = new String[input.size()];
        for (int i=0; i<strArr.length; i++){
            strArr[i] = input.get(i);
        }
        return strArr;
    }

    public void nextStep(View v){
        if (medications != null){
            //add all items to one string
            String send = "";
            for (int i=0; i<medications.size(); i++){
                send = send+medications.get(i) + ",";
            }
            if (send.length() > 0) {
                send = send.substring(0, send.length() - 1);
            }
            InputActivity.inputs[3] = send;
        }

        Intent NextActivity = new Intent(this, InputContact.class);
        startActivity(NextActivity);
    }

    public void update(){
        if (medications == null){
            return;
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,medications);
        list.setAdapter(adapter);

    }
}
