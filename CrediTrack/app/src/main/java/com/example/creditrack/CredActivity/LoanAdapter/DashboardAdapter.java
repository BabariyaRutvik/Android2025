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

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<Dashboardview> {

    private Context context; // Get context;
    private List<Loan> loanList;
    OnRecentClickListener onRecentClickListener;
    public interface OnRecentClickListener {
        void onRecentClick(Loan loan);
    }
    // now creating adapter class
    public DashboardAdapter(Context context, List<Loan> loanList, OnRecentClickListener onRecentClickListener) {
        this.context = context;
        this.loanList = loanList;
        this.onRecentClickListener = onRecentClickListener;
    }

    @NonNull
    @Override
    public Dashboardview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_recents,parent,false);
        Dashboardview dashboard = new Dashboardview(view);
        return dashboard;
    }

    @Override
    public void onBindViewHolder(@NonNull Dashboardview holder, int position) {

        holder.textDashPName.setText(loanList.get(position).getPersonName());
        holder.textDashDate.setText(loanList.get(position).getDate());
        holder.textDashLoanType.setText(loanList.get(position).getLoanType());
        holder.textDashAmount.setText("₹ " + loanList.get(position).getAmount());


        if (loanList.get(position).getLoanType().equalsIgnoreCase("Given")) {

            holder.textDashAmount.setTextColor(context.getResources().getColor(R.color.teal_500));

        }
        else {
            holder.textDashAmount.setTextColor(context.getResources().getColor(R.color.red));

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loanList.get(position);
                onRecentClickListener.onRecentClick(loanList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
       return loanList != null ? loanList.size() : 0;
    }

    public void setLoansList(List<Loan> loanList) {
        this.loanList = loanList;
        notifyDataSetChanged();
    }

}
class Dashboardview extends RecyclerView.ViewHolder {

TextView textDashPName,textDashDate,textDashLoanType,textDashAmount;
    public Dashboardview(@NonNull View itemView) {
        super(itemView);


        textDashPName = itemView.findViewById(R.id.text_dash_p_name);
        textDashDate = itemView.findViewById(R.id.text_dash_date);
        textDashLoanType = itemView.findViewById(R.id.text_dash_loan_type);
        textDashAmount = itemView.findViewById(R.id.text_dash_amount);
    }
}
