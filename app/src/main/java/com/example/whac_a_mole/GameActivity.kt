package com.example.whac_a_mole

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random
import kotlin.random.nextInt

class GameActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val menuIntent = Intent(this, MainActivity::class.java)

        var timeTextView = findViewById<TextView>(R.id.timeTextView)
        var scoreTextView = findViewById<TextView>(R.id.scoreTextView)

        var firstHole = findViewById<ImageButton>(R.id.firstHoleButton)
        var secondHole = findViewById<ImageButton>(R.id.secondHoleButton)
        var thirdHole = findViewById<ImageButton>(R.id.thirdHoleButton)
        var fourthHole = findViewById<ImageButton>(R.id.fourthHoleButton)
        var fifthHole = findViewById<ImageButton>(R.id.fifthHoleButton)
        var sixthHole = findViewById<ImageButton>(R.id.sixthHoleButton)
        var seventhHole = findViewById<ImageButton>(R.id.seventhHoleButton)
        var eighthHole = findViewById<ImageButton>(R.id.eighthHoleButton)
        var ninthHole = findViewById<ImageButton>(R.id.ninthHoleButton)

        var menuButton = findViewById<ImageButton>(R.id.menuButton)

        var score = 0
        var best = 0
        var holeID = 0
        var isAgain = "false"
        var pref: SharedPreferences? = null
        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
        best = pref?.getInt("Best", 0)!!

        val gameOverMenu = AlertDialog.Builder(this)
        gameOverMenu.setTitle("Game Over")
        gameOverMenu.setPositiveButton("Play again") { dialogInterface: DialogInterface, i: Int ->
            run {
                isAgain = "true"
                menuIntent.putExtra("isAgain", isAgain)
                startActivity(menuIntent)
                finish()
            }
        }
        gameOverMenu.setNeutralButton("Menu") { dialogInterface: DialogInterface, i: Int ->
            run {
                startActivity(menuIntent)
                finish()
            }
        }

        var timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeTextView.setText("Time: ${millisUntilFinished / 1000} s.")
            }

            override fun onFinish() {
                isAgain = "false"

                if (score > best) {
                    best = score
                    val editor = pref?.edit()
                    editor?.putInt("Best", best)
                    editor?.apply()
                }

                gameOverMenu.setMessage("Score: $score\nBest: $best")
                gameOverMenu.show()
            }
        }.start()

        fun click(button: ImageButton, id: Int) {
            if (holeID == id) {
                score++
                scoreTextView.text = "Score: $score"
                button.setImageResource(R.drawable.dust)
                holeID = 0
            }
        }

        fun resetHoles() {
            firstHole.setImageResource(R.drawable.hole)
            secondHole.setImageResource(R.drawable.hole)
            thirdHole.setImageResource(R.drawable.hole)
            fourthHole.setImageResource(R.drawable.hole)
            fifthHole.setImageResource(R.drawable.hole)
            sixthHole.setImageResource(R.drawable.hole)
            seventhHole.setImageResource(R.drawable.hole)
            eighthHole.setImageResource(R.drawable.hole)
            ninthHole.setImageResource(R.drawable.hole)
            holeID = 0
        }

        var moleTimer = object : CountDownTimer(30000, 500) {
            override fun onTick(millisUntilFinished: Long) {
                resetHoles()
                var randomNumber = Random.nextInt(1..9)
                when (randomNumber) {
                    1 -> {
                        firstHole.setImageResource(R.drawable.mole)
                        holeID = 1
                    }
                    2 -> {
                        secondHole.setImageResource(R.drawable.mole)
                        holeID = 2
                    }
                    3 -> {
                        thirdHole.setImageResource(R.drawable.mole)
                        holeID = 3
                    }
                    4 -> {
                        fourthHole.setImageResource(R.drawable.mole)
                        holeID = 4
                    }
                    5 -> {
                        fifthHole.setImageResource(R.drawable.mole)
                        holeID = 5
                    }
                    6 -> {
                        sixthHole.setImageResource(R.drawable.mole)
                        holeID = 6
                    }
                    7 -> {
                        seventhHole.setImageResource(R.drawable.mole)
                        holeID = 7
                    }
                    8 -> {
                        eighthHole.setImageResource(R.drawable.mole)
                        holeID = 8
                    }
                    9 -> {
                        ninthHole.setImageResource(R.drawable.mole)
                        holeID = 9
                    }
                }
            }

            override fun onFinish() {
                resetHoles()
            }
        }.start()

        firstHole.setOnClickListener {
            click(firstHole, 1)
        }
        secondHole.setOnClickListener {
            click(secondHole, 2)
        }
        thirdHole.setOnClickListener {
            click(thirdHole, 3)
        }
        fourthHole.setOnClickListener {
            click(fourthHole, 4)
        }
        fifthHole.setOnClickListener {
            click(fifthHole, 5)
        }
        sixthHole.setOnClickListener {
            click(sixthHole, 6)
        }
        seventhHole.setOnClickListener {
            click(seventhHole, 7)
        }
        eighthHole.setOnClickListener {
            click(eighthHole, 8)
        }
        ninthHole.setOnClickListener {
            click(ninthHole, 9)
        }

        menuButton.setOnClickListener {
            startActivity(menuIntent)
            finish()
        }
    }
}