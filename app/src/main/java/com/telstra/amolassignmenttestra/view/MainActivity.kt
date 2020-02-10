package com.telstra.amolassignmenttestra.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telstra.amolassignmenttestra.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.replace(R.id.frgment, MainFragment())
        transaction.commit()


    }




}