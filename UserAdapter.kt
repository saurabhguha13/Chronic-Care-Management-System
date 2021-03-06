package com.example.chroniccaremanagementapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.row_users.view.*

class UserAdapter(options: FirestoreRecyclerOptions<UsersModel>) : FirestoreRecyclerAdapter<UsersModel, UserAdapter.UserAdapterVH>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterVH {
        return UserAdapterVH(LayoutInflater.from(parent.context).inflate(R.layout.row_users, parent, false))
    }

    override fun onBindViewHolder(holder: UserAdapterVH, position: Int, model: UsersModel) {
        holder.fName.text = model.fName
        holder.email.text = model.email
        holder.uname.text = model.uname
        holder.mobno.text = model.mobno
        holder.location.text = model.location
    }

    class UserAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fName = itemView.fName
        var email = itemView.email
        var uname = itemView.uname
        var mobno = itemView.mobno
        var location = itemView.location
    }
}