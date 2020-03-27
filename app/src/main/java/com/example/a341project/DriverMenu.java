package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class DriverMenu extends AppCompatActivity {

    TextView orderText, reviewText, changeInfoText, otherInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_menu);

        orderText = findViewById(R.id.orderText);
        reviewText = findViewById(R.id.reviewText);
        changeInfoText = findViewById(R.id.changeInfoText);
        otherInfoText = findViewById(R.id.otherInfoText);
    }
    public void naClick(View view){

        Toast.makeText(DriverMenu.this, "Feature not implemented yet", Toast.LENGTH_SHORT).show();
    }
    public void orderClick(View view){
        Intent intent = new Intent(this,DriverOrdersPage.class);
        startActivity(intent);
    }



}
