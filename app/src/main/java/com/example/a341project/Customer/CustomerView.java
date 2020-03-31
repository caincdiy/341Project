package com.example.a341project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a341project.R;

public class CustomerView extends AppCompatActivity {

    String[] RestaurantTitle ={"Oh! Pizza","Oishi Sushi","Delicious Asian cuisine","Burger Queen","Nice Salad"};
    String[] RestaurantDescription ={"Restaurant Description: \nDelicious Pizza, fries and soft drink ","Restaurant Coming Soon","Restaurant Coming Soon","Restaurant Coming Soon","Restaurant Coming Soon"};
    int[] RestaurantImages={R.drawable.cheese_pizza,R.drawable.sushi_combo,R.drawable.curry_rice,R.drawable.cheese_burger,R.drawable.green_salad};//elements that will display in the list view
    ListView list;
    String userName;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        Bundle bn = getIntent().getExtras(); // passing value
        userName = bn.getString("uname");

        list=(ListView)findViewById(R.id.listview);
        restaurant_list_view RL= new restaurant_list_view(this,RestaurantTitle,RestaurantDescription,RestaurantImages);//create new java object
        list.setAdapter(RL);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//check the list view button is clicked
                if(id==0){
                    Intent intent=new Intent(CustomerView.this, oh_pizza_page.class);
                    String uname = userName;
                    intent.putExtra("uname",uname);
                    startActivity(intent);
                }// jump to Oh Pizza page
                else {
                    Toast.makeText(CustomerView.this,"Coming Soon !", Toast.LENGTH_LONG).show();
                }//else comming soon
                }
        });// On item click listener


    }
    public void CustomerViewClickMyAccount(View view){//if my account button is clicked jump to new page
        Intent intent=new Intent(CustomerView.this, MyAccountPage.class);
        startActivity(intent);
    }//click my account
}
