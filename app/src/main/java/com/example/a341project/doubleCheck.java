package com.example.a341project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class doubleCheck extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doublecheck);
        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.6),(int)(height*.3));

    }
    public void cancelOrder(View view){
        Intent lastIntent=getIntent();
        int index=lastIntent.getIntExtra("position",0);
        FileInputStream in=null;
        ArrayList<String> temp= new ArrayList<String>();
        try{
            in = openFileInput("sample.txt");
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
                        finalContent+="2\n";
                        count++;
                    }else{
                        finalContent+=info+"\n";
                        count++;
                    }
                }else{
                    finalContent+=info+"\n";
                }
                FileOutputStream fOut = openFileOutput("sample.txt",
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
        Intent intent=new Intent(this,RestaurantIndex.class);
        startActivity(intent);
    }
    public void regretCancellation(View view){
        finish();
    }
}
