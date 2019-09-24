package dev_moveis.ufrpe.br.minhaescola

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Recife and move the camera
        val recife = LatLng(-8.061610, -34.882411)
        mMap.addMarker(MarkerOptions().position(recife).title("Recife"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(recife))

        // carregando escolas da prefeitura do Recife
        val vovoArthur = LatLng(-8.068827873926091, -34.89105971935054)
        mMap.addMarker(MarkerOptions().position(vovoArthur).title("Creche Municipal Vovô Arthur"))

        // Colocar aqui o código para ativar abrir a janela de detalhes quando clicar no marker
    }
}
