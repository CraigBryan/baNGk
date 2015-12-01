package com.bangk.bangk_android_prototype;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Wrapper fragment for loading a Google Maps View. Currently mocks the location
 * loading to a fixed location and shows no results on the map.
 */
public class CustomMapFragment extends SupportMapFragment {

    /**
     * Called when this fragment is initialized. Loads elements on the map.
     * @param savedInstanceState - data that allows the program to contain
     *                           state to allow it to be reloaded
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        GoogleMap gMap = getMap();

        // For prototype, hard-coded to zoom into lees campus
        LatLng latLng = new LatLng(45.415786, -75.667693);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
            latLng, 15
        );

        gMap.addMarker(new MarkerOptions().position(latLng).icon(
            BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_GREEN
            )
        ).alpha(0.7f));

        gMap.animateCamera(cameraUpdate);
    }
}
