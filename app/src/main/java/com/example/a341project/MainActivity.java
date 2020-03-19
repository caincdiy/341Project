package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
        user=findViewById(R.id.MainUserSpinner);//user type
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.UserType,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user.setAdapter(adapter);
        user.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        UserType=user.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void ClickLogIn(View view){
        String line="";
        boolean check=false;

        if(UserType.equals("Customer")){//read the file and check the username and password
            try {
                FileInputStream file = openFileInput("Customer.txt");
                InputStreamReader ISR = new InputStreamReader(file);
                BufferedReader buff =new BufferedReader(ISR);

                while((line=buff.readLine())!=null){
                    String []part=line.split(",");
                    if(Uname.getText().toString().equals(part[0])&&Upass.getText().toString().equals(part[1])) {
                        check = true;
                    }//if true
                }//end of while
            }catch(IOException e){
                e.printStackTrace();
            }//catch

            if(check==true){ //if the username and password is correct, jump to the corresponding page
            Intent intent=new Intent(MainActivity.this,CustomerView.class);
            startActivity(intent);
        }//if equal
        else{
            Toast.makeText(MainActivity.this,"Wrong password, please check your password and user name",Toast.LENGTH_LONG).show();
        }//password not match
        }//if user ==customer


        else if(UserType.equals("Restaurant")){
            try {
                FileInputStream file = openFileInput("Restaurant.txt");
                InputStreamReader ISR = new InputStreamReader(file);
                BufferedReader buff =new BufferedReader(ISR);

                while((line=buff.readLine())!=null){
                    String []part=line.split(",");
                    if(Uname.getText().toString().equals(part[0])&&Upass.getText().toString().equals(part[1])) {
                        check = true;
                    }//if check=true
                }//end of while
            }catch(IOException e){
                e.printStackTrace();
            }//end of catch
            if(check==true){
            Intent intent=new Intent(MainActivity.this,RestaurantView.class);
            startActivity(intent);
        }//if equal
        else{
            Toast.makeText(MainActivity.this,"Wrong password, please check your password and user name",Toast.LENGTH_LONG).show();
        }//password not match

        }else{
            try {
                FileInputStream file = openFileInput("Driver.txt");
                InputStreamReader ISR = new InputStreamReader(file);
                BufferedReader buff =new BufferedReader(ISR);

                while((line=buff.readLine())!=null){
                    String []part=line.split(",");
                    if(Uname.getText().toString().equals(part[0])&&Upass.getText().toString().equals(part[1])) {
                        check = true;
                    }//if check =true
                }//end of while
            }catch(IOException e){
                e.printStackTrace();
            }//catch
            if(check==true){
            Intent intent=new Intent(MainActivity.this,DriverView.class);
            startActivity(intent);
        }//if equal
                    else{
            Toast.makeText(MainActivity.this,"Wrong password, please check your password and user name",Toast.LENGTH_LONG).show();
        }//password not match

        }//else driver

    }//click log in

    public void ClickCreateAccount(View view){//when click create account, jump to the create account page
        Intent intent=new Intent(MainActivity.this,Create_Account.class);
        Uname.getText().clear();
        Upass.getText().clear();
        startActivity(intent);
    }//click create account
}
