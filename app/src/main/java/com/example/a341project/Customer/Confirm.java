package com.example.a341project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a341project.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Confirm extends AppCompatActivity {

    String uInfo, uname;
    int cpco, pepco, fco, sco, cco; // check to display
    String CPC, PePC, FriesC, SaladC, ColaC, totalC; // pass info from oh pizza page
    String add,phn;
    EditText ad,ph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle bn = getIntent().getExtras(); // passing value
        assert bn != null;
        CPC = bn.getString("excp"); PePC = bn.getString("expp");
        FriesC = bn.getString("exf"); SaladC = bn.getString("exs");
        ColaC = bn.getString("exc"); totalC = bn.getString("ext");
        uname = bn.getString("uname"); totalC = bn.getString("ext");

        cpco = Integer.parseInt(CPC); pepco = Integer.parseInt(PePC); // user input convert to int
        fco = Integer.parseInt(FriesC); sco = Integer.parseInt(SaladC);
        cco = Integer.parseInt(ColaC);

        Button button = (Button) findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conf();
            }
        });

    }


    public void conf(){//jump to cart page

        ad = (EditText) findViewById(R.id.address);
        ph = (EditText) findViewById(R.id.ph);
        add = ad.getText().toString().trim();
        phn = ph.getText().toString().trim();

        uInfo = uname + "," + add + "," + phn + ",";

        if (cpco != 0) { uInfo = uInfo + "CheesePizza" + CPC + ","; }
        if (pepco != 0) { uInfo = uInfo + "PepperoniPizza"+ PePC + ","; }
        if (fco != 0) { uInfo = uInfo + "Fries" + FriesC + ","; }
        if (sco != 0) {uInfo = uInfo + "AvocadoSalad" + SaladC + ",";}
        if(cco != 0){ uInfo = uInfo + "Cola" + ColaC + ",";}
        uInfo = uInfo +"-,$" + totalC +",0";

        String passtxt = uInfo + "\n";

        String filename = "CustomerOrder.txt";

        FileOutputStream outputStream;
        try{
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(passtxt.getBytes());
            outputStream.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MyAccountPage.class);  // passing info
        startActivity(intent);
    }
}
