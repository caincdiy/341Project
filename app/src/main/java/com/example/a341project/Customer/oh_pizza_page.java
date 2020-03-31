package com.example.a341project.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a341project.R;

public class oh_pizza_page extends AppCompatActivity {

//    String[] RestaurantTitle ={"Oh! Pizza","Oishi Sushi","Delicious Asian cuisine","Burger Queen","Nice Salad"};
//    String[] RestaurantDescription ={"Restaurant Description: \nDelicious Pizza, fries and soft drink ","Restaurant Coming Soon","Restaurant Coming Soon","Restaurant Coming Soon","Restaurant Coming Soon"};
//    int[] RestaurantImages={R.drawable.cheese_pizza,R.drawable.sushi_combo,R.drawable.curry_rice,R.drawable.cheese_burger,R.drawable.green_salad};
//    ListView list;
    TextView CheeseP,PeP,Fries,Salad,Cola,Total;
    int CPCount=0,PePCount=0,FriesCount=0,SaladCount=0,ColaCount=0,totalCount=0;
    String uName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oh_pizza_page);
        CheeseP=(TextView) findViewById(R.id.OhPizzaTextCheeseP); PeP=(TextView) findViewById(R.id.OhPizzaTextPepperP);
        Fries=(TextView) findViewById(R.id.OhPizzaTextFries); Salad=(TextView) findViewById(R.id.OhPizzaTextAvocado);
        Cola=(TextView) findViewById(R.id.OhPizzaTextCoca); Total=(TextView)findViewById(R.id.OhPizzaTextTotal); //text view for number of food

        Bundle bn = getIntent().getExtras(); // passing value
        uName = bn.getString("uname");

        Button button = (Button) findViewById(R.id.OhPizzaButtonCart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnPizzaClickCart();
            }
        });
    }

    public void OnPizzaClickCart(){//jump to cart page
        CheeseP.setText(CPCount+""); PeP.setText(PePCount+""); Total.setText(totalCount+"");
        Fries.setText(FriesCount+""); Salad.setText(SaladCount+""); Cola.setText(ColaCount+"");

        String cp = CheeseP.getText().toString(); String pp =PeP.getText().toString();
        String f =Fries.getText().toString(); String s =Salad.getText().toString();
        String c =Cola.getText().toString(); String t =Total.getText().toString();
        String username = uName;

        int checkTotal = Integer.parseInt(t); // if nothing selected give warning
        if(checkTotal==0){
            Toast.makeText(this,"Please select an item.",Toast.LENGTH_LONG).show();
        }else {
            Intent intent = new Intent(oh_pizza_page.this, CartPage.class);  // passing info
            intent.putExtra("excp", cp); intent.putExtra("expp", pp);
            intent.putExtra("exf", f); intent.putExtra("exs", s);
            intent.putExtra("exc", c); intent.putExtra("ext", t);
            intent.putExtra("uname", username);
            startActivity(intent);
        }
    }// click cart


    public void OhPizzaClickBack(View view){
        finish();
    }// click Back jump to restaurant list page


    public void OnPizzaClickMButton(View view){//when click minus button
        switch(view.getId()){
            case R.id.OhPizzaButtonCheesePM:
                if(CPCount>0){
                CPCount--;
                totalCount-=12;
                }
                CheeseP.setText(CPCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonPepPM:
                if(PePCount>0){
                PePCount--;
                totalCount-=15;
                }
                PeP.setText(PePCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonFriesM:
                if(FriesCount>0){
                FriesCount--;
                totalCount-=6;
                }
                Fries.setText(FriesCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonAvocadoM:
                if(SaladCount>0){
                SaladCount--;
                totalCount-=10;
                }
                Salad.setText(SaladCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonCocaM:
                if(ColaCount>0){
                ColaCount--;
                totalCount-=3;
                }
                Cola.setText(ColaCount+"");
                Total.setText(totalCount+"");
                break;
        }//switch
    }// click Minus button


    public void OnPizzaClickPButton(View view){ //when click add button
        switch(view.getId()){
            case R.id.OhPizzaButtonCheesePP:
                    CPCount++;
                    totalCount+=12;
                CheeseP.setText(CPCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonPepPP:
                    PePCount++;
                    totalCount+=15;
                PeP.setText(PePCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonFriesP:
                    FriesCount++;
                    totalCount+=6;
                Fries.setText(FriesCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonAvocadoP:
                    SaladCount++;
                    totalCount+=10;
                Salad.setText(SaladCount+"");
                Total.setText(totalCount+"");
                break;
            case R.id.OhPizzaButtonCocaP:
                    ColaCount++;
                    totalCount+=3;
                Cola.setText(ColaCount+"");
                Total.setText(totalCount+"");
                break;
        }//switch

    }// click add button

}
