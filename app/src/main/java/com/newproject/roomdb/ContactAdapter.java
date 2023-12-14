package com.newproject.roomdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newproject.roomdb.Room.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    private Context context;
    private List<Contact> contactList;

    private AdapterListener adapterListener;

    public ContactAdapter(Context context, AdapterListener listener) {
        this.context = context;
        contactList = new ArrayList<>();
        this.adapterListener = listener;
    }
    public void addContact(Contact contact){
        contactList.add(contact);
        notifyDataSetChanged();
    }

    public void removeContact(int position){
        contactList.remove(position);
        notifyDataSetChanged();
    }

    public void clearData(){
        contactList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.name.setText(contact.getName());
        holder.number.setText(contact.getNumber());

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.OnUpdate(contact);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.OnDelete(contact.getId(),position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, number;
        private ImageView update, delete;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name =  itemView.findViewById(R.id.tvname);
            number = itemView.findViewById(R.id.tvnumber);
            update =  itemView.findViewById(R.id.update);
            delete = itemView.findViewById(R.id.delete);
        }

    }

}
