package com.bangk.bangk_android_prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by craigbryan on 30/11/15.
 */
public class MapFragment extends Fragment {

    @Override
    public View onCreateView(
        LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View layout = inflater.inflate(R.layout.view_map, container, false);

        CustomMapFragment mapFragment = new CustomMapFragment();
        getChildFragmentManager().beginTransaction().add(
            R.id.map_container, mapFragment
        ).commit();
        return layout;
    }
}
