package com.example.tripease.TripActivity.TripAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripease.R;
import com.example.tripease.TripActivity.Top10PlacesActivity;
import com.example.tripease.TripActivity.TripModel.CountryModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountryAdapter extends RecyclerView.Adapter<CountryView> {

    public ArrayList<CountryModel> countryList;
    public Context context;

    public CountryAdapter(Context context, ArrayList<CountryModel> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public CountryView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_design, parent, false);
        CountryView country = new CountryView(view);
        return country;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryView holder, int position) {
        CountryModel country = countryList.get(position);

        holder.countryimage.setImageResource(country.getImage());
        holder.countryname.setText(country.getName());


        // itemview click listener for  country and the respective top 10 places


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountryModel countryModel = countryList.get(position);

                Intent intent = new Intent(context, Top10PlacesActivity.class);
                intent.putExtra("position",position);
                context.startActivity(intent);
                Toast.makeText(context, countryModel.getName(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            }
        });





    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}
class CountryView extends RecyclerView.ViewHolder {

    CircleImageView countryimage;
    TextView countryname;

    public CountryView(@NonNull View itemView) {
        super(itemView);


        countryimage = itemView.findViewById(R.id.country_image);
        countryname = itemView.findViewById(R.id.text_country_name);

    }
}

