package com.example.creditrack.CredActivity.LoanAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creditrack.CredActivity.AddFragmentDatabase.Loan;
import com.example.creditrack.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class PersonalListAdapter extends RecyclerView.Adapter<personalView>
{

    private Context context;
    private List<Loan> personalList;

    private OnPersonalListListener listListener;

    @NonNull
    @Override
    public personalView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.person_design, parent, false);
        personalView personalview = new personalView(view);
        return personalview;
    }

    @Override
    public void onBindViewHolder(@NonNull personalView holder, int position) {
           Loan loan = personalList.get(position);



           // setting data into the item
        holder.textPersonName.setText(loan.getPersonName());
        holder.textLoanDate.setText(loan.getDate());
        holder.textAmount.setText("₹ " + loan.getAmount());
        holder.chipLoanType.setText(loan.getLoanType());


        // now chip the chip background color based on type
        if (loan.getLoanType().equalsIgnoreCase("Given")){
            holder.chipLoanType.setChipBackgroundColorResource(R.color.light_green);
            holder.textAmount.setTextColor(context.getResources().getColor(R.color.teal_500));

        }
        else {
            holder.chipLoanType.setChipBackgroundColorResource(R.color.light_red);
            holder.textAmount.setTextColor(context.getResources().getColor(R.color.red));

        }

        // click event
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listListener != null){
                    listListener.onPersonalClick(loan);
                }
                else {
                    System.out.println("list listener is null");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return personalList == null ? 0 : personalList.size();
    }

    public void setLoanList(List<Loan> list){
        this.personalList = list;
        notifyDataSetChanged();

    }


    // click interface for the person
    public interface OnPersonalListListener{
        void onPersonalClick(Loan loan);
    }

    // creating adapter constructor
    public PersonalListAdapter(Context context, List<Loan> personalList, OnPersonalListListener listListener) {
        this.context = context;
        this.personalList = personalList;
        this.listListener = listListener;

    }



}
class personalView extends RecyclerView.ViewHolder{

    TextView textPersonName, textLoanDate, textAmount;

    Chip chipLoanType;

    public personalView(@NonNull View itemView) {
        super(itemView);



        textPersonName = itemView.findViewById(R.id.textPersonName);
        textLoanDate = itemView.findViewById(R.id.textLoanDate);
        textAmount = itemView.findViewById(R.id.textAmount);
        chipLoanType = itemView.findViewById(R.id.chipLoanType);
    }
}
