package ie.wit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import ie.wit.R
import ie.wit.main.DepositDiaryApp
import ie.wit.utils.setMapMarker
import ie.wit.utils.trackLocation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : SupportMapFragment(), OnMapReadyCallback {

    lateinit var app: DepositDiaryApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as DepositDiaryApp
        getMapAsync(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = getString(R.string.toilets_title)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        app.mMap = googleMap
        app.mMap.isMyLocationEnabled = true
        app.mMap.uiSettings.isZoomControlsEnabled = true
        app.mMap.uiSettings.setAllGesturesEnabled(true)
        app.mMap.clear()
        trackLocation(app)
        setMapMarker(app)
        var wit = LatLng(52.245696, -7.139102)
        googleMap.addMarker(
            MarkerOptions().position(wit)
                .title("Waterford Institute of Technology Public toilets")
        )
        var citysquare = LatLng(52.2603457, -7.109914027728097)
        googleMap.addMarker(
            MarkerOptions().position(citysquare)
                .title("City Square Public toilets")
        )
        var ardkeenstores = LatLng(52.2466058, -7.083996866388315)
        googleMap.addMarker(
            MarkerOptions().position(ardkeenstores)
                .title("Ardkeen Shopping Centre Public toilets")
        )
        var whospital = LatLng(52.248997200000005, -7.0781626608919765)
        googleMap.addMarker(
            MarkerOptions().position(whospital)
                .title("Ardkeen Hospital Public toilets")
        )
        var tescoardkeen = LatLng(52.2539923, -7.111845077008835)
        googleMap.addMarker(
            MarkerOptions().position(tescoardkeen)
                .title("Tesco Ardkeen Public toilets")
        )
        var maxol = LatLng(52.2424475, -7.069538)
        googleMap.addMarker(
            MarkerOptions().position(maxol)
                .title("Maxol Public toilets")
        )
        var londis = LatLng(52.2407796, -7.0666271)
        googleMap.addMarker(
            MarkerOptions().position(londis)
                .title("Londis Public toilets")
        )
        var hypercentre = LatLng(52.2606503, -7.121756199999998)
        googleMap.addMarker(
            MarkerOptions().position(hypercentre)
                .title("Hyper Centre Public toilets")
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MapFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}