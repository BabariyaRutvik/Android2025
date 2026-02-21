package com.example.producthub.ProductFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.producthub.Adapter.MyAdapter;
import com.example.producthub.ProductModel.Product;
import com.example.producthub.R;
import com.example.producthub.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class homeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MyAdapter myAdapter;
    private List<Product> productList;
    private FirebaseFirestore firestore;
    private ListenerRegistration listenerRegistration;

    public homeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firestore = FirebaseFirestore.getInstance();
        productList = new ArrayList<>();

        setUpRecyclerView();
        setUpSearchView();
        fetchProducts();

        binding.fabAddProduct.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new AddFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void setUpRecyclerView() {
        myAdapter = new MyAdapter(getContext(), productList);
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvProducts.setAdapter(myAdapter);
    }

    private void setUpSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void fetchProducts() {
        listenerRegistration = firestore.collection("Products")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null) {
                        if (isAdded()) {
                            Toast.makeText(requireContext(), "Error fetching products", Toast.LENGTH_SHORT).show();
                        }
                        return;
                    }

                    if (snapshots != null) {
                        productList.clear();
                        for (QueryDocumentSnapshot doc : snapshots) {
                            Product product = doc.toObject(Product.class);
                            product.setProductId(doc.getId()); // Set the ID from Firestore document
                            productList.add(product);
                        }
                        myAdapter.updateData(productList);
                    }
                });
    }

    private void filter(String text) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(product);
            }
        }
        myAdapter.filterList(filteredList);
    }

    @Override
    public void onDestroyView() {
        if (listenerRegistration != null) {
            listenerRegistration.remove(); // Remove listener to prevent memory leaks
        }
        super.onDestroyView();
        binding = null;
    }
}
