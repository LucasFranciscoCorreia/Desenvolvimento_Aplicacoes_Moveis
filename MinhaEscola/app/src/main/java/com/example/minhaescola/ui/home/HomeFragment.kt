package com.example.minhaescola.ui.home

import android.content.Intent
import android.os.Bundle
import com.example.minhaescola.DetalhesEscola
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions




class HomeFragment : SupportMapFragment(), OnMapReadyCallback {
    private lateinit var map: GoogleMap
//    private lateinit var homeViewModel: HomeViewModel
////
////
////    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
////        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
////        val root = inflater.inflate(R.layout.fragment_gmaps, container, false)
////
////        return root
////    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Recife and move the camera
        val recife = LatLng(-8.061610, -34.882411)
        map.addMarker(MarkerOptions().position(recife).title("Recife"))
        map.moveCamera(CameraUpdateFactory.newLatLng(recife))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(recife,12.0f))

        // carregando escolas da prefeitura do Recife
        val vovoArthur = LatLng(-8.068827873926091, -34.89105971935054)
        val mrkVovoArthur = map.addMarker(MarkerOptions().position(vovoArthur).title("Creche Municipal Vovô Arthur"))
        map.setOnMarkerClickListener { mrkVovoArthur ->  abrirJanela()}

    }


    private fun abrirJanela(): Boolean {
        val detalhes = Intent(this.context, DetalhesEscola::class.java)
        startActivity(detalhes)
        return false
    }
}