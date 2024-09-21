package com.practice.workoutapp

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding : ActivityHistoryBinding? =  null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setTitle("HISTORY")
        }
        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        val dao = (application as WorkoutApp).db.historyDao()
        getAllCompleteDates(dao)

    }

    private fun getAllCompleteDates (historyDao: HistoryDao){

        lifecycleScope.launch {

            historyDao.fetchAllDates().collect{allCompleteDatesList ->

                if (allCompleteDatesList.isNotEmpty()){
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataAvailable?.visibility = View.GONE

                    binding?.rvHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    val dates = ArrayList<String>()
                    for (date in allCompleteDatesList){
                        dates.add(date.date)
                    }

                    val historyAdapter = HistoryAdapter(ArrayList(dates))

                    binding?.rvHistory?.adapter = historyAdapter
                }else{
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistory?.visibility = View.GONE
                    binding?.tvNoDataAvailable?.visibility = View.VISIBLE

                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}