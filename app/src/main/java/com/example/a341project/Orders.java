package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Orders extends AppCompatActivity {
    boolean orderStatus= false;
    String[] orderTitle ={"Oh! Pizza","order2","order3","order4","order5","order6"};
    String[] orderDistance ={"5 km","...","...","...","...","..."};
    String[] orderAmount={"$24","...","...","...","...","..."};
    ListView orderlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        Intent temp=getIntent();
        orderlist = (ListView)findViewById(R.id.orderlistview);
        listview RL= new listview(this,orderTitle,orderDistance,orderAmount);//create new java object
        orderlist.setAdapter(RL);

        orderlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//check the list view button is clicked
                if(id==0){
                    Intent intent=new Intent(Orders.this,OrderDetail.class);
                    startActivity(intent);
                }// jump to Oh Pizza page
                else {
                    Toast.makeText(Orders.this,"Coming Soon !", Toast.LENGTH_LONG).show();
                }//else comming soon
            }
        });// On item click listener

    }


}
