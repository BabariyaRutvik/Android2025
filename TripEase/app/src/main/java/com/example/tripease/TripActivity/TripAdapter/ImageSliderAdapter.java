package com.example.tripease.TripActivity.TripAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripease.R;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<Slider> {

    private List<Integer> imageList;
    private Context context;

    // creating Constructor
    public ImageSliderAdapter(List<Integer> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public Slider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_image,parent, false);
        Slider slider = new Slider(view);
        return slider;
    }

    @Override
    public void onBindViewHolder(@NonNull Slider holder, int position) {
        holder.imageslide.setImageResource(imageList.get(position));
        holder.imageslide.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
class Slider extends RecyclerView.ViewHolder{
    ImageView imageslide;
    public Slider(@NonNull View itemView) {
        super(itemView);

        imageslide = itemView.findViewById(R.id.slider_image);
    }
}
