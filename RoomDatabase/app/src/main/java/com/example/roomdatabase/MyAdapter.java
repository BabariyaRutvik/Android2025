package com.example.roomdatabase;

import android.content.Context;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.RoomDatabase.StudentViewModel;
import com.example.roomdatabase.RoomDatabase.Students;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyView> {

    private Context context;
    private ArrayList<Students> studentsList;

    private StudentViewModel studentViewModel;

    // For multi-select
    private ArrayList<Students> selectedStudents = new ArrayList<>();
    private boolean isActionMode = false;
    private ActionMode actionMode;

    // Constructor
    public MyAdapter(Context context, ArrayList<Students> studentsList) {
        this.context = context;
        this.studentsList = studentsList;

        if (context instanceof ViewModelStoreOwner) {
            this.studentViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(StudentViewModel.class);
        }
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.add_design, null);
        return new MyView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyView holder, int position) {

        Students student = studentsList.get(position);

        holder.text_studentName.setText(student.getName());
        holder.text_student_roll_no.setText(student.getRollNo());
        holder.text_student_age.setText(student.getAge());
        holder.text_student_course.setText(student.getCourse());

        // Set selection state
        if (selectedStudents.contains(student)) {
            holder.img_check.setVisibility(View.VISIBLE);
            holder.cardView.setCardBackgroundColor(Color.LTGRAY);
        } else {
            holder.img_check.setVisibility(View.GONE);
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }

        // Normal click → Update
        // Selection mode click → Toggle selection
        holder.itemView.setOnClickListener(v -> {
            if (isActionMode) {
                toggleSelection(student, holder);
            } else {
                showUpdateDialog(student);
            }
        });

        // Long press → Activate selection mode
        holder.itemView.setOnLongClickListener(v -> {
            if (!isActionMode) {
                isActionMode = true;
                if (context instanceof AppCompatActivity) {
                    ((AppCompatActivity) context).startActionMode(actionModeCallback);
                }
                toggleSelection(student, holder);
            }
            return true;
        });
    }

    // Selection toggle
    private void toggleSelection(Students student, MyView holder) {

        if (selectedStudents.contains(student)) {
            selectedStudents.remove(student);
        } else {
            selectedStudents.add(student);
        }

        notifyItemChanged(holder.getAdapterPosition());

        if (selectedStudents.size() == 0) {
            if (actionMode != null) {
                actionMode.finish();
            }
        } else {
            if (actionMode != null) {
                actionMode.setTitle(selectedStudents.size() + " Selected");
            }
        }
    }

    // Update Dialog
    private void showUpdateDialog(Students currentStudent) {

        DialogPlus dialogPlus = DialogPlus.newDialog(context)
                .setContentHolder(new ViewHolder(R.layout.update_data))
                .setExpanded(true, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();

        View view = dialogPlus.getHolderView();

        TextInputEditText edtName = view.findViewById(R.id.edtname_update);
        TextInputEditText edtRollNo = view.findViewById(R.id.edtRoll_no_update);
        TextInputEditText edtAge = view.findViewById(R.id.edtAge_update);
        TextInputEditText edtCourse = view.findViewById(R.id.edt_course_update);
        AppCompatButton btnUpdate = view.findViewById(R.id.btn_update_data);

        edtName.setText(currentStudent.getName());
        edtRollNo.setText(currentStudent.getRollNo());
        edtAge.setText(currentStudent.getAge());
        edtCourse.setText(currentStudent.getCourse());

        btnUpdate.setOnClickListener(v -> {

            Students updatedStudent = new Students(
                    edtName.getText().toString(),
                    edtRollNo.getText().toString(),
                    edtAge.getText().toString(),
                    edtCourse.getText().toString()
            );

            updatedStudent.setId(currentStudent.getId()); // KEEP ID

            Toast.makeText(context, "Student Data Updated", Toast.LENGTH_SHORT).show();

            studentViewModel.UpdateStudent(updatedStudent);

            dialogPlus.dismiss();
        });

        dialogPlus.show();
    }

    // ActionMode for delete
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            actionMode = mode;
            mode.getMenuInflater().inflate(R.menu.delete, menu);
            mode.setTitle("0 Selected");
            mode.setSubtitle("Tap items to select");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            if (item.getItemId() == R.id.delete_item) {

                for (Students s : selectedStudents) {
                    Toast.makeText(context, "Student Data Deleted", Toast.LENGTH_SHORT).show();
                    studentViewModel.DeleteStudent(s);
                }

                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isActionMode = false;
            selectedStudents.clear();
            notifyDataSetChanged();
        }
    };

    @Override
    public int getItemCount() {
        return studentsList.size();
    }
}

// ========================== VIEW HOLDER ==========================
class MyView extends RecyclerView.ViewHolder {

    TextView text_studentName, text_student_roll_no, text_student_age, text_student_course;
    ImageView img_check;
    MaterialCardView cardView;

    public MyView(@NonNull View itemView) {
        super(itemView);

        text_studentName = itemView.findViewById(R.id.text_student_name);
        text_student_roll_no = itemView.findViewById(R.id.text_student_roll_no);
        text_student_age = itemView.findViewById(R.id.text_age);
        text_student_course = itemView.findViewById(R.id.text_course);

        img_check = itemView.findViewById(R.id.image_delete_muliple_item); // checkbox icon

        // The itemView is a MaterialCardView based on add_design.xml
        if (itemView instanceof MaterialCardView) {
            cardView = (MaterialCardView) itemView;
        }
    }
}
