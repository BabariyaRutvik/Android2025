package com.example.todoflow.Activity.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoflow.Activity.TodoModel.AllTaskModel;
import com.example.todoflow.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AllTaskAdapter extends RecyclerView.Adapter<MyTask> {

    private Context context;
    private ArrayList<AllTaskModel> task;
    private OnItemClickListener listener;
    private final Set<Integer> selectedIds = new HashSet<>();
    private boolean SelectionMode = false;
    private OnSelectionListener selectionChangeListener;

    public interface OnItemClickListener {
        void onItemClick(AllTaskModel model);
        void onItemLongClick(AllTaskModel model, int position);
    }

    public interface OnSelectionListener {
        void onSelectionChanged(int selectedCount);
        void onItemClicked(AllTaskModel model);
        void onItemLongClicked(AllTaskModel model);
    }

    public void SetOnSelectionListener(OnSelectionListener listener) {
        this.selectionChangeListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // creating Adapter Constructor class
    public AllTaskAdapter(Context context, ArrayList<AllTaskModel> task) {
        this.context = context;
        this.task = task;
    }

    @NonNull
    @Override
    public MyTask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_task_desin, parent, false);
        MyTask tasknew = new MyTask(view);
        return tasknew;
    }

    @Override
    public void onBindViewHolder(@NonNull MyTask holder, int position) {

        AllTaskModel model = task.get(position);
        holder.textPriority.setText(model.getPriority());
        holder.textDescription.setText(model.getDescription());
        holder.textCategory.setText(model.getCategory());
        holder.textDueDate.setText("Due Date " + (model.getDueDate() == null ? "" : model.getDueDate()));

        // changing the color of the priority
        switch (model.getPriority() == null ? "Low" : model.getPriority()) {
            case "High":
                holder.textPriority.setBackgroundColor(0xFFE53935); // Red
                break;
            case "Medium":
                holder.textPriority.setBackgroundColor(0xFFFFA726); // Orange
                break;
            case "Low":
                holder.textPriority.setBackgroundColor(0xFF4CAF50); // Green
                break;
            default:
                holder.textPriority.setBackgroundColor(0xFF4CAF50); // Default to Green
                break;
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (selectionChangeListener != null) {
                    selectionChangeListener.onItemLongClicked(model);
                    toggleSelect(model);
                } else if (listener != null) {
                    listener.onItemLongClick(model, holder.getAdapterPosition());
                }
                return true;
            }
        });

        // Selection Mode UI
        if (selectedIds.contains(model.getId())) {
            holder.image_delete_check.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.parseColor("#E1F5FE"));
        } else {
            holder.image_delete_check.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SelectionMode) {
                    toggleSelect(model);
                } else {
                    // Fix: Call selectionChangeListener.onItemClicked as well
                    if (selectionChangeListener != null) {
                        selectionChangeListener.onItemClicked(model);
                    }
                    if (listener != null) {
                        listener.onItemClick(model);
                    }
                }
            }
        });
    }

    private void toggleSelect(AllTaskModel model) {
        if (selectedIds.contains(model.getId())) {
            selectedIds.remove(model.getId());
        } else {
            selectedIds.add(model.getId());
        }
        
        if (selectedIds.isEmpty()) {
            SelectionMode = false;
        } else {
            SelectionMode = true;
        }

        if (selectionChangeListener != null) {
            selectionChangeListener.onSelectionChanged(selectedIds.size());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return task.size();
    }
    public ArrayList<Integer> getSelectedIds() {
        ArrayList<Integer> selectedIdsList = new ArrayList<>(selectedIds);
        return selectedIdsList;
    }

    public void SetTask(ArrayList<AllTaskModel> task) {
        this.task = task;
        notifyDataSetChanged();
    }
    public boolean isSelectionMode() {
        return SelectionMode;
    }
    public void enableSelectionMode() {
        SelectionMode = true;
        notifyDataSetChanged();
    }
    public void disableSelectionMode() {
        SelectionMode = false;
        selectedIds.clear();
        notifyDataSetChanged();
    }

}

class MyTask extends RecyclerView.ViewHolder {

    TextView textPriority, textDescription, textCategory, textDueDate;
    ImageView image_delete_check;

    public MyTask(@NonNull View itemView) {
        super(itemView);
        textPriority = itemView.findViewById(R.id.text_priority_);
        textDescription = itemView.findViewById(R.id.text_description);
        textCategory = itemView.findViewById(R.id.text_category);
        textDueDate = itemView.findViewById(R.id.text_due_date);
        image_delete_check = itemView.findViewById(R.id.image_check_delete);
    }
}
