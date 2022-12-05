package com.koushal_jha.helpme.business_owner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.koushal_jha.helpme.R
import com.koushal_jha.helpme.databinding.FragmentDashboardBusinessownerBinding

class dashboard : Fragment() {

    var binding:FragmentDashboardBusinessownerBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val db=Firebase.firestore

        binding=FragmentDashboardBusinessownerBinding.inflate(inflater, container, false)
        val view=binding!!.root
        binding!!.MyBusiness.setOnClickListener {

        }
        binding!!.Requestcurrently.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_new_business)
        }
        binding!!.Updatedetails.setOnClickListener {

        }
        binding!!.contactAdmin.setOnClickListener {

        }
        binding!!.history.setOnClickListener {

        }
        binding!!.approvalStatus.setOnClickListener {

        }
        return view
    }
}