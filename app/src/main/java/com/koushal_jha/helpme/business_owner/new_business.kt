package com.koushal_jha.helpme.business_owner

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.koushal_jha.helpme.R
import com.koushal_jha.helpme.databinding.FragmentNewBusinessBinding

class new_business : Fragment() {

    var longitude=""
    var latitude=""
    lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    private lateinit var mAuth: FirebaseAuth
    var _binding:FragmentNewBusinessBinding?=null
    private val binding get() = _binding!!
    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentNewBusinessBinding.inflate(inflater,container,false)
        val view=binding.root
        mAuth= FirebaseAuth.getInstance()
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this.requireActivity())
        binding.whytv.setOnClickListener {
            showMessage("Please Enable location to get your exact location")
        }
        binding.createbusiness.setOnClickListener {
            if(!allDetailsFilled()){
                showMessage("fill All details")
            }
            else if(longitude==""||latitude==""){
                showMessage("Please give your exact location by clicking on enable location")
            }
            else{
                val userId= mAuth.uid.toString()
                val db= Firebase.firestore
                val user= hashMapOf<String,String>(
                    "businessName" to binding.businessName.text.toString(),
                    "ownerName" to binding.ownername.text.toString(),
                    "mobileNumber" to binding.mobileNumber.text.toString(),
                    "availableDays" to binding.AvailableDays.text.toString(),
                    "adress" to binding.fullAdress.text.toString(),
                    "longitude" to longitude,
                    "latitude" to latitude,
                    "userId" to userId,
                    "timming" to binding.timming.text.toString()
                )
                db.collection("Businessowners")
                    .document(userId)
                    .set(user)
                    .addOnSuccessListener {
                        //adding to pending approval of admin
                        db.collection("pendingApproval")
                            .document()
                            .set(user)
                            .addOnSuccessListener {
                                showMessage("New Bussiness Sent to Admin Sucessfully Your business will start when Admin Approves it")
                            }
                            .addOnFailureListener{
                                showMessage("Something went wrong in sending to admin")
                            }
                    }
                    .addOnFailureListener{
                        showMessage("Something went wrong in your data")
                    }


            }
        }
        binding.LocationButton.setOnClickListener {
             //get location here
            if(!permission_given()){
                showMessage("please give location permission")
                ask_permission()
            }
            else {
                fusedLocationProviderClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    null
                ).addOnSuccessListener {
                    if (it != null) {
                        longitude = it.longitude.toString()
                        latitude = it.latitude.toString()
                        showMessage(longitude+" "+latitude)
                    } else {
                        showMessage("something went wrong in location fetching")
                    }

                }
            }
        }
        return view
    }

    private fun allDetailsFilled(): Boolean {
        return binding.businessName.text.toString()!=""&&
                binding.ownername.text.toString()!=""&&
                binding.mobileNumber.text.toString()!=""&&
                binding.fullAdress.text.toString()!=""&&
                binding.AvailableDays.toString()!=""&&
                binding.services.text.toString()!=""&&
                binding.timming.text.toString()!=""

    }

    fun showMessage(msg:String){
        Toast.makeText(this.requireActivity(),msg,Toast.LENGTH_LONG).show()
    }
    private fun ask_permission() {
        ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION),100)
    }

    private fun permission_given():Boolean {
        return (ActivityCompat.checkSelfPermission(this.requireActivity(),android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED&& ActivityCompat.checkSelfPermission(this.requireActivity(),android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)
    }
}