package com.example.tripease.TripActivity.TripAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripease.R;
import com.example.tripease.TripActivity.TripModel.FevoriteManager;
import com.example.tripease.TripActivity.TripModel.Top10Model;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<MyPlaces>
{
    private Context context;
    private ArrayList<Top10Model> topplacesList;

    public Top10Adapter(Context context, ArrayList<Top10Model> topplacesList) {
        this.context = context;
        this.topplacesList = topplacesList;
    }


    @NonNull
    @Override
    public MyPlaces onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top10_design,parent,false);
        MyPlaces places = new MyPlaces(view);
        return places;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPlaces holder, int position) {
        Top10Model model = topplacesList.get(position);

        holder.top_10_image.setImageResource(model.getImage());
        holder.text_place_name.setText(model.getName());
        holder.text_place_location.setText(model.getLocation());
        holder.text_place_bestTime.setText(model.getBest_time());

        boolean isFav = FevoriteManager.isFevorite(context, model.getId());
        if (isFav) {
            holder.imageFevorite.setImageResource(R.drawable.ic_favorite_filled);
        } else {
            holder.imageFevorite.setImageResource(R.drawable.ic_favorite_border);

        }

        holder.imageFevorite.setOnClickListener(v -> {

            // Load animation on click
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.fevorite_place);
            holder.imageFevorite.startAnimation(animation);

            // Toggle favorite
            if (FevoriteManager.isFevorite(context, model.getId())) {
                FevoriteManager.RemoveFevorite(context, model.getId());
                holder.imageFevorite.setImageResource(R.drawable.ic_favorite_border);
            } else {
                FevoriteManager.SavedFevorite(context, model);
                holder.imageFevorite.setImageResource(R.drawable.ic_favorite_filled);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topplacesList.size();
    }
}
class MyPlaces extends RecyclerView.ViewHolder{
   ImageView top_10_image,imageFevorite;
   TextView text_place_name,text_place_location,text_place_bestTime;
    public MyPlaces(View itemView) {
        super(itemView);

        top_10_image = itemView.findViewById(R.id.image_top10);
        text_place_name = itemView.findViewById(R.id.text_place_name);
        text_place_location = itemView.findViewById(R.id.text_location);
        text_place_bestTime = itemView.findViewById(R.id.text_bestTime);
        imageFevorite = itemView.findViewById(R.id.fevorite_place_image);




    }
}
