package com.telstra.amolassignmenttestra.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telstra.amolassignmenttestra.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actvitymain)
        if (savedInstanceState == null) {
            CallFragment()
        }
    }

    // attached the Fragment
    private fun CallFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frgment, MainFragment()!!)
        fragmentTransaction.commit()
    }

}