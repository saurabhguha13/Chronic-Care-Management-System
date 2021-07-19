package com.example.chroniccaremanagementapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PharmacySearch : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_search)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val ambe = LatLng(24.484887606897633, 72.78736580418763)
        mMap.addMarker(MarkerOptions().position(ambe).title("Ambe Medical Store"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ambe))

        val kailash = LatLng(24.484845022428917, 72.78650994634536)
        mMap.addMarker(MarkerOptions().position(kailash).title("Kailash Medical Store"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kailash))

        val rathi = LatLng(24.486585549785996, 72.78542860747675)
        mMap.addMarker(MarkerOptions().position(rathi).title("Rathi Medical Shop"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rathi))

        val mm = LatLng(24.48533745654024, 72.78395715296634)
        mMap.addMarker(MarkerOptions().position(mm).title("Manish Medical Shop"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mm))

        val hariom = LatLng(24.48212856506753, 72.78161094374421)
        mMap.addMarker(MarkerOptions().position(hariom).title("Hari Om Medical Store"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hariom))

        val ss = LatLng(24.476035549025735, 72.78933570550211)
        mMap.addMarker(MarkerOptions().position(ss).title("Shiv Shakti Medical Store"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ss))

        val nn = LatLng(24.474473189703872, 72.78152511305802)
        mMap.addMarker(MarkerOptions().position(nn).title("Nagnechi Medical Store"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nn))

        val krish = LatLng(24.486251944616917, 72.78674296884914)
        mMap.addMarker(MarkerOptions().position(krish).title("Krishna Medical Store"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(krish))

        val jk = LatLng(24.474707544838445, 72.78204009717521)
        mMap.addMarker(MarkerOptions().position(jk).title("J.K. Medical Store"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jk))
    }
}