package com.example.a341project.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a341project.MapsActivity;
import com.example.a341project.R;

public class DriverCustomerOrder extends AppCompatActivity {

    TextView name,ph,ad;
    String cname,phn,add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_customer_order);

        name=findViewById(R.id.custName);
        ph=findViewById(R.id.customerPhoneNumberOutput);
        ad=findViewById(R.id.customerAddressInput);

        Intent lastIntent=getIntent();
        final String info=lastIntent.getStringExtra("orderInfo");
        String[] arr=info.split(",");

        cname = arr[0]; phn = arr[2]; add = arr[1];

        name.setText(cname); ph.setText(phn); ad.setText(add);
    }

    public void acceptClick(View view) {
        Toast.makeText(this, "Accepted!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("user","driver");
        intent.putExtra("MapCaddress",add);
        intent.putExtra("MapCphnoe",phn);
        startActivity(intent);
    }

    public void goBackClick(View view) {
        Intent intent = new Intent(this, DriverOrdersPage.class);
        startActivity(intent);

        finish();
    }
}
