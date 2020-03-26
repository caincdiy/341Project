package com.example.a341project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OrderListview extends ArrayAdapter<String> {
    private String [] orderTitle;
    private String [] orderDistance;
    private String [] orderAmount;
    private Activity context;


    public OrderListview(Activity context, String [] orderTitle, String [] orderDistance, String [] orderAmount) {
        super(context,R.layout.listview,orderTitle);
        this.context=context;
        this.orderDistance=orderDistance;
        this.orderTitle=orderTitle;
        this.orderAmount=orderAmount;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;
        if(r==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.listview,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }//if r==null
        else{
            viewHolder=(ViewHolder) r.getTag();

        }
        viewHolder.tvw1.setText(orderTitle[position]);
        viewHolder.tvw2.setText("Distance: "+orderDistance[position]);
        viewHolder.tvw3.setText("Amount: "+orderAmount[position]);

        return r;



    }
    class ViewHolder{
        TextView tvw1;
        TextView tvw2;
        TextView tvw3;
        ViewHolder(View v){
            tvw1=(TextView) v.findViewById(R.id.orderTitle);
            tvw2=(TextView) v.findViewById(R.id.orderDistance);
            tvw3=(TextView) v.findViewById(R.id.orderAmount);

        }//cons

    }
}
