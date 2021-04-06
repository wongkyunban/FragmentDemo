package com.example.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var container1:LinearLayout
    private lateinit var container2:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container1 = findViewById(R.id.container_start)
        container2 = findViewById(R.id.container_end)
        addFragmentToHost()
    }
    private fun addFragmentToHost(){
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container_start,DrawerFragment())
                .commit()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container_end,MenuFragment())
                .commit()
    }
}