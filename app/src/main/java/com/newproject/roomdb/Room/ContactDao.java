package com.newproject.roomdb.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Query("delete from Contact where id=:id")
    void delete(int id);

    @Query("select * from Contact")
    List<Contact> getAllContact();


}
