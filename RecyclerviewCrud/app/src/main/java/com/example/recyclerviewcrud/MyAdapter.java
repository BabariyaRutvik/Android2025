package com.example.recyclerviewcrud;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyView> {

    private Context context;
    private ArrayList<Model> list;
    private ArrayList<Model> selectedlist = new ArrayList<>();
    private Activity activity;
    private boolean isEnable = false;
    private boolean isSelectAll = false;
    private ActionMode actionMode;

    public MyAdapter(Activity activity, Context context, ArrayList<Model> list) {
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_list, parent, false);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {
        Model model = list.get(position);

        holder.img_data.setImageResource(model.getImage());
        holder.text_name.setText(model.getName());
        holder.text_age.setText(model.getAge());
        holder.text_phone_number.setText(model.getPhone_number());

        // Single click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnable) {
                    ClickItem(holder);
                } else {
                    // Open update dialog
                    DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                            .setContentHolder(new ViewHolder(R.layout.update_design))
                            .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .create();

                    View view = dialogPlus.getHolderView();

                    EditText edt_update_name = view.findViewById(R.id.edt__update_name);
                    EditText edt_update_age = view.findViewById(R.id.edt_update_age);
                    EditText edt_update_phone = view.findViewById(R.id.edt_update_phone);
                    Button btnupdate = view.findViewById(R.id.update_btn);

                    // Pre-fill old data
                    edt_update_name.setText(model.getName());
                    edt_update_age.setText(model.getAge());
                    edt_update_phone.setText(model.getPhone_number());

                    btnupdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String name = edt_update_name.getText().toString();
                            String age = edt_update_age.getText().toString();
                            String phone = edt_update_phone.getText().toString();

                            list.set(position, new Model(list.get(position).getImage(), name, age, phone));
                            notifyItemChanged(position);
                            dialogPlus.dismiss();
                        }
                    });

                    dialogPlus.show();
                }
            }
        });

        // Long click for multi-select mode
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isEnable) {
                    ActionMode.Callback callback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                            MenuInflater inflater = mode.getMenuInflater();
                            inflater.inflate(R.menu.list_menu, menu);
                            isEnable = true;
                            actionMode = mode;
                            ClickItem(holder);
                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                            mode.setTitle(selectedlist.size() + " Selected");
                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                            int id = item.getItemId();
                            if (id == R.id.menu_delete) {
                                list.removeAll(selectedlist);
                                Toast.makeText(activity, selectedlist.size() + " items deleted!", Toast.LENGTH_SHORT).show();
                                mode.finish();
                                return true;
                            } else if (id == R.id.menu_select_all) {
                                if (selectedlist.size() == list.size()) {
                                    isSelectAll = false;
                                    selectedlist.clear();
                                } else {
                                    isSelectAll = true;
                                    selectedlist.clear();
                                    selectedlist.addAll(list);
                                }
                                notifyDataSetChanged();
                                mode.setTitle(selectedlist.size() + " Selected");
                                return true;
                            }
                            return false;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode mode) {
                            isEnable = false;
                            isSelectAll = false;
                            selectedlist.clear();
                            notifyDataSetChanged();
                            actionMode = null;
                        }
                    };
                    ((AppCompatActivity) v.getContext()).startActionMode(callback);
                } else {
                    ClickItem(holder);
                }
                return true;
            }
        });

        // Selection UI updates
        if (isSelectAll || selectedlist.contains(model)) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            holder.image_check.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            holder.image_check.setVisibility(View.GONE);
        }
    }

    private void ClickItem(MyView holder) {
        Model s = list.get(holder.getAdapterPosition());

        if (selectedlist.contains(s)) {
            selectedlist.remove(s);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            holder.image_check.setVisibility(View.GONE);
        } else {
            selectedlist.add(s);
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            holder.image_check.setVisibility(View.VISIBLE);
        }

        if (actionMode != null) {
            actionMode.setTitle(selectedlist.size() + " Selected");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class MyView extends RecyclerView.ViewHolder {
    ImageView img_data, image_check;
    TextView text_name, text_age, text_phone_number;

    public MyView(@NonNull View itemView) {
        super(itemView);
        img_data = itemView.findViewById(R.id.image_crude);
        text_name = itemView.findViewById(R.id.text_Name);
        text_age = itemView.findViewById(R.id.text_age);
        text_phone_number = itemView.findViewById(R.id.text_phone_number);
        image_check = itemView.findViewById(R.id.check_box);
    }
}
