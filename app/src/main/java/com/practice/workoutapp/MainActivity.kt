package com.practice.workoutapp

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.practice.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener{
            Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show()

            val intent  = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }
        binding?.flBMI?.setOnClickListener {

            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }
        binding?.flHistory?.setOnClickListener {
            // Launching the History Activity
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}