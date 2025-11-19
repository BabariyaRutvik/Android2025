package com.example.products;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Model> product_list;
    private ArrayList<Model> selectedList = new ArrayList<>();
    private onSelectionChangeListener listener;

    public interface onSelectionChangeListener{
        void  onselectionChange(ArrayList<Model>selectedItems);
    }

    public MyAdapter(Context context, ArrayList<Model> product_list,onSelectionChangeListener listener) {
        this.context = context;
        this.product_list = product_list;
        this .listener = listener;

    }

    @Override
    public int getCount() {
        return product_list.size();
    }

    @Override
    public Object getItem(int position) {
        return product_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_design, parent, false);

        Model model = product_list.get(position);

        CircleImageView image_product = view.findViewById(R.id.image_product);
        TextView text_product_name = view.findViewById(R.id.txt_product_name);
        TextView text_product_price = view.findViewById(R.id.text_price);
        TextView text_description = view.findViewById(R.id.txt_description);
        CheckBox checkBox = view.findViewById(R.id.checkbox_select);

        image_product.setImageResource(model.getImage());
        text_product_name.setText(model.getName());
        text_product_price.setText(model.getPrice());
        text_description.setText(model.getDescription());

        // 🔹 Update product when clicked
        view.setOnClickListener(v -> {
            DialogPlus dialogPlus = DialogPlus.newDialog(context)
                    .setContentHolder(new ViewHolder(R.layout.update_data))
                    .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .create();

            View myview = dialogPlus.getHolderView();

            TextInputEditText product_name = myview.findViewById(R.id.edt_product_name);
            TextInputEditText product_price = myview.findViewById(R.id.edt_product_price);
            TextInputEditText product_description = myview.findViewById(R.id.edt_product_description);
            Button btnupdate = myview.findViewById(R.id.update_data_btn);

            product_name.setText(model.getName());
            product_price.setText(model.getPrice());
            product_description.setText(model.getDescription());

            dialogPlus.show();

            btnupdate.setOnClickListener(v1 -> {
                model.setName(product_name.getText().toString());
                model.setPrice(product_price.getText().toString());
                model.setDescription(product_description.getText().toString());

                notifyDataSetChanged();
                Toast.makeText(context, "Product Updated!", Toast.LENGTH_SHORT).show();
                dialogPlus.dismiss();
            });
        });

        // 🔹 Delete product when long pressed
        view.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Product")
                    .setMessage("Are you sure you want to delete this product?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        product_list.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Product Deleted!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        });

        checkBox.setOnCheckedChangeListener(null);
        checkBox.setChecked(selectedList.contains(model));

        // when user chexk or unchaked
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                if (!selectedList.contains(model)) selectedList.add(model);

            }
            else {
                selectedList.remove(model);

            }
            listener.onselectionChange(selectedList);
        });


        return view;
    }
}
