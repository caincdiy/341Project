package com.example.a341project.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a341project.MainActivity;
import com.example.a341project.R;

public class DriverMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_menu);
    }

    public void orderClick(View view){
        Intent intent = new Intent(this, DriverOrdersPage.class);
        startActivity(intent);
    }

    public void OnProgress(View view){
        Toast.makeText(DriverMenu.this,"On Progress. Coming Soon !", Toast.LENGTH_LONG).show();
    }

    public void OnClickLogOut(View view){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
