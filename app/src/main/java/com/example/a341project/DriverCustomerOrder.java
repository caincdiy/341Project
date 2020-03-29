package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

public class DriverCustomerOrder extends AppCompatActivity {

    Button custButton, restButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_customer_order);

        custButton = findViewById(R.id.customerAddressInput);
        restButton = findViewById(R.id.restaurantAddressInput);

    }

    public void navClickCust(View view) {

        //TODO: Change this so we get the  value of the customer based off their data
        Uri gmmIntentUri = Uri.parse("geo:49.933551, -119.402162");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
    public void navClickRest(View view) {

        //TODO: Change this so we get the streetview value of the restaurant based off their data
        Uri gmmIntentUri = Uri.parse("geo:49.886624, -119.497699");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void acceptClick(View view) {
        Toast.makeText(this, "Accepted!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void goBackClick(View view) {
        Intent intent = new Intent(this, DriverOrdersPage.class);
        startActivity(intent);

        finish();
    }
}
