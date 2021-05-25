package com.cartrack.portal.user.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.cartrack.portal.user.Constant
import com.cartrack.portal.user.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class UserDetailActivity : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var latitude: Double = 0.00
    private var longitude: Double = 0.00
    var userName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_map)

        var bundle: Bundle? = intent.extras
        bundle?.let {
            latitude = bundle.getString(Constant.EXTRA_LATITUDE, "0.0").toDouble()
            longitude = bundle.getString(Constant.EXTRA_LONGITUDE, "0.00").toDouble()
            userName = bundle.getString(Constant.EXTRA_NAME, "DEFAULT")
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        val location = LatLng(latitude, longitude)
        mMap?.let {
            it.addMarker(MarkerOptions().position(location).title(userName))
            it.moveCamera(CameraUpdateFactory.newLatLng(location))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}