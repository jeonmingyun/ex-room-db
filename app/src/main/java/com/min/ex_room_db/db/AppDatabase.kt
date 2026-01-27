package com.min.ex_room_db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.min.ex_room_db.UserEntity
import com.min.ex_room_db.UserDao

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}