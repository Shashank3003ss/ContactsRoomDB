package com.newproject.roomdb;

import com.newproject.roomdb.Room.Contact;

public interface AdapterListener {
    void OnUpdate(Contact contact);
    void OnDelete(int id, int pos);

}
