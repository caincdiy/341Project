package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RestaurantIndex extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_index);
    }
    public void showOrders(View view){
        Intent intent=new Intent(this,Orders.class);
        startActivity(intent);
    }
    public void notWorkNow(View view){
        Toast toast = Toast.makeText(getApplicationContext(),
                "This function is not in major task, thus we did not develop it",
                Toast.LENGTH_SHORT);

        toast.show();
    }
}
