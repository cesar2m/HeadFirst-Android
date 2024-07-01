package com.cesar2m.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cesar2m.stopwatch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var running = false
    var offset: Long = 0

    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this@MainActivity, "(A) onCreate()", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =binding.root
        setContentView(view)
        //setContentView(R.layout.activity_main)//Se eliminó al usar Binding

        if (savedInstanceState != null){
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if(running){
                binding.stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                binding.stopwatch.start()
            }else{
                setBaseTime()
            }
        }

        binding.startButton.setOnClickListener{
                if(!running){
                    setBaseTime()
                    binding.stopwatch.start()
                    running = true
                }

        }


        binding.pauseButton.setOnClickListener{
            if(running){
                saveOffset()
                binding.stopwatch.stop()
                running = false
            }
        }

        binding.resetButton.setOnClickListener{
            offset = 0
            setBaseTime()
        }

    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        Toast.makeText(this@MainActivity, "onSaveInstanceState()", Toast.LENGTH_SHORT).show()
        savedInstanceState.putLong(OFFSET_KEY,offset)
        savedInstanceState.putBoolean(RUNNING_KEY, running)
        savedInstanceState.putLong(BASE_KEY, binding.stopwatch.base)
        super.onSaveInstanceState(savedInstanceState)

    }


    override fun onStart() {
        Toast.makeText(this@MainActivity, "(G) onStart()", Toast.LENGTH_SHORT).show()
        super.onStart()
    }

    override fun onResume() {
        Toast.makeText(this@MainActivity, "(D) onResume()", Toast.LENGTH_SHORT).show()
        super.onResume()
        if (running) {
            setBaseTime()
            binding.stopwatch.start()
            offset = 0
        }
    }

    override fun onPause() {
        Toast.makeText(this@MainActivity, "(B) onPause()", Toast.LENGTH_SHORT).show()

        super.onPause()
        if (running) {
            saveOffset()
            binding.stopwatch.stop()
        }
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this@MainActivity, "(E) onStop()", Toast.LENGTH_SHORT).show()

    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this@MainActivity, "(C) onRestart()", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this@MainActivity, "(H) onDestroy()", Toast.LENGTH_SHORT).show()
    }




    fun setBaseTime(){
        binding.stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffset(){
        offset = SystemClock.elapsedRealtime() - binding.stopwatch.base
    }


}