package com.example.a341project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a341project.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CartPage extends AppCompatActivity {

    TextView CheeseP,PeP,Fries,Salad,Cola,Total; // input num
    String CPC,PePC,FriesC,SaladC,ColaC,totalC; // pass info from oh pizza page
    int cpco,pepco,fco,sco,cco; // check to display

    TextView cpn,ppn,fn,sn,cn; // dish name
    TextView cpi,ppi,fi,si,ci; // dish info
    TextView cpc,ppc,fc,sc,cc; // dish cost
    ImageView cpp,ppp,fp,sp,cp; // dish picture

    String uName; // passed from main page

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        CheeseP=(TextView) findViewById(R.id.OhPizzaTextCP); PeP=(TextView) findViewById(R.id.OhPizzaTextPP); // user input
        Fries=(TextView) findViewById(R.id.OhPizzaTextF); Salad=(TextView) findViewById(R.id.OhPizzaTextA);
        Cola=(TextView) findViewById(R.id.OhPizzaTextC); Total=(TextView)findViewById(R.id.OhPizzaTextT);

        cpn=(TextView) findViewById(R.id.textView13); ppn=(TextView) findViewById(R.id.textView15); // dish name
        fn=(TextView) findViewById(R.id.textView14); sn=(TextView) findViewById(R.id.textView16);
        cn=(TextView) findViewById(R.id.textView17);

        cpi=(TextView) findViewById(R.id.textView18); ppi=(TextView) findViewById(R.id.textView20); // dish info
        fi=(TextView) findViewById(R.id.textView21); si=(TextView) findViewById(R.id.textView23);
        ci=(TextView) findViewById(R.id.textView24);

        cpc=(TextView) findViewById(R.id.textView30); ppc=(TextView) findViewById(R.id.textView31); // dish cost
        fc=(TextView) findViewById(R.id.textView33); sc=(TextView) findViewById(R.id.textView34);
        cc=(TextView) findViewById(R.id.textView35);

        cpp=(ImageView) findViewById(R.id.imageView3); ppp=(ImageView) findViewById(R.id.imageView4); // dish picture
        fp=(ImageView) findViewById(R.id.imageView6); sp=(ImageView) findViewById(R.id.imageView7);
        cp=(ImageView) findViewById(R.id.imageView12);

        Bundle bn = getIntent().getExtras(); // passing value
        CPC = bn.getString("excp"); PePC = bn.getString("expp");
        FriesC = bn.getString("exf"); SaladC = bn.getString("exs");
        ColaC = bn.getString("exc"); totalC = bn.getString("ext");

        CheeseP.setText(CPC+""); PeP.setText(PePC+""); Fries.setText(FriesC+""); // set passed value
        Salad.setText(SaladC+""); Cola.setText(ColaC+""); Total.setText(totalC+"");

        cpco = Integer.parseInt(CPC); pepco = Integer.parseInt(PePC); // user input convert to int
        fco = Integer.parseInt(FriesC); sco = Integer.parseInt(SaladC);
        cco = Integer.parseInt(ColaC);

        uName = bn.getString("uname");

        // check to display:
        if(cpco==0){
            CheeseP.setVisibility(View.GONE); cpn.setVisibility(View.GONE); cpi.setVisibility(View.GONE);
            cpc.setVisibility(View.GONE); cpp.setVisibility(View.GONE);
        }

        if(pepco==0){
            PeP.setVisibility(View.GONE); ppn.setVisibility(View.GONE); ppi.setVisibility(View.GONE);
            ppc.setVisibility(View.GONE); ppp.setVisibility(View.GONE);
        }

        if(fco==0){
            Fries.setVisibility(View.GONE); fn.setVisibility(View.GONE); fi.setVisibility(View.GONE);
            fc.setVisibility(View.GONE); fp.setVisibility(View.GONE);
        }

        if(sco==0){
            Salad.setVisibility(View.GONE); sn.setVisibility(View.GONE); si.setVisibility(View.GONE);
            sc.setVisibility(View.GONE); sp.setVisibility(View.GONE);
        }

        if(cco==0){
            Cola.setVisibility(View.GONE); cn.setVisibility(View.GONE); ci.setVisibility(View.GONE);
            cc.setVisibility(View.GONE); cp.setVisibility(View.GONE);
        }

        Button button = (Button) findViewById(R.id.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmation();
            }
        });
    }

    public void OhPizzaClickBack(View view){
        finish();
    }// click Back jump to restaurant list page

    // Method onClick to covert user info to .txt then jump to my account
    public void confirmation(){//jump to cart page
        String cpcou = CPC;  String ppcou = PePC; String fcou = FriesC; String scou = SaladC; String ccou = ColaC; // count user
        String tcou = totalC;
        String username = uName;
        Intent intent = new Intent(this, Confirm.class);  // passing info
        intent.putExtra("excp", cpcou); intent.putExtra("expp", ppcou);
        intent.putExtra("exf", fcou); intent.putExtra("exs", scou);
        intent.putExtra("exc", ccou); intent.putExtra("uname", username);
        intent.putExtra("ext", tcou);

        startActivity(intent);
    }

}
