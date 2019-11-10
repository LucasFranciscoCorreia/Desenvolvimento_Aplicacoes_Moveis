package com.example.minhaescola.ui.home

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.minhaescola.DetalhesEscola
import com.example.minhaescola.dao.Escola
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*

class RequestSchoolsFromRemote(val map: GoogleMap, val context: Context?, val home : HomeFragment) : AsyncTask<Void, Void, ArrayList<Escola>>(){
    var listescolas : ArrayList<Escola> = ArrayList()
    @Suppress("NAME_SHADOWING")
    override fun doInBackground(vararg p0: Void?): ArrayList<Escola> {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        val escolas = FirebaseDatabase.getInstance().getReference("escolas")
        val dEscolas = ArrayList<Escola>()
        escolas.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (child in p0.children) {
                    val esc: Escola = (child.getValue(Escola::class.java)!!)
                    dEscolas.add(esc)
                    val long = esc.geometry?.coordinates?.get(0)?.get(0)?.get(0)
                    val lat = esc.geometry?.coordinates?.get(0)?.get(0)?.get(1)
                    if (lat != null && long != null) {
                        val marker = map.addMarker(MarkerOptions().position(LatLng(lat, long)).title(esc.properties?.escola_nome))
                        marker.tag = esc
                    }
                }
                map.setOnMarkerClickListener(home)
                listescolas = dEscolas
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        return dEscolas
    }
}


class HomeFragment : SupportMapFragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(marker: Marker?): Boolean {
        abrirJanela(marker?.tag as Escola)
        return false
    }

    private lateinit var map: GoogleMap
    private lateinit var request : RequestSchoolsFromRemote
    private var escolas : ArrayList<Escola> = ArrayList()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        request = RequestSchoolsFromRemote(map, this.context, this)
        request.execute()
        val recife = LatLng(-8.061610, -34.882411)
//        map.addMarker(MarkerOptions().position(recife).title("Recife"))
        map.moveCamera(CameraUpdateFactory.newLatLng(recife))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(recife,12.0f))
    }


    private fun abrirJanela(esc : Escola) :Boolean{
        val detalhes = Intent(context, DetalhesEscola::class.java)
        detalhes.putExtra("escola", esc)

        startActivity(context!!, detalhes, null)
        return false
    }
}