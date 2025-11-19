package com.example.contactapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView contact_recyclerview;
    ArrayList<ContactModel> contact_list;
    FloatingActionButton add_contact_btn;
    ContactAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contact_recyclerview = findViewById(R.id.recyclerview_contacts);
        add_contact_btn = findViewById(R.id.add_contacts_btn);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contact_list = new ArrayList<>();
        contact_recyclerview.setLayoutManager(new LinearLayoutManager(this));

        // Add dummy contacts
        contact_list.add(new ContactModel(R.drawable.img_1, "Rutvik Babariya", "6351202084"));
        contact_list.add(new ContactModel(R.drawable.img_2, "Dhaval Babariya", "6351897865"));
        contact_list.add(new ContactModel(R.drawable.img_3, "Jayraj Babariya", "7654123456"));
        contact_list.add(new ContactModel(R.drawable.img_4, "Bhavin Babariya", "7654321098"));
        contact_list.add(new ContactModel(R.drawable.img_5, "Narendra Rathod", "63555053750"));
        contact_list.add(new ContactModel(R.drawable.img_6, "Ronak Dholakiya", "6543120987"));
        contact_list.add(new ContactModel(R.drawable.img_7, "Ruturaj Jadeja", "876431290"));
        contact_list.add(new ContactModel(R.drawable.img_8, "Shah Dhairya", "8976564321"));
        contact_list.add(new ContactModel(R.drawable.img_9, "Raju Karmta", "7654345678"));
        contact_list.add(new ContactModel(R.drawable.img_10, "Monak Pethani", "7865432109"));
        contact_list.add(new ContactModel(R.drawable.img_11, "Jenti Kordiya", "6543210989"));
        contact_list.add(new ContactModel(R.drawable.img_12, "Pratik", "6754312098"));
        contact_list.add(new ContactModel(R.drawable.img_13, "Parth Soni", "7865456788"));
        contact_list.add(new ContactModel(R.drawable.img_14, "Bhavesh", "7854398765"));
        contact_list.add(new ContactModel(R.drawable.img_15, "Raj", "7678654510"));
        contact_list.add(new ContactModel(R.drawable.img_16, "Ravi", "7657890981"));
        contact_list.add(new ContactModel(R.drawable.img_17, "Bhavin Dangar", "7766554433"));
        contact_list.add(new ContactModel(R.drawable.img_18, "Parth", "7861209867"));
        contact_list.add(new ContactModel(R.drawable.img_19, "Jaydip", "8967541209"));
        contact_list.add(new ContactModel(R.drawable.img_20, "Hardik", "6712098981"));

        adapter = new ContactAdapter(this, contact_list);
        contact_recyclerview.setAdapter(adapter);

        // Add Contact button
        add_contact_btn.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Add_Contact_Activity.class);
            startActivityForResult(i, 1);
        });

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                contact_list.remove(position);
                adapter.notifyItemRemoved(position);
            }
        }).attachToRecyclerView(contact_recyclerview);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String number = data.getStringExtra("number");
            contact_list.add(new ContactModel(R.drawable.img_1, name, number));
            adapter.notifyItemInserted(contact_list.size() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_select, menu);

        MenuItem searchItem = menu.findItem(R.id.search_contact);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.select_item) {
            ArrayList<ContactModel> selectedContacts = adapter.getSelectedContacts();

            if (selectedContacts.isEmpty()) {
                Toast.makeText(this, "You have to select at least one contact", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getApplicationContext(), Contact_ListActivity.class);
                intent.putParcelableArrayListExtra("select_list", selectedContacts);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void filter(String text) {
        ArrayList<ContactModel> filteredList = new ArrayList<>();

        for (ContactModel item : contact_list) {
            if (item.getContactName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Contact Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.filterContact(filteredList);
        }
    }
}
