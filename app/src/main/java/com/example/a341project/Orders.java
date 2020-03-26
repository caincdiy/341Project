package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
        //writeSample();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
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
                Intent intent=new Intent(Orders.this,OrderDetail.class);
                intent.putExtra("position",position);
                intent.putExtra("orderInfo",temp.get(position));
                intent.putExtra("status",status);
                startActivity(intent);
            }
        });// On item click listener

    }
    //write same sample order manually
    public void writeSample(){
        String info="John,,7788922113,fries2,coke1,pizza1,-,$24,0\n";
        try{
            FileOutputStream fOut = openFileOutput("sample.txt",
                    MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(info);
            osw.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
        }
    }
    public ArrayList<String> loadData(String status){
        FileInputStream in=null;
        ArrayList<String> temp= new ArrayList<String>();
        try{
            in = openFileInput("sample.txt");
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
