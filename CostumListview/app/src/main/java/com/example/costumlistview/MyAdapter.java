package com.example.costumlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends BaseAdapter
{
        private Context context;
        private ArrayList<Model> list;

        public MyAdapter(Context context,ArrayList<Model>list){
            this .context = context;
            this .list = list;
        }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.design,parent,false);

            }
           CircleImageView imageView = convertView.findViewById(R.id.image_car);
           TextView txt_name = convertView.findViewById(R.id.txt_car_name);
           TextView txt_price = convertView.findViewById(R.id.txt_price);


           Model model = list.get(position);

           imageView.setImageResource(model.getImage());
           txt_name.setText(model.getName());
           txt_price.setText(model.getPrice());


           return convertView;
    }
}
