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
 * Created by craigbryan on 30/11/15.
 */
public class CustomMapFragment extends SupportMapFragment {

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
