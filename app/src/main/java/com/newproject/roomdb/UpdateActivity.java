package com.newproject.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.newproject.roomdb.Room.Contact;
import com.newproject.roomdb.Room.ContactDao;
import com.newproject.roomdb.Room.ContactDatabase;

public class UpdateActivity extends AppCompatActivity {

    private EditText etname, etno;
    private Button btnupdate;

    private Contact contact;

    private ContactDatabase contactDatabase;
    private ContactDao contactDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        contactDatabase = ContactDatabase.getInstance(this);
        contactDao = contactDatabase.getDao();

        etname = findViewById(R.id.etname);
        etno = findViewById(R.id.etno);
        btnupdate = findViewById(R.id.btnupdate);

        contact = (Contact) getIntent().getSerializableExtra("model");

        etname.setText(contact.getName());
        etno.setText(contact.getNumber());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contactModel = new Contact(contact.getId(), etname.getText().toString(), etno.getText().toString());
                contactDao.update(contactModel);
                finish();
            }
        });

    }
}