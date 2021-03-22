package com.swapnil.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.swapnil.model.MainData;

import java.util.List;

@Dao
public interface MainDao {
    //Insert query
    @Insert
    void insert(MainData mainData);

    //Delete query
    @Delete
    void delete(MainData mainData);

    //Delete all query
    @Delete
    void reset(List<MainData> mainData);

    //Update query
    @Query("UPDATE MainData SET text = :sText ,last_name = :last_name ,email = :email WHERE ID = :sID")
    void update(int sID, String sText, String last_name, String email);

    //Get all data query
    @Query("SELECT * FROM MainData")
    List<MainData> getAll();
}
