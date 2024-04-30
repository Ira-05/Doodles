package com.example.doodleapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.CountDownTimer
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.doodleapp.PaintView.Companion.colorList
import com.example.doodleapp.PaintView.Companion.currentBrush
import com.example.doodleapp.PaintView.Companion.pathList
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer


    companion object {
        var path = Path()
        var paintBrush = Paint()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val redBtn = findViewById<ImageButton>(R.id.redColor)
        val blueBtn = findViewById<ImageButton>(R.id.blueColor)
        val grenBtn = findViewById<ImageButton>(R.id.greenColor)
        val ylowBtn = findViewById<ImageButton>(R.id.yellowColor)
        val eraser = findViewById<ImageButton>(R.id.whiteColor)
        val blackBtn = findViewById<ImageButton>(R.id.blackColor)

        redBtn.setOnClickListener {
            Toast.makeText(this, "Red Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.RED)
            currentColor(paintBrush.color)
        }

        blueBtn.setOnClickListener {
            Toast.makeText(this, "Blue Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.BLUE)
            currentColor(paintBrush.color)
        }

        grenBtn.setOnClickListener {
            Toast.makeText(this, "Green Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.GREEN)
            currentColor(paintBrush.color)
        }

        ylowBtn.setOnClickListener {
            Toast.makeText(this, "Yellow Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.YELLOW)
            currentColor(paintBrush.color)
        }

        eraser.setOnClickListener {
            Toast.makeText(this, "Eraser Clicked", Toast.LENGTH_SHORT).show()
            pathList.clear()
            colorList.clear()
            path.reset()
        }

        blackBtn.setOnClickListener {
            Toast.makeText(this, "Black Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.BLACK)
            currentColor(paintBrush.color)
        }

        startTimer()



    }

    fun startTimer() {
        val intent = Intent(this,ProfileActivity::class.java)
        countDownTimer = object : CountDownTimer(60000, 1000) { // 60 seconds countdown
            override fun onTick(millisUntilFinished: Long) {
                // Update the timer TextView with the remaining time
                val minutes = millisUntilFinished / 1000 / 60
                val seconds = millisUntilFinished / 1000 % 60
                val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
                findViewById<TextView>(R.id.timerTextView).text = timeLeftFormatted

            }

            override fun onFinish() {
                // Handle actions when the timer finishes
                Toast.makeText(this@MainActivity, "Drawing Uploaded", Toast.LENGTH_SHORT).show()
//                val file = File("Absolute Path")
//                val inputStream = FileInputStream(file)
//                val bitmap = BitmapFactory.decodeStream(inputStream)

//                // Convert the bitmap to a byte array
//                val stream = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//                val imageData = stream.toByteArray()

                // Create an intent to start the next activity
//                val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                // Pass the image data as an extra to the next activity
//                intent.putExtra("imageData", imageData)
                startActivity(intent)
            }
        }
        countDownTimer.start()
    }

    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }
}