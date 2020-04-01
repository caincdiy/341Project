package com.example.a341project.Customer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a341project.R;

//helper class for restaurant list do nothing

public class restaurant_list_view extends ArrayAdapter<String> {

    private String [] Title;
    private String [] Description;
    private int [] Images;
    private Activity context;


    public restaurant_list_view(Activity context, String [] Title,String [] Description,int [] Images) {
        super(context, R.layout.restaurant_list_view,Title);
        this.context=context;
        this.Description=Description;
        this.Title=Title;
        this.Images=Images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View r=convertView;
       ViewHolder viewHolder=null;
       if(r==null){
           LayoutInflater layoutInflater=context.getLayoutInflater();
           r=layoutInflater.inflate(R.layout.restaurant_list_view,null,true);
           viewHolder=new ViewHolder(r);
           r.setTag(viewHolder);
       }//if r==null
       else{
           viewHolder=(ViewHolder) r.getTag();

       }
       viewHolder.ivw.setImageResource(Images[position]);
       viewHolder.tvw1.setText(Title[position]);
       viewHolder.tvw2.setText(Description[position]);

        return r;



    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        ImageView ivw;
        ViewHolder(View v){
        tvw1=(TextView) v.findViewById(R.id.Restaurant_list_Title);
        tvw2=(TextView) v.findViewById(R.id.Restaurant_list_Description);
        ivw=(ImageView) v.findViewById(R.id.Restaurant_list_image);

        }//cons

    }
}
