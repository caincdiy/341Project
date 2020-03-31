package com.example.a341project.Restaurant;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a341project.R;

public class orderDetailListView extends ArrayAdapter<String> {
    private String [] productName;
    private String [] quantity;
    private String [] addition;
    private Activity context;


    public orderDetailListView(Activity context, String [] productName, String [] quantity, String [] addition) {
        super(context, R.layout.r_order_detail_listview,productName);
        this.context=context;
        this.productName=productName;
        this.quantity=quantity;
        this.addition=addition;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        orderDetailListView.ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.r_order_detail_listview,null,true);
            viewHolder=new orderDetailListView.ViewHolder(r);
            r.setTag(viewHolder);
        }//if r==null
        else{
            viewHolder=(orderDetailListView.ViewHolder) r.getTag();

        }
        viewHolder.tvw1.setText(productName[position]);
        viewHolder.tvw2.setText(quantity[position]);
        viewHolder.tvw3.setText(addition[position]);

        return r;
    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        ViewHolder(View v){
            tvw1=(TextView) v.findViewById(R.id.produvtName);
            tvw2=(TextView) v.findViewById(R.id.quantity);
            tvw3=(TextView) v.findViewById(R.id.addition);

        }//cons

    }
}
