package com.example.chroniccaremanagementapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_doctor_facilities.*

class DoctorFacilities : AppCompatActivity() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val collectionReference: CollectionReference = db.collection("patient_registration")
    var userAdapter:UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_facilities)
        setUpRecyclerView()

        btnFab.setOnClickListener {
            startActivity(Intent(this@DoctorFacilities, MainActivity::class.java))
        }
    }

    fun setUpRecyclerView() {
        val query : Query = collectionReference
        val firestoreRecyclerOptions: FirestoreRecyclerOptions<UsersModel> = FirestoreRecyclerOptions.Builder<UsersModel>()
                .setQuery(query, UsersModel::class.java)
                .build()
        userAdapter = UserAdapter(firestoreRecyclerOptions)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = userAdapter
    }

    override fun onStart() {
        super.onStart()
        userAdapter!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        userAdapter!!.stopListening()
    }
}