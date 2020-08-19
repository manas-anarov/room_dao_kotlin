package com.example.roomdaokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.room.Room
import com.example.roomdao.database.AppDatabase
import com.example.roomdao.database.ProductFromDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        doAsync {

            val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "database-name"
            ).build()

            db.productDao().insertAll(ProductFromDatabase(null, "Socks - one dozen", 1.99))
            val products = db.productDao().getAll()

            uiThread {

                d("daniel", "products size? ${products.size} ${products[0].title}")

            }
        }


    }
}