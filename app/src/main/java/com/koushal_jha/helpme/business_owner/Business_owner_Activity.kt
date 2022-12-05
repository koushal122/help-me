package com.koushal_jha.helpme.business_owner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koushal_jha.helpme.R

class Business_owner_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business_owner)
        supportActionBar!!.hide()
    }
}