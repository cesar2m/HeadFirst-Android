package com.cesar2m.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    lateinit var stopwatch: Chronometer
    var running = false
    var offset: Long = 0

    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(this@MainActivity, "(A) onCreate()", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch = findViewById<Chronometer>(R.id.stopwatch)


        if (savedInstanceState != null){
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if(running){
                stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                stopwatch.start()
            }else{
                setBaseTime()
            }
        }

        val startButton = findViewById<Button>(R.id.start_button)
            startButton.setOnClickListener{
                if(!running){
                    setBaseTime()
                    stopwatch.start()
                    running = true
                }

        }


        val pauseButton = findViewById<Button>(R.id.pause_button)
            pauseButton.setOnClickListener{
            if(running){
                saveOffset()
                stopwatch.stop()
                running = false
            }
        }

        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener{
            offset = 0
            setBaseTime()
        }

    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        Toast.makeText(this@MainActivity, "onSaveInstanceState()", Toast.LENGTH_SHORT).show()
        savedInstanceState.putLong(OFFSET_KEY,offset)
        savedInstanceState.putBoolean(RUNNING_KEY, running)
        savedInstanceState.putLong(BASE_KEY, stopwatch.base)
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
            stopwatch.start()
            offset = 0
        }
    }

    override fun onPause() {
        Toast.makeText(this@MainActivity, "(B) onPause()", Toast.LENGTH_SHORT).show()

        super.onPause()
        if (running) {
            saveOffset()
            stopwatch.stop()
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
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    fun saveOffset(){
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }


}