package com.example.a341project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class oh_pizza_page extends AppCompatActivity {

//    String[] RestaurantTitle ={"Oh! Pizza","Oishi Sushi","Delicious Asian cuisine","Burger Queen","Nice Salad"};
//    String[] RestaurantDescription ={"Restaurant Description: \nDelicious Pizza, fries and soft drink ","Restaurant Coming Soon","Restaurant Coming Soon","Restaurant Coming Soon","Restaurant Coming Soon"};
//    int[] RestaurantImages={R.drawable.cheese_pizza,R.drawable.sushi_combo,R.drawable.curry_rice,R.drawable.cheese_burger,R.drawable.green_salad};
//    ListView list;
    TextView CheeseP,PeP,Fries,Salad,Cola,Total;
    int CPCount=0,PePCount=0,FriesCount=0,SaladCount=0,ColaCount=0,totalCount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oh_pizza_page);
        CheeseP=(TextView) findViewById(R.id.OhPizzaTextCheeseP);
        PeP=(TextView) findViewById(R.id.OhPizzaTextPepperP);
        Fries=(TextView) findViewById(R.id.OhPizzaTextFries);
        Salad=(TextView) findViewById(R.id.OhPizzaTextAvocado);
        Cola=(TextView) findViewById(R.id.OhPizzaTextCoca);
        Total=(TextView)findViewById(R.id.OhPizzaTextTotal);


    }

    public void OnPizzaClickCart(View view){
        Intent intent=new Intent(oh_pizza_page.this,CartPage.class);
        startActivity(intent);

    }// click cart


    public void OhPizzaClickBack(View view){
    finish();
    }// click Back


    public void OnPizzaClickMButton(View view){
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


    public void OnPizzaClickPButton(View view){
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
