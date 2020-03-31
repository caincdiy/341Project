package com.example.a341project.Restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a341project.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Orders extends AppCompatActivity {

    ListView orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_activity_orders);

        Intent lastIntent=getIntent();
        final String status=lastIntent.getStringExtra("status");
        final ArrayList<String> temp;
        temp=loadData(status);
        String[] orderTitle =new String[temp.size()];
        String[] orderDistance =new String[temp.size()];
        String[] orderAmount=new String[temp.size()];

        for (int i=0; i<temp.size();i++){

            String[] arr=temp.get(i).split(",");
            orderTitle[i]="Order"+i;
            orderAmount[i]=arr[arr.length-2];
            orderDistance[i]="5 km";
        }



        orderList = (ListView)findViewById(R.id.orderlistview);
        OrderListview RL= new OrderListview(this,orderTitle,orderDistance,orderAmount);//create new java object
        orderList.setAdapter(RL);

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//check the list view button is clicked
                Intent intent=new Intent(Orders.this, OrderDetail.class);
                intent.putExtra("position",position);
                intent.putExtra("orderInfo",temp.get(position));
                intent.putExtra("status",status);
                startActivity(intent);
            }
        });// On item click listener

    }
    public void OrdersOnClickBack(View view){
        finish();
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
