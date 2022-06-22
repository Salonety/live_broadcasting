package com.example.live_broadcasting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class broadcast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        val game=intent.getStringExtra("game")

    }
}