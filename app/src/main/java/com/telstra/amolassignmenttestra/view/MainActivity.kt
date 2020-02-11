package com.telstra.amolassignmenttestra.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.telstra.amolassignmenttestra.R

class MainActivity : AppCompatActivity(),MainFragment.OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CallFragment()
    }

    private fun CallFragment() {
        supportActionBar!!.title="Telestra"
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frgment, MainFragment()!!)
        fragmentTransaction.commit()
    }

    override fun onFragmentInteraction(uri: String?) {
        supportActionBar!!.title=uri
    }


}