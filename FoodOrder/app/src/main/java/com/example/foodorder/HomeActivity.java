package com.example.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorder.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // set up clickListener for All checkboxes
        HandledSelection(binding.chkPizza,"Pizza",R.drawable.pizza,249);
        HandledSelection(binding.chkBurger,"Burger",R.drawable.burger,149);
        HandledSelection(binding.chkDhosa,"Dhosa",R.drawable.dhosa,159);
        HandledSelection(binding.chkBhaji,"Poun Bhaji",R.drawable.pow_bhaji,199);
        HandledSelection(binding.chkCofee,"Cofee",R.drawable.cofee,49);


    }
    // function to show and hide selected item
    private void HandledSelection(CheckBox checkBox,String name,int imageRes,int price){
       checkBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if (isChecked){
                // going to billactivity when user will check to the checboxes
                Intent i = new Intent(getApplicationContext(), BillActivity.class);
                i.putExtra("name",name);
                i.putExtra("price",price);
                i.putExtra("image",imageRes);
                startActivity(i);

                // unchacked after navigation
                checkBox.setChecked(false);
            }
       }));

    }
}