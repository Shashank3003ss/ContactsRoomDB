package com.newproject.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.newproject.roomdb.Room.Contact;
import com.newproject.roomdb.Room.ContactDao;
import com.newproject.roomdb.Room.ContactDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener{

    EditText etname, etno;
    Button btnAdd;
    RecyclerView recycler;

    private ContactDatabase contactDatabase;
    private ContactDao contactDao;

    private ContactAdapter contactAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactDatabase = ContactDatabase.getInstance(this);
        contactDao = contactDatabase.getDao();

        etname = findViewById(R.id.etname);
        etno = findViewById(R.id.etno);
        btnAdd = findViewById(R.id.btnAdd);
        recycler = findViewById(R.id.contactRecycler);

        contactAdapter = new ContactAdapter(this,this);
        recycler.setAdapter(contactAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etname.getText().toString();
                String number = etno.getText().toString();

                Contact contact = new Contact(0, name, number);
                contactAdapter.addContact(contact);

                contactDao.insert(contact);

                etname.setText("");
                etno.setText("");
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchData(){
        contactAdapter.clearData();
       List<Contact> contactList = contactDao.getAllContact();
       for(int i=0;i<contactList.size();i++){
           Contact contact = contactList.get(i);
           contactAdapter.addContact(contact);
       }

    }

    @Override
    public void OnUpdate(Contact contact) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("model", contact);
        startActivity(intent);
    }

    @Override
    public void OnDelete(int id, int pos) {
        contactDao.delete(id);
        contactAdapter.removeContact(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}