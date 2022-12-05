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
import com.koushal_jha.helpme.databinding.ActivityLoginBinding
import com.koushal_jha.helpme.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val db= Firebase.firestore
        mAuth= FirebaseAuth.getInstance()
        binding.LoginActivityLoginNowButton.setOnClickListener {
            val email=binding.loginActivityEmailTextview.text.toString()
            val password=binding.loginActivityPassword.text.toString()
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val userId= mAuth.uid.toString()
                    var type:String=""
                    db.collection("users")
                        .document(userId)//after validation we will get unique userId identified by documentId
                        .get()
                        .addOnSuccessListener {
                            Log.d("loginInfo", "DocumentSnapshot data: ${it.data?.get("type")}")
                            val t:MutableMap<String,Any>
                            t= it.data as MutableMap<String, Any>
                            type=t.get("type").toString() //because we have saved type in info and from there we
                            //are getting type and going to respective activity
                            if(type.equals("admin")){
                                //to to admin Activity
                                startActivity(Intent(this, Admin_activity::class.java))
                                finish()
                            }
                            else if(type.equals("business_owner")){
                                startActivity(Intent(this, Business_owner_Activity::class.java))
                                finish()
                            }
                            else{
                                startActivity(Intent(this, User_Activity::class.java))
                                finish()
                            }
                        }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("loginInfailed", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
        }

    }
}