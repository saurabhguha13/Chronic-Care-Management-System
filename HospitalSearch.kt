package com.example.chroniccaremanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class HospitalSearch : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_search)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val chandrawal = LatLng(24.50291591350239, 72.79056603207347)
        mMap.addMarker(MarkerOptions().position(chandrawal).title("Chandrawal Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chandrawal))

        val agrawal = LatLng(24.492519526216338, 72.792488139783)
        mMap.addMarker(MarkerOptions().position(agrawal).title("Agrawal Orthopaedic Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(agrawal))

        val globalh = LatLng(24.469323216605506, 72.77008549062585)
        mMap.addMarker(MarkerOptions().position(globalh).title("GLobal Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(globalh))

        val esi = LatLng(24.466226517761065, 72.77922038456772)
        mMap.addMarker(MarkerOptions().position(esi).title("ESI Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(esi))

        val geetanjali = LatLng(24.47173492470689, 72.78207825445222)
        mMap.addMarker(MarkerOptions().position(geetanjali).title("Geetanjali Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(geetanjali))

        val sanjeev = LatLng(24.48082120599452, 72.7822432404323)
        mMap.addMarker(MarkerOptions().position(sanjeev).title("Sanjeevni Lifecare Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sanjeev))

        val railway = LatLng(24.48246072547735, 72.78818813584515)
        mMap.addMarker(MarkerOptions().position(railway).title("Railway Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(railway))

        val abhin = LatLng(24.48101248436826, 72.77918071855295)
        mMap.addMarker(MarkerOptions().position(abhin).title("Abhinav Bal Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(abhin))

        val lifecare = LatLng(24.494930122288086, 72.7939992974317)
        mMap.addMarker(MarkerOptions().position(lifecare).title("LifeCare Hospital"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lifecare))
    }
}