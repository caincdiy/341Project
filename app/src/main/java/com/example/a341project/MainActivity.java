package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner user;
    String UserType;
    EditText Uname,Upass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uname= findViewById(R.id.MainEditTextUserName);
        Upass= findViewById(R.id.MainEditTextPassword);
        user=findViewById(R.id.MainUserSpinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.UserType,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user.setAdapter(adapter);
        user.setOnItemSelectedListener(this);
        go();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        UserType=user.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void go(){
        Intent intent=new Intent(this,RestaurantIndex.class);
        startActivity(intent);
    }
}
