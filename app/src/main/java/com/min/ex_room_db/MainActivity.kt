package com.min.ex_room_db

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.min.ex_room_db.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // DB 초기화
        db = (application as App).database

        // --- 데이터베이스 작업은 코루틴 스코프 내에서 수행 ---
        lifecycleScope.launch {
            // IO 스레드에서 데이터베이스 작업 실행
            withContext(Dispatchers.IO) {
                val userDao = db.userDao()

                // 예시 : Insert
                userDao.insertAll(
                    UserEntity(firstName = "길동", lastName = "홍"),
                    UserEntity(firstName = "철수", lastName = "김")
                )

                // 예시: Select
                val users: List<UserEntity> = userDao.getAll()
                Log.d("MainActivity", "Users: $users")
            }
        }
    }
}