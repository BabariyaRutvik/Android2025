package com.example.creditrack.CredActivity.LoanAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creditrack.CredActivity.AddFragmentDatabase.Loan;
import com.example.creditrack.R;

import java.text.DecimalFormat;
import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<LoanView>
{
    private Context context;
    private List<Loan> loans;

    private OnLoanClickListener listener;

    // now creating a adapter constructor
    public LoanAdapter(Context context, List<Loan> loans, OnLoanClickListener listener){
        this.context = context;
        this.loans = loans;
        this.listener = listener;
    }

    // creating interface for click listener
    public interface OnLoanClickListener{
        void onLoanClick(Loan loan);
    }




    @NonNull
    @Override
    public LoanView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_loan_design, parent, false);
        return new LoanView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanView holder, int position) {
        Loan loan = loans.get(position);
        holder.textPersonName.setText(loan.getPersonName());
        holder.textDate.setText(loan.getDate());

        // formatting amount
        DecimalFormat format = new DecimalFormat("#,###");
        String formattedAmount = format.format(loan.getAmount());
        holder.textAmount.setText("₹ " + formattedAmount);

        // now setting up a a loan type and badges
        holder.textLoanType.setText(loan.getLoanType());

        if (loan.getLoanType().equals("Given")) {
            holder.textLoanType.setBackgroundResource(R.drawable.bg_loan_type);
            holder.textLoanType.setTextColor(context.getResources().getColor(R.color.white));


        }
        else if (loan.getLoanType().equals("Taken")) {

            holder.textLoanType.setBackgroundResource(R.drawable.bg_loan_type_red);
            holder.textLoanType.setTextColor(context.getResources().getColor(R.color.white));
        }


        // Item click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLoanClick(loan);
            }
        });
    }

    @Override
    public int getItemCount() {
        return loans.size();
    }
    // Updating the data
    public void setLoansList(List<Loan> loans){
        this.loans = loans;
        notifyDataSetChanged();
    }

}
class  LoanView extends RecyclerView.ViewHolder{

    ImageView imageUser;
    TextView textPersonName;
    TextView textDate;
    TextView textLoanType;
    TextView textAmount;

    public LoanView(@NonNull View itemView) {
        super(itemView);


        imageUser = itemView.findViewById(R.id.image_user_add);
        textPersonName = itemView.findViewById(R.id.text_person_name);
        textDate = itemView.findViewById(R.id.text_date);
        textLoanType = itemView.findViewById(R.id.text_loan_type);
        textAmount = itemView.findViewById(R.id.text_amount);
    }
}