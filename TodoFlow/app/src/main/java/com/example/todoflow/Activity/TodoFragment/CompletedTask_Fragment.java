package com.example.todoflow.Activity.TodoFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.todoflow.Activity.Adapter.AllTaskAdapter;
import com.example.todoflow.Activity.SqliteDatabseDBHelper.TodoDataHelper;
import com.example.todoflow.Activity.TodoModel.AllTaskModel;
import com.example.todoflow.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;


public class CompletedTask_Fragment extends Fragment {

    RecyclerView CompletedTask_RecyclerView;
    TextView text_no_completed_tasks;
    LottieAnimationView lottieAnimationView;
    TodoDataHelper completeDatahelper;
    AllTaskAdapter complete_adapter;
    ArrayList<AllTaskModel> complete_list =  new ArrayList<>();




    public CompletedTask_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed_task_, container, false);

        CompletedTask_RecyclerView = view.findViewById(R.id.recycler_completed);
        text_no_completed_tasks = view.findViewById(R.id.text_no_completed);
        lottieAnimationView = view.findViewById(R.id.empty_todo_lottie);
        completeDatahelper = new TodoDataHelper(getContext());


        CompletedTask_RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        complete_adapter = new AllTaskAdapter(getContext(), complete_list);
        CompletedTask_RecyclerView.setAdapter(complete_adapter);



        LoadCompletedTask();

        complete_adapter.SetOnSelectionListener(new AllTaskAdapter.OnSelectionListener() {
            @Override
            public void onSelectionChanged(int selectedCount) {

            }

            @Override
            public void onItemClicked(AllTaskModel model) {
                    ShowBottomSheet(model);
            }

            @Override
            public void onItemLongClicked(AllTaskModel model) {

            }
        });




        return view;
    }
    private void LoadCompletedTask(){
        complete_list.clear();
        complete_list.addAll(completeDatahelper.getCompletedTask());
        complete_adapter.SetTask(complete_list);
        if (complete_list.isEmpty()){
            text_no_completed_tasks.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
            CompletedTask_RecyclerView.setVisibility(View.GONE);
            FadeIn(lottieAnimationView);
            FadeIn(text_no_completed_tasks);
        }
        else {
            text_no_completed_tasks.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.GONE);
            CompletedTask_RecyclerView.setVisibility(View.VISIBLE);
        }


    }

    private void FadeIn(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(300);
        view.startAnimation(animation);
    }

    // Show Bottom Sheet
    private void ShowBottomSheet(AllTaskModel model){
        BottomSheetDialog dialog = new BottomSheetDialog(requireContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_complete_action, null);


        TextView edit_task = view.findViewById(R.id.text_edit);
        TextView delete_task = view.findViewById(R.id.text_delete_task);
        TextView mark_incomplete = view.findViewById(R.id.text_uncomplete);

        // For Edit the task
        edit_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                openEditorDialog(model);
            }
        });
        // For Delete the task
        delete_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                completeDatahelper.deleteTask(model.getId());
                showDeleteToast();
                LoadCompletedTask();
                dialog.dismiss();

            }
        });

        // Undo the task
        mark_incomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                model.setIsCompleted(0);
                completeDatahelper.updateTask(model);
                LoadCompletedTask();
                showUpdateToast("Moved Back to all task");
                dialog.dismiss();


            }
        });
        dialog.setContentView(view);
        dialog.show();
    }
    private void openEditorDialog(AllTaskModel model) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.update_todo_task);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextInputEditText edtDescription = dialog.findViewById(R.id.edit_update_description);
        TextInputEditText edtCategory = dialog.findViewById(R.id.edit_update_category);
        TextInputEditText edtDate = dialog.findViewById(R.id.edit_update_due_date);
        ChipGroup chipGroup = dialog.findViewById(R.id.chip_group_update_priority);
        AppCompatButton btnUpdate = dialog.findViewById(R.id.button_update_task);
        AppCompatButton btnCancel = dialog.findViewById(R.id.button_update_cancel);

        // Setting up Existing Data
        edtDescription.setText(model.getDescription());
        edtCategory.setText(model.getCategory());
        edtDate.setText(model.getDueDate());

        // Setting up the Priority
        if (model.getPriority().equals("High")){
            chipGroup.check(R.id.chip_update_high);
        }
        else if (model.getPriority().equals("Medium")) {
            chipGroup.check(R.id.chip_update_medium);


        }
        else {
            chipGroup.check(R.id.chip_update_low);
        }


        // DatePicker
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                        edtDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });
        // Update Task
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = edtDescription.getText().toString();
                String category = edtCategory.getText().toString();
                String dueDate = edtDate.getText().toString();


                String priority = "Low";
                if (chipGroup.getCheckedChipId() == R.id.chip_update_high){
                    priority = "High";
                }
                else if (chipGroup.getCheckedChipId() == R.id.chip_update_medium){
                    priority = "Medium";
                }
                else {
                    priority = "Low";

                }
                
                // Fix: Use all fields to update the model
                AllTaskModel updatedModel = new AllTaskModel(model.getId(),
                        description,
                        category,
                        priority,
                        dueDate,
                        model.getCreatedDate(),
                        model.getIsCompleted());

                boolean Updated = completeDatahelper.updateTask(updatedModel);
                if (Updated){
                    showUpdateToast("Updated Successfully");
                    LoadCompletedTask();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(getContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
                }




            }
        });
        
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        dialog.show();





    }
    private void showUpdateToast(String message){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.update_todo_toast, null);
        TextView text_toast = view.findViewById(R.id.text_update_toast);

        Toast toast = new Toast(requireContext());
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);

        text_toast.setText(message);
        toast.show();

    }
    private void showDeleteToast() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.delete_toast, null);
        TextView text_toast = view.findViewById(R.id.text_delete_toast);

        Toast toast = new Toast(requireContext());
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.show();
    }
}