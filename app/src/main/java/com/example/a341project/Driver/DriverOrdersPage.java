package com.example.a341project.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.a341project.R;
import com.example.a341project.Restaurant.OrderListview;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DriverOrdersPage extends AppCompatActivity {
    ListView orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_orders_page);

        final String status="1";
        final ArrayList<String> temp;
        temp=loadData(status);
        String[] orderTitle =new String[temp.size()];
        String[] orderDistance =new String[temp.size()];
        String[] orderAmount=new String[temp.size()];
        for (int i=0; i<temp.size();i++){
            String[] arr=temp.get(i).split(",");
            orderTitle[i]="Order"+i;
            orderAmount[i]=arr[arr.length-2];
            int randomNum= (int) (Math.random() * 10) +3;
            orderDistance[i]=randomNum+" km";
        }

        orderList = (ListView)findViewById(R.id.orderlistview);
        OrderListview RL= new OrderListview(this,orderTitle,orderDistance,orderAmount);//create new java object
        orderList.setAdapter(RL);

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//check the list view button is clicked
                Intent intent=new Intent(DriverOrdersPage.this, DriverCustomerOrder.class);
                intent.putExtra("position",position);
                intent.putExtra("orderInfo",temp.get(position));
                intent.putExtra("status",status);
                startActivity(intent);
            }
        });// On item click listener


        if(loadData(status).size()==0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No Order Right Now!",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void DriverOrderOnClickBack(View view){
        Intent intent=new Intent(this,DriverMenu.class);
        startActivity(intent);
    }

    public ArrayList<String> loadData(String status){
        FileInputStream in=null;
        ArrayList<String> temp= new ArrayList<String>();
        try{
            in = openFileInput("CustomerOrder.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info;
            while((info=bufferedReader.readLine())!=null){
                String[] arr=info.split(",");
                if(arr[(arr.length-1)].equals(status)) {
                    temp.add(info);
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try{
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return temp;
    }


}
