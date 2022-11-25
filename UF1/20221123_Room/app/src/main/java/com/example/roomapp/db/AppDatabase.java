package com.example.roomapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomapp.db.daos.UserDao;
import com.example.roomapp.db.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
