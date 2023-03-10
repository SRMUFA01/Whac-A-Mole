package com.example.whac_a_mole

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gameIntent = Intent(this, GameActivity::class.java)

        var bestTextView = findViewById<TextView>(R.id.bestTextView)
        var best = 0

        var pref: SharedPreferences? = null
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        best = pref?.getInt("Best", 0)!!

        bestTextView.text = "Best: $best"

        var isAgain = intent.getStringExtra("isAgain")

        if (isAgain == "true") {
            startActivity(gameIntent)
            finish()
        }

        var playButton = findViewById<ImageButton>(R.id.playButton)
        playButton.setOnClickListener {
            startActivity(gameIntent)
            finish()
        }
    }
}