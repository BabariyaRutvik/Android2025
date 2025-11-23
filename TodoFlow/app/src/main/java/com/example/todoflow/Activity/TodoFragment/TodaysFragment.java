package com.example.todoflow.Activity.TodoFragment;

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
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TodaysFragment extends Fragment {

    RecyclerView today_recycler;
    LottieAnimationView today_lottie;
    TextView today_no_text;

    AllTaskAdapter TodayAdapter;
    ArrayList<AllTaskModel> TodayList;
    TodoDataHelper TodayHelper;
    private ActionMode actionMode;






    public TodaysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todays, container, false);

        today_recycler = view.findViewById(R.id.today_todo_recyclerview);
        today_lottie = view.findViewById(R.id.empty_todo_lottie);
        today_no_text = view.findViewById(R.id.empty_todo_text_today);
        TodayList = new ArrayList<>();
        TodayHelper = new TodoDataHelper(getContext());

        today_recycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));



        TodayAdapter = new AllTaskAdapter(getContext(), TodayList); // Removed "AllTaskAdapter" type declaration
        today_recycler.setAdapter(TodayAdapter);



        LoadTodayTask();

        // click Listener For Selection task
        TodayAdapter.SetOnSelectionListener(new AllTaskAdapter.OnSelectionListener() {
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

                        boolean Updated = TodayHelper.updateTask(updatedModel);

                        if (Updated){
                            LoadTodayTask();
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
                    TodayAdapter.enableSelectionMode();
                    actionMode.setTitle("1 Selected");
                }
            }
        });

        return view;
    }
    private void LoadTodayTask(){
        // Today ' s Formated Date
        // Use single digit day/month format to match saved tasks (e.g., 9/1/2025 instead of 09/01/2025)
        String todayDate = new SimpleDateFormat("d/M/yyyy", Locale.getDefault())
                .format(new Date());

        TodayList.clear();


        // Fetching All task
        ArrayList<AllTaskModel> allTask = TodayHelper.getAllTask();

        // Now Filtering Today Task
        for (AllTaskModel task : allTask){
            if (task.getDueDate() != null && task.getDueDate().trim().equalsIgnoreCase(todayDate) && task.getIsCompleted() == 0){
                TodayList.add(task);
            }
        }

        // Updating Recycler View
        if (TodayAdapter != null) {
            TodayAdapter.SetTask(TodayList);
        }

        // Now Setting Up Animation
        if (TodayList.isEmpty()){
            today_recycler.setVisibility(View.GONE);
            today_lottie.setVisibility(View.VISIBLE);
            today_no_text.setVisibility(View.VISIBLE);

            FadeIn(today_lottie);
            FadeIn(today_no_text);

        }
        else
        {
            today_recycler.setVisibility(View.VISIBLE);
            today_lottie.setVisibility(View.GONE);
            today_no_text.setVisibility(View.GONE);

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
                ArrayList<Integer> ids = TodayAdapter.getSelectedIds();
                for (int id : ids){
                    TodayHelper.deleteTask(id);
                }
                TodayAdapter.disableSelectionMode();
                actionMode.finish();
                LoadTodayTask();
                ShowDelete(); // Added ShowDelete toast
                mode.finish();
                return true;
            }
            else if (item.getItemId() == R.id.completed_task){
                ArrayList<Integer> ids = TodayAdapter.getSelectedIds();
                for (int id : ids){

                    TodayHelper.markCompleted(id);


                }
                TodayAdapter.disableSelectionMode();
                LoadTodayTask();
                ShowCompletedTask(); // Added ShowCompletedTask()
                mode.finish();
                return true;
            }

            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater =mode.getMenuInflater();
            inflater.inflate(R.menu.delete_task,menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            TodayAdapter.disableSelectionMode();
            actionMode = null;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    };

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
        if (toast != null) {
            toast.show();

        }
    }
}
