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
        activity?.title = getString(R.string.maps_title)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        app.mMap = googleMap

        val wit = LatLng(52.245696, -7.139102)
        app.mMap.addMarker(MarkerOptions().position(wit).title("Marker in Waterford"))
        app.mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(wit, 14f))
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MapFragment().apply {
                arguments = Bundle().apply { }
            }
    }
}