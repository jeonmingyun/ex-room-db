package com.min.ex_room_db

import android.app.Application
import androidx.room.Room
import com.min.ex_room_db.db.AppDatabase

class App : Application() {
    // AppDatabase 인스턴스를 저장할 변수
    // lazy를 사용해 실제로 접근할 때 단 한 번만 초기
    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

}