package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Create_Account extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner user;
    String UserType;
    EditText Uname,Upass;
    ArrayList<String> UnameArray=new ArrayList<String>();
    ArrayList<String> UpassArray=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__account);

        user=(Spinner) findViewById(R.id.CreateAccountSpinnerUserType);
        Uname=(EditText) findViewById(R.id.CreateAccountEditTextName);
        Upass=(EditText) findViewById(R.id.CreateAccountEditTextPassword);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.UserType,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        user.setAdapter(adapter);
        user.setOnItemSelectedListener(this);

    }
    public void ClickSubmit(View view){

        if(Uname.getText().toString().length()<1||Upass.getText().toString().length()<1) {// check the username and password can not be empty
            Toast.makeText(Create_Account.this,"Please enter your user name and password",Toast.LENGTH_LONG).show();

        }else {
            if (UserType.equals("Customer")) {
                if (NameExist("Customer.txt", Uname.getText().toString())==false) { //check if the username is not exist
                    String tofile=Uname.getText().toString()+","+Upass.getText().toString()+"\n";
                    FileOutputStream outputStream;
                    try{
                        outputStream= openFileOutput("Customer.txt", Context.MODE_APPEND);//write file
                        outputStream.write(tofile.getBytes());
                        outputStream.close();
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }//catch
                    finish();
                }else{
                    Toast.makeText(Create_Account.this,"The user name already Exist",Toast.LENGTH_LONG).show();
                }//Name Exist

            } else if (UserType.equals("Restaurant")) {
                if (NameExist("Restaurant.txt", Uname.getText().toString())==false) {
                    String tofile=Uname.getText().toString()+","+Upass.getText().toString()+"\n";
                    FileOutputStream outputStream;
                    try{
                        outputStream= openFileOutput("Restaurant.txt", Context.MODE_APPEND);
                        outputStream.write(tofile.getBytes());
                        outputStream.close();
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }//catch
                    finish();
                }else{
                    Toast.makeText(Create_Account.this,"The user name already Exist",Toast.LENGTH_LONG).show();
                }//Name Exist

            } else {
                if (NameExist("Driver.txt", Uname.getText().toString())==false) {
                    String tofile=Uname.getText().toString()+","+Upass.getText().toString()+"\n";
                    FileOutputStream outputStream;
                    try{
                        outputStream= openFileOutput("Driver.txt", Context.MODE_APPEND);
                        outputStream.write(tofile.getBytes());
                        outputStream.close();
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }//catch
                    finish();
                }else{
                    Toast.makeText(Create_Account.this,"The user name already Exist",Toast.LENGTH_LONG).show();
                }//Name Exist

            }//driver
        }//else Uname and U pass length >1

    }//click Submit


    public boolean NameExist(String filename, String name){
        String line="";

        try{
            FileInputStream file =openFileInput(filename);
            InputStreamReader ISR= new InputStreamReader(file);
            BufferedReader buff= new BufferedReader(ISR);

            while ((line=buff.readLine())!=null){//read the file and check if the user name is exist
                String [] part=line.split(",");
                UnameArray.add(part[0]);
                //UpassArray.add(part[1]);
            }//while
        }catch (IOException e){
            e.printStackTrace();
        }//catch
        for(int i=0;i<UnameArray.size();i++){
            if(name.equals(UnameArray.get(i))){
                UnameArray.clear();
                return true;}
        }//for i <Uname
        return false;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        UserType=user.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void goback(View view){
        finish();
    }

}
