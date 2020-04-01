package com.example.a341project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a341project.MainActivity;
import com.example.a341project.MapsActivity;
import com.example.a341project.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyAccountPage extends AppCompatActivity {
    int order;

    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_page);

        Bundle bn = getIntent().getExtras(); // passing value
        userName = bn.getString("uname");

        order = CustomerView.getOrder();

    }
    public boolean checkorder(){
        String line="";
        boolean order=false;
        try{
            FileInputStream file=openFileInput("CustomerOrder.txt");
            InputStreamReader ISR=new InputStreamReader(file);
            BufferedReader buff =new BufferedReader(ISR);
            while((line=buff.readLine())!=null){
                String []part=line.split(",");
                if(part[part.length-1].equals("1")&&userName.equals(part[0]))
                    order=true;
            }
            return order;
        }catch(IOException e){
            e.printStackTrace();
        }
        return order;
    }

    public void CustomerViewTrack(View view){//if trackMyOrder button is clicked jump to map page
        if(checkorder() == false){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "You may not have placed an order or the restaurant has not accepted your order yet",
                    Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Intent intent = new Intent(MyAccountPage.this, MapsActivity.class);
            intent.putExtra("user", "customer");
            startActivity(intent);
        }
    }

    public void OnProgress(View view){
        Toast.makeText(MyAccountPage.this,"On Progress. Coming Soon !", Toast.LENGTH_LONG).show();
    }

    public void OnClickLogOut(View view){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goview(View view) {
        Intent intent = new Intent(this, CustomerView.class);
        String uname = userName;
        intent.putExtra("uname", uname);
        startActivity(intent);
    }
}
