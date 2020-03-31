package com.example.a341project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a341project.MapsActivity;
import com.example.a341project.R;

public class MyAccountPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_page);
    }

    public void CustomerViewTrack(View view){//if trackMyOrder button is clicked jump to map page
        Intent intent=new Intent(MyAccountPage.this, MapsActivity.class);
        intent.putExtra("user","customer");
        startActivity(intent);

    }

    public void OnProgress(View view){
        Toast.makeText(MyAccountPage.this,"On Progress. Coming Soon !", Toast.LENGTH_LONG).show();
    }
}
