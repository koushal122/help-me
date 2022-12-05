package com.koushal_jha.helpme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koushal_jha.helpme.Admin.Admin_activity
import com.koushal_jha.helpme.RegisterAndLogin.RegisterActivity
import com.koushal_jha.helpme.User.User_Activity
import com.koushal_jha.helpme.business_owner.Business_owner_Activity
import com.koushal_jha.helpme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.adminButton.setOnClickListener{
            val intent=Intent(this,RegisterActivity::class.java)
            intent.putExtra("type","admin");
            startActivity(intent)
            finish()
        }
        binding.MechanicBussinessButton.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            intent.putExtra("type","business_owner");
            startActivity(intent)
            finish()
        }
        binding.User.setOnClickListener {
            val intent=Intent(this,RegisterActivity::class.java)
            intent.putExtra("type","user");
            startActivity(intent)
            finish()
        }
    }
}