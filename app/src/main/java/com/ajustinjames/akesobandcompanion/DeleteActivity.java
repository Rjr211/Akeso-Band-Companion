package com.ajustinjames.akesobandcompanion;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

public class DeleteActivity extends AppCompatActivity {


    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    Tag tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_delete);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            //nfc not supported by your advice
            Toast.makeText(getApplicationContext(), "NFC not supported by this device", Toast.LENGTH_LONG).show();
            this.finish();
            ;
        }

        if (!nfcAdapter.isEnabled()) {
            //nfc disabled
            Toast.makeText(getApplicationContext(), "NFC radio is off, please turn it on.", Toast.LENGTH_LONG).show();
            this.finish();
        }

        pendingIntent = PendingIntent.getActivity(this,0,new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP), 0);
        IntentFilter discovery=new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

        writeTagFilters = new IntentFilter[] {
                discovery
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (nfcAdapter != null){
            nfcAdapter.enableForegroundDispatch(this,pendingIntent,writeTagFilters,null);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            //check if tag can be written
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            try {
                write(tag);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private void write(Tag tag) throws IOException, FormatException {
        NdefRecord[] records = new NdefRecord[9];
        for (int i =0; i<records.length;i++) {

            records[i] = NdefRecord.createTextRecord("en", "");

        }
        NdefMessage message = new NdefMessage(records);
        // Get an instance of Ndef for the tag.
        Ndef ndef = Ndef.get(tag);
        // Enable I/O
        ndef.connect();
        // Write the message
        ndef.writeNdefMessage(message);
        // Close the connection
        ndef.close();
        Toast.makeText(this, "Wrote to Akeso Band", Toast.LENGTH_SHORT).show();
        //return to home and clear everything
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void cancel(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to cancel?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //clear all old activities
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //cancelled action
            }
        });
        builder.show();
    }
}
