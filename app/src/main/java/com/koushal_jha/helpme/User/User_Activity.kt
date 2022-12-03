package com.koushal_jha.helpme.User

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.koushal_jha.helpme.R
import com.koushal_jha.helpme.databinding.ActivityUserBinding

class User_Activity : AppCompatActivity() {
    private lateinit  var binding:ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv3.setOnClickListener {
            Toast.makeText(this,"We need location permission to get mechanics" +
                    " nearby you please allow us",Toast.LENGTH_LONG).show()
        }
        binding.fetchLocationButton.setOnClickListener {
            if(!location_permission_given()){
                showToast("Please allow location permission to this app")
            }
            else if(!locationEnabled()){
                showToast("please was not enabled in your phone")
            }
            else{

            }
        }
    }

    private fun location_permission_given(): Boolean {
        return false
    }
    private fun locationEnabled():Boolean{
        return false
    }
    private fun showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}