package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RestaurantIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_index);
        TextView icomingNum=findViewById(R.id.uncheckedOrderNum);
        TextView cancelledNum=findViewById(R.id.cancelledOrderNum);
        TextView checkedNum=findViewById(R.id.checkedOrderNum);
        icomingNum.setText(""+loadData("0").size());
        cancelledNum.setText(""+loadData("2").size());
        checkedNum.setText(""+loadData("1").size());
    }
    public void showIncomingOrders(View view){
        Intent intent=new Intent(this,Orders.class);
        intent.putExtra("status","0");
        startActivity(intent);
    }

    public void showCancelledOrders(View view){
        Intent intent=new Intent(this,Orders.class);
        intent.putExtra("status","2");
        startActivity(intent);
    }
    public void showacceptedOrders(View view){
        Intent intent=new Intent(this,Orders.class);
        intent.putExtra("status","1");
        startActivity(intent);
    }
    public void notWorkNow(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "This function is not in major task, thus we did not develop it",
                Toast.LENGTH_SHORT);

        toast.show();
    }
    public ArrayList<String> loadData(String status) {
        FileInputStream in = null;
        ArrayList<String> temp = new ArrayList<String>();
        try {
            in = openFileInput("sample.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                String[] arr = info.split(",");
                if (arr[(arr.length - 1)].equals(status)) {
                    temp.add(info);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return temp;
    }
}
