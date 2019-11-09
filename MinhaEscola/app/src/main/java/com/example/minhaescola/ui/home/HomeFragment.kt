package com.example.minhaescola.ui.home

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import com.example.minhaescola.DetalhesEscola
import com.example.minhaescola.dao.Escola
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.*

class RequestSchoolsFromRemote(var map: GoogleMap) : AsyncTask<Void, Void, ArrayList<Escola>>() {
    var listescolas : ArrayList<Escola> = ArrayList()
    override fun doInBackground(vararg p0: Void?): ArrayList<Escola> {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        val escolas = FirebaseDatabase.getInstance().getReference("escolas")
        val dEscolas = ArrayList<Escola>()
        escolas.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (child in p0.children) {
                    dEscolas.add(child.getValue(Escola::class.java)!!)
                    val long = dEscolas.last().geometry?.coordinates?.get(0)?.get(0)?.get(0)
                    val lat = dEscolas.last().geometry?.coordinates?.get(0)?.get(0)?.get(1)
                    if (lat != null && long != null) {
                        map.addMarker(
                            MarkerOptions().position(
                                LatLng(
                                    lat,
                                    long
                                )
                            ).title(dEscolas.last().properties?.escola_nome)
                        )
                    }
                }
                listescolas = dEscolas
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        })
        return dEscolas
    }
}


class HomeFragment : SupportMapFragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
    private lateinit var request : RequestSchoolsFromRemote
    private var escolas : ArrayList<Escola> = ArrayList()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        request = RequestSchoolsFromRemote(map)
        request.execute()
        val recife = LatLng(-8.061610, -34.882411)
//        map.addMarker(MarkerOptions().position(recife).title("Recife"))
        map.moveCamera(CameraUpdateFactory.newLatLng(recife))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(recife,12.0f))
    }


    private fun abrirJanela(): Boolean {
        val detalhes = Intent(this.context, DetalhesEscola::class.java)
        startActivity(detalhes)
        return false
    }
}