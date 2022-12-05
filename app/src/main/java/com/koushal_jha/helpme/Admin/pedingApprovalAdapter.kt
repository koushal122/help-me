package com.koushal_jha.helpme.Admin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.koushal_jha.helpme.R

class pedingApprovalAdapter (val pedingApproval:ArrayList<business_details>,val context: Context):RecyclerView.Adapter<pedingApprovalAdapter.viewHolder>() {
    class viewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
        var businessName=itemview.findViewById<TextView>(R.id.bussines_name)
        var ownerName=itemview.findViewById<TextView>(R.id.ownerName)
        var mobileNumber=itemview.findViewById<TextView>(R.id.mobileNumber)
        var availableDays=itemview.findViewById<TextView>(R.id.availableDays)
        var timming=itemview.findViewById<TextView>(R.id.timming)
        var adress=itemview.findViewById<TextView>(R.id.fullAdress)
        var approve=itemview.findViewById<Button>(R.id.approve_id)
        var deny=itemview.findViewById<Button>(R.id.deny_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.admin_business_detail_approval, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val bd=pedingApproval[position]
        holder.businessName.text=bd.businessName
        holder.adress.text=bd.Adress
        holder.timming.text=bd.timming
        holder.availableDays.text=bd.availableDays
        holder.mobileNumber.text=bd.mobileNumber
        holder.ownerName.text=bd.ownerName
        holder.approve.setOnClickListener {
            showMessage("clicked oN "+bd.businessName)
        }
        holder.deny.setOnClickListener {
            showMessage("clicked oN "+bd.businessName)
        }

    }

    override fun getItemCount(): Int {
        return pedingApproval.size
    }
    fun showMessage(msg:String){
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show()
    }
}