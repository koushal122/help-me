package com.koushal_jha.helpme.RegisterAndLogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.koushal_jha.helpme.Admin.Admin_activity
import com.koushal_jha.helpme.R
import com.koushal_jha.helpme.User.User_Activity
import com.koushal_jha.helpme.business_owner.Business_owner_Activity
import com.koushal_jha.helpme.databinding.ActivityRegisterBinding
import com.koushal_jha.helpme.user

class RegisterActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val db=Firebase.firestore
        //getting type from intent
        val type=intent.getStringExtra("type").toString()
        mAuth=FirebaseAuth.getInstance()
        binding.registerActivityRegisterButton.setOnClickListener {
            if(!AllFieldEntered())
            {
                Toast.makeText(this,"Enter all field",Toast.LENGTH_LONG).show()
            }
            else
            {
                var email=binding.registerActivityEmail.text.toString()
                var password=binding.registerActivityPassword.text.toString()
                var mobilenumber=binding.registerActivityMobile.text.toString()
                var name=binding.registerActivityName.text.toString()
                //creating account
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
                    task->
                    if(task.isSuccessful){
                        val userId=mAuth.uid.toString() //unique userId by this we will determine type of User
                        val user= hashMapOf<String,String>(
                            "email" to email,
                            "name" to name,
                            "mobile" to mobilenumber,
                            "userId" to userId,
                            "type" to type
                        )
                        db.collection("users")
                            .document(userId)//here we are passing userId as document name so we can identify unique user
                            .set(user)
                            .addOnSuccessListener {
                                 Toast.makeText(this,"user Added Succesfully",Toast.LENGTH_LONG).show()
                            }
                            .addOnFailureListener{
                                Toast.makeText(this,"Something went wrong in adding user",Toast.LENGTH_LONG).show()
                            }
                        //here untill this step we will get type of user
                        if(type=="admin"){
                            //to to admin Activity
                            startActivity(Intent(this,Admin_activity::class.java))
                            finish()
                        }
                        else if(type=="business_owner"){
                            startActivity(Intent(this,Business_owner_Activity::class.java))
                            finish()
                        }
                        else{
                            startActivity(Intent(this,User_Activity::class.java))
                            finish()
                        }

                    }
                    else{
                        Log.w("authenticationFailed", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        binding.loginPageButton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
    fun AllFieldEntered():Boolean{
        return binding.registerActivityName.text.toString()!=""&&
                binding.registerActivityEmail.text.toString()!=""&&
                binding.registerActivityMobile.text.toString()!=""&&
                binding.registerActivityPassword.text.toString()!=""
    }
}