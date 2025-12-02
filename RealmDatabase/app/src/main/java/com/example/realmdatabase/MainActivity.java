package com.example.realmdatabase;

import android.app.Dialog;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realmdatabase.RealmDatabase.CustomToastinsert;
import com.example.realmdatabase.RealmDatabase.DeleteCustom;
import com.example.realmdatabase.RealmDatabase.RealmAdapter;
import com.example.realmdatabase.RealmDatabase.RealmModel;
import com.example.realmdatabase.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Realm realm;
    RealmAdapter adapter;
    RealmResults<RealmModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        realm = Realm.getDefaultInstance();
        // Removed redundant Realm.init(this); it is already in Application class

        binding.realmDataRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.realmDataRecyclerview.setHasFixedSize(true);



        // now fetching the data
        list = realm.where(RealmModel.class).findAll();
        adapter = new RealmAdapter(this,list);
        binding.realmDataRecyclerview.setAdapter(adapter);


        // now inserting the data
        binding.addBtnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });
        // now deleting the Data
        DeleteData();




    }
    private void AddData(){
        // now inserting the data
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_data);
        dialog.setCancelable(true);
        if(dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }


        TextInputEditText edit_text_name = dialog.findViewById(R.id.edit_text_name);
        TextInputEditText edit_phone = dialog.findViewById(R.id.edit_text_phone_number);
        TextInputEditText edit_age = dialog.findViewById(R.id.edit_text_age);
        TextInputEditText edit_email = dialog.findViewById(R.id.edit_text_email);
        TextInputEditText edit_password = dialog.findViewById(R.id.edit_text_password);

        // Corrected ID from R.id.add_btn_data to R.id.add_data_btn
        AppCompatButton add_btn = dialog.findViewById(R.id.add_data_btn);

        if (add_btn != null) {
            add_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // now inserting data into realms
                    realm.executeTransaction(r ->{
                        // Using Number type for ID since RealmModel has int but currentTimeMillis is long.
                        // Ideally RealmModel id should be long, but casting to int for now as defined in model.
                        // Watch out for overflow if using int.
                        Number currentIdNum = r.where(RealmModel.class).max("id");
                        int nextId;
                        if(currentIdNum == null) {
                            nextId = 1;
                        } else {
                            nextId = currentIdNum.intValue() + 1;
                        }

                        RealmModel realmModel = r.createObject(RealmModel.class, nextId);

                        realmModel.setPerson_name(edit_text_name.getText() != null ? edit_text_name.getText().toString() : "");
                        realmModel.setPhone_number(edit_phone.getText() != null ? edit_phone.getText().toString() : "");
                        realmModel.setAge(edit_age.getText() != null ? edit_age.getText().toString() : "");
                        realmModel.setEmail(edit_email.getText() != null ? edit_email.getText().toString() : "");
                        realmModel.setPassword(edit_password.getText() != null ? edit_password.getText().toString() : "");


                    });
                    adapter.notifyDataSetChanged();
                    CustomToastinsert.show(getApplicationContext(),"Data inserted");

                    dialog.dismiss();
                }
            });
        }
        dialog.show();

    }
    private void DeleteData(){
        // Swipe left to delete the data with icon
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(
                    @NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                // for deleting the data
                int position = viewHolder.getAdapterPosition();
                
                if (position != RecyclerView.NO_POSITION && list != null && position < list.size()) {
                     realm.executeTransaction(r -> {
                        RealmModel item = list.get(position);
                        if(item != null && item.isValid()) {
                            item.deleteFromRealm();
                        }
                     });

                    adapter.notifyItemRemoved(position);
                    DeleteCustom.show(getApplicationContext()," Data Deleted!");
                } else {
                     // Reset swipe if invalid position
                     adapter.notifyItemChanged(position);
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c,
                                    @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY,
                                    int actionState,
                                    boolean isCurrentlyActive) {

                ColorDrawable background = new ColorDrawable(Color.RED);
                Drawable deleteIcon = ContextCompat.getDrawable(MainActivity.this,R.drawable.delete_realm);

                View itemview = viewHolder.itemView;


                background.setBounds(itemview.getRight()
                +(int) dX,
                        itemview.getTop()
                ,itemview.getRight(),
                        itemview.getBottom());

                background.draw(c);
                
                if (deleteIcon != null) {
                    int iconMargin = (itemview.getHeight() - deleteIcon .getIntrinsicHeight()) / 2;
                    int iconTop = itemview.getTop() + iconMargin;
                    int iconBottom = iconTop + deleteIcon .getIntrinsicHeight();
                    int iconLeft = itemview.getRight() - iconMargin - deleteIcon.getIntrinsicHeight();
                    int iconRight = itemview.getRight() - iconMargin;


                    deleteIcon.setBounds(iconLeft,iconTop,iconRight,iconBottom);
                    deleteIcon.draw(c);
                }

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        new ItemTouchHelper(callback).attachToRecyclerView(binding.realmDataRecyclerview);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(realm != null) {
            realm.close();
        }
    }

}
