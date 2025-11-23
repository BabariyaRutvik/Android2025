package com.example.todoflow.Activity.TodoFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.todoflow.Activity.Adapter.AllTaskAdapter;
import com.example.todoflow.Activity.SqliteDatabseDBHelper.TodoDataHelper;
import com.example.todoflow.Activity.TodoModel.AllTaskModel;
import com.example.todoflow.R;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class All_Task_Fragment extends Fragment {


    RecyclerView all_task_recyclerview;
    LottieAnimationView empty_lottie;
    TextView empty_text;

    AllTaskAdapter allTaskAdapter;
    private ArrayList<AllTaskModel> taskList = new ArrayList<>();
    private TodoDataHelper todoDataHelper;
    ExtendedFloatingActionButton floatingActionButtonAdd;
    private ActionMode actionMode;


    public All_Task_Fragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all__task_, container, false);

        all_task_recyclerview = view.findViewById(R.id.all_task_recyclerView);
        floatingActionButtonAdd = view.findViewById(R.id.floatingAddTask);
        empty_lottie = view.findViewById(R.id.empty_todo_lottie);
        empty_text = view.findViewById(R.id.empty_todo_text_today);

        all_task_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        todoDataHelper = new TodoDataHelper(getContext());
        taskList = todoDataHelper.getAllTask();


        allTaskAdapter = new AllTaskAdapter(getContext(), taskList);
        all_task_recyclerview.setAdapter(allTaskAdapter);


        // Loading Data From Database
        LoadingTaskFromDatabase();

        // click Listener For Selection task
        allTaskAdapter.SetOnSelectionListener(new AllTaskAdapter.OnSelectionListener() {
            @Override
            public void onSelectionChanged(int selectedCount) {
                if (actionMode != null){
                    actionMode.setTitle(selectedCount + " Selected");
                    if (selectedCount == 0){
                        actionMode.finish();
                    }

                }
            }

            @Override
            public void onItemClicked(AllTaskModel model) {

                // for Update the data
                DialogPlus dialogPlus = DialogPlus.newDialog(getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_todo_task))
                        .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .create();

                View view = dialogPlus.getHolderView();

                TextInputEditText edtDescription = view.findViewById(R.id.edit_update_description);
                TextInputEditText edtCategory = view.findViewById(R.id.edit_update_category);
                TextInputEditText edt_Due_Date = view.findViewById(R.id.edit_update_due_date);

                ChipGroup chipGroup = view.findViewById(R.id.chip_group_update_priority);


                AppCompatButton btnUpdateTodo = view.findViewById(R.id.button_update_task);
                AppCompatButton btnDeleteTodo = view.findViewById(R.id.button_update_cancel);

                // getting data from Model class

                edtDescription.setText(model.getDescription());
                edtCategory.setText(model.getCategory());
                edt_Due_Date.setText(model.getDueDate());


                //Pre Selected Priority
                switch (model.getPriority()) {
                    case "High":
                        chipGroup.check(R.id.chip_update_high);
                        break;
                    case "Medium":
                        chipGroup.check(R.id.chip_update_medium);
                        break;
                    case "Low":
                        chipGroup.check(R.id.chip_update_low);
                        break;
                }

                // Date Picker On Update Dialog
                edt_Due_Date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);


                        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                                edt_Due_Date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day).show();


                    }
                });
                btnUpdateTodo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String Desc = edtDescription.getText().toString();
                        String Category = edtCategory.getText().toString();
                        String Date = edt_Due_Date.getText().toString();

                        String Priority = "Low";
                        int id = chipGroup.getCheckedChipId();


                        if (id == R.id.chip_update_high) {
                            Priority = "High";

                        } else if (id == R.id.chip_update_medium) {
                            Priority = "Medium";

                        }

                        if (Desc.isEmpty() || Category.isEmpty() || Date.isEmpty()) {
                            edtDescription.setError("Required");
                            edtCategory.setError("Required");
                            edt_Due_Date.setError("Required");
                            return;
                        }
                        
                         // Now Updating the data
                        AllTaskModel updatedModel = new AllTaskModel(model.getId(),
                                Desc,
                                Category,
                                Priority,
                                Date,
                                model.getCreatedDate(),
                                model.getIsCompleted());
                                
                        boolean Updated = todoDataHelper.updateTask(updatedModel);

                        if (Updated){
                            LoadingTaskFromDatabase();
                            ShowUpdate();
                            dialogPlus.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Failed to update task", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                
                btnDeleteTodo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.dismiss();
                    }
                });
                
                dialogPlus.show();
            }

            @Override
            public void onItemLongClicked(AllTaskModel model) {

                if (actionMode == null){
                    actionMode = ((AppCompatActivity)requireActivity()).startSupportActionMode(actionModeCallback);
                    allTaskAdapter.enableSelectionMode();
                    actionMode.setTitle("1 Selected");
                }
            }
        });

        // Open a Dialog for add the task
        floatingActionButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowView();
            }
        });


        return view;
    }


    private void LoadingTaskFromDatabase() {
        taskList.clear();
        // Fetch all tasks
        ArrayList<AllTaskModel> all = todoDataHelper.getAllTask();
        // Filter out completed tasks
        for (AllTaskModel t : all) {
            if (t.getIsCompleted() == 0) {
                taskList.add(t);
            }
        }
        allTaskAdapter.SetTask(taskList);

        // Animation Logic
        if (taskList.isEmpty()){
            all_task_recyclerview.setVisibility(View.GONE);
            empty_lottie.setVisibility(View.VISIBLE);
            empty_text.setVisibility(View.VISIBLE);
            FadeIn(empty_lottie);
            FadeIn(empty_text);
        } else {
            all_task_recyclerview.setVisibility(View.VISIBLE);
            empty_lottie.setVisibility(View.GONE);
            empty_text.setVisibility(View.GONE);
        }
    }

    private void FadeIn(View view) {
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(300);
        view.startAnimation(animation);
    }

    // For Delete the task Action Mode
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.delete_task){
                ArrayList<Integer> ids = allTaskAdapter.getSelectedIds();
                for (int id : ids){
                    todoDataHelper.deleteTask(id);
                }
                allTaskAdapter.disableSelectionMode();
                actionMode.finish();
                LoadingTaskFromDatabase();
                ShowDelete(); // Added ShowDelete toast
                mode.finish();
                return true;
            }
            else if (item.getItemId() == R.id.completed_task){
                ArrayList<Integer> ids = allTaskAdapter.getSelectedIds();
                for (int id : ids){

                    todoDataHelper.markCompleted(id);


            }
                allTaskAdapter.disableSelectionMode();
                LoadingTaskFromDatabase();
                ShowCompletedTask();
                mode.finish();
                return true;
            }

            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater =mode.getMenuInflater();
            inflater.inflate(R.menu.delete_task,menu);
            floatingActionButtonAdd.setVisibility(View.GONE);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
                allTaskAdapter.disableSelectionMode();
                floatingActionButtonAdd.setVisibility(View.VISIBLE);
                actionMode = null;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    };

    private void ShowView() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_todo_task);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextInputEditText edtDescription = dialog.findViewById(R.id.edit_text_add_todo_des);
        TextInputEditText edtCategory = dialog.findViewById(R.id.edit_text_add_todo_category);
        TextInputEditText edtDate = dialog.findViewById(R.id.edit_text_add_todo_due_date);

        ChipGroup chipGroup = dialog.findViewById(R.id.chip_group_add_todo_priority);

        AppCompatButton btnAddTodo = dialog.findViewById(R.id.button_add_todo);

        // Date Picker
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                // Date Picker
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        edtDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }

        });
        // Now inserting data
        btnAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Desc = edtDescription.getText().toString();
                String Category = edtCategory.getText().toString();
                String Date = edtDate.getText().toString();

                String Priority = "Low";
                int id = chipGroup.getCheckedChipId();

                if (id == R.id.chip_high) {
                    Priority = "High";
                } else if (id == R.id.chip_medium) {
                    Priority = "Medium";
                } else if (id == R.id.chip_low) {
                    Priority = "Low";
                }

                if (Desc.isEmpty() || Category.isEmpty() || Date.isEmpty()) {
                    edtDescription.setError("Required");
                    edtCategory.setError("Required");
                    edtDate.setError("Required");
                    return; // Added return to stop execution if validation fails
                }
                String createdDate =
                        new SimpleDateFormat("yyyy-MM-dd"
                        , Locale.getDefault())
                                .format(System.currentTimeMillis());



                // now data are inserting
                AllTaskModel model
                        =new AllTaskModel(0,
                        Desc,
                        Category,
                        Priority, // Fixed: Priority comes before Date in constructor
                        Date,     // Fixed: Date comes after Priority in constructor
                        createdDate,
                        0);



                boolean isInserted = todoDataHelper.insertTask(model);

                if (isInserted) {
                    ShowToast(); // Added ShowToast call
                    LoadingTaskFromDatabase();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Failed to add task", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }



            }
        });

        dialog.show();
    }
    private void ShowToast(){

        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View layout = inflater.inflate(R.layout.custom_toast_add_todo, null);

        android.widget.TextView textView = layout.findViewById(R.id.text_toast_cu_add);


        Toast toast = new Toast(requireContext());

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,200);
        toast.show();


    }
    private void ShowDelete(){
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View layout = inflater.inflate(R.layout.delete_toast, null);

        android.widget.TextView textView = layout.findViewById(R.id.text_delete_toast);


        Toast toast = new Toast(requireContext());

        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,200);
        toast.show();
    }
    private void ShowUpdate(){
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View layout = inflater.inflate(R.layout.update_todo_toast, null);

        android.widget.TextView textView = layout.findViewById(R.id.text_update_toast);

        Toast toast = new Toast(requireContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,200);
        toast.show();
    }
    private void ShowCompletedTask() {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View layout = inflater.inflate(R.layout.toast_completed, null);

        android.widget.TextView textView = layout.findViewById(R.id.text_complete_toast);
        Toast toast = new Toast(requireContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 200);
        toast.show();
    }
}
