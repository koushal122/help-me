package com.koushal_jha.helpme.Admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.koushal_jha.helpme.R

class Dashboard : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_dashboard_admin, container, false)
        val db=Firebase.firestore
        val pendingApproval=ArrayList<business_details>()
        db.collection("pendingApproval")
            .get()
            .addOnSuccessListener {
                for (document in it){
                    var pb:business_details=business_details("","",
                    "","","","","")
                    pb.Adress=document.get("adress").toString()
                    pb.businessName=document.get("businessName").toString()
                    pb.mobileNumber=document.get("mobileNumber").toString()
                    pb.ownerName=document.get("ownerName").toString()
                    pb.serviceOffering=document.get("serviveOffering").toString()
                    pb.availableDays=document.get("availableDays").toString()
                    pb.timming=document.get("timming").toString()
                    pendingApproval.add(pb)

                }
                val recyclerview=this.requireActivity().findViewById<RecyclerView>(R.id.pending_approval_Recycler_View)
                recyclerview.layoutManager=LinearLayoutManager(this.requireActivity())
                val adapter=pedingApprovalAdapter(pendingApproval,this.requireActivity())
                recyclerview.adapter=adapter
            }
            .addOnFailureListener{
                showMessage("Something Went wrong")
            }

        return view
    }
    fun showMessage(msg:String){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
    }
}