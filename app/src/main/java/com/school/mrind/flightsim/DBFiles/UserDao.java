package com.school.mrind.flightsim.DBFiles;

import android.arch.persistence.room.*;

import com.school.mrind.flightsim.DBFiles.User;

import java.util.List;

@Dao

public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where classname like :clnum")
    List<User> loadAllByClassnum(String clnum);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Update
    void update(User user);

    @Update
    void updateAll(User... users);
}
