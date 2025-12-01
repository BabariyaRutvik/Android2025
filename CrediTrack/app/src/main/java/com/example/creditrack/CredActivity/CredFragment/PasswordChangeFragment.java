package com.example.creditrack.CredActivity.CredFragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.creditrack.CredActivity.Database.LoanManager;
import com.example.creditrack.CredActivity.Database.LoginHelper;
import com.example.creditrack.R;
import com.example.creditrack.databinding.FragmentPasswordChangeBinding;

public class PasswordChangeFragment extends Fragment {

    private FragmentPasswordChangeBinding binding;
    LoanManager loanManager;
    LoginHelper loginHelper;
    NavController navController;



    public PasswordChangeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentPasswordChangeBinding.inflate(inflater, container, false);

        // initializing loan manager
        loanManager = new LoanManager(requireContext());
        loginHelper = new LoginHelper(requireContext());


        // initializing nav controller
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_loan);



        // save button functionality
        binding.btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePassword();
            }
        });

        return binding.getRoot();
    }
    private void SavePassword(){
        String currentPassword = binding.edtCurrentPassword.getText().toString();
        String newPassword = binding.edtNewPassword.getText().toString();
        String confirmPassword = binding.edtConformPassword.getText().toString();


        // now making the validating the fields
        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(requireContext(), "All Fields Required", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(requireContext(), "New Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = loanManager.getUser();
        if (userId != -1 && userId != 0) {
            String savedPassword = loginHelper.getPassword(userId);
            
            if (savedPassword.equals(currentPassword)) {
                boolean isUpdated = loginHelper.updatePassword(userId, newPassword);
                if (isUpdated) {
                    Toast.makeText(requireContext(), "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    navController.navigateUp();
                } else {
                    Toast.makeText(requireContext(), "Failed to Update Password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "Current Password is Incorrect", Toast.LENGTH_SHORT).show();
            }
        } else {
             Toast.makeText(requireContext(), "User Not Logged In", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}