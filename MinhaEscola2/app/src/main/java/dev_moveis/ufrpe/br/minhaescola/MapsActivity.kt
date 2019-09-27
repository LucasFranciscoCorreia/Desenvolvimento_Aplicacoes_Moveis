package dev_moveis.ufrpe.br.minhaescola


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker
import android.content.Intent
// só pro commit ir


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    /** Keeps track of the selected marker. It will be set to null if no marker is selected. */
    private var selectedMarker: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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


        // Colocar aqui o código para ativar abrir a janela de detalhes quando clicar no marker
    }

    private fun abrirJanela(): Boolean {
        val detalhes = Intent(this, DetalhesEscola::class.java)
        startActivity(detalhes)

        return false
    }
}
