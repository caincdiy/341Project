package com.example.a341project.Restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class OrderDetail extends AppCompatActivity {
    ListView productListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.r_activity_order_detail);
        Intent lastIntent=getIntent();
        final String info=lastIntent.getStringExtra("orderInfo");
        final String status=lastIntent.getStringExtra("status");
        final int position=lastIntent.getIntExtra("position",0);;
        if(!status.equals("0")){
            Button accpet=findViewById(R.id.acceptOrder);
            Button cancel=findViewById(R.id.cancelOrder);
            accpet.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
        }
        ArrayList<String> productList=getProductList(info);
        String[] productName =new String[productList.size()];
        String[] quantity =new String[productList.size()];
        String[] addition=new String[productList.size()];
        for (int i=0; i<productList.size();i++){
            String product=productList.get(i);
            productName[i]=product.substring(0,(product.length()-1));
            quantity[i]=product.substring((product.length()-1));
            addition[i]="None";
        }
        productListView = (ListView) findViewById(R.id.orderDetialListview);
        orderDetailListView RL= new orderDetailListView(this,productName,quantity,addition);//create new java object
        productListView.setAdapter(RL);
        Button b=findViewById(R.id.cancelOrder);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderDetail.this, doubleCheck.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
    public void OrderDetailOnClickBack(View view){
        finish();
    }
    public ArrayList<String> getProductList(String info){
        ArrayList<String> temp=new ArrayList<>();
        String[] arr=info.split(",");
        TextView custName=findViewById(R.id.customerName);
        TextView phoneNum=findViewById(R.id.phoneNum);
        TextView total = findViewById(R.id.orderDetailTotal);
        custName.setText("Customer Name: "+arr[0]);
        phoneNum.setText("Phone Number: "+arr[2]);
        total.setText("Total: " + arr[arr.length-2]);
        for(int i=3;i<arr.length;i++){
            if(arr[i].equals("-")){
                break;
            }else {
                temp.add(arr[i]);
            }
        }
        return temp;
    }
    public void acceptOrder(View view){
        Intent lastIntent=getIntent();
        int index=lastIntent.getIntExtra("position",0);
        FileInputStream in=null;
        ArrayList<String> temp= new ArrayList<String>();
        try{
            in = openFileInput("CustomerOrder.txt");
            InputStreamReader inputStreamReader =new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info;
            int count=0;
            String finalContent="";
            while((info=bufferedReader.readLine())!=null){
                String[] arr=info.split(",");
                if(arr[(arr.length-1)].equals("0")) {
                    if(count==index) {
                        for (int i = 0; i < (arr.length-1); i++) {
                            finalContent += arr[i]+",";
                        }
                        finalContent+="1\n";
                        count++;
                    }else{
                        finalContent+=info+"\n";
                        count++;
                    }
                }else{
                    finalContent+=info+"\n";
                }
                FileOutputStream fOut = openFileOutput("CustomerOrder.txt",
                        MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fOut);
                osw.write(finalContent);
                osw.close();
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
        Intent intent=new Intent(OrderDetail.this, RestaurantIndex.class);
        startActivity(intent);

    }

}
