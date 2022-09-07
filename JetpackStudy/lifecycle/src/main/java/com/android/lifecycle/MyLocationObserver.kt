package com.android.lifecycle

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationObserver(_context: Context) : LifecycleObserver {
    val context = _context
    lateinit var locationManager: LocationManager
    lateinit var locationListener: MyLocationListener

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun startGetLocation() {
        Log.d("lcy","startGetLocation")
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = MyLocationListener()

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        locationManager.apply {
            requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 1f, locationListener)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun stopGetLocation() {
        Log.d("lcy", "stopGetLocation")
        locationManager.removeUpdates(locationListener)
    }

    class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d("lcy", "location changed: " + location.toString())
        }
    }
}