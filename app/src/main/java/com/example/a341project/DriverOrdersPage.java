package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class DriverOrdersPage extends AppCompatActivity {


    TextView customerOrders;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_orders_page);

        //TODO: Read order log with a counter up to 10 orders || might make scrollable
//        customerOrders = findViewById(R.id.customerOrders);
//
//        String file = "output.txt";
//        String line = "";
//        String data = "";
//
//        try{
//            FileInputStream fis = openFileInput(file);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br  = new BufferedReader(isr);
//            while((line=br.readLine()) != null){
//                data += (counter+1) + "\t" + line + "\n";
//                counter++;
//            }
//        } catch (FileNotFoundException e){e.printStackTrace();}
//        catch (Exception e){e.printStackTrace();}
//        customerOrders.setText(data);

    }

    public void onClick(View view){
        Intent intent = new Intent(this, DriverCustomerOrder.class);

        startActivity(intent);
    }
}
