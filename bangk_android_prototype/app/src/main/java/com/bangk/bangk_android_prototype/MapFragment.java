package com.bangk.bangk_android_prototype;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Standard fragment to allow loading of the map fragment in the same way other
 * fragments are loaded. Just wraps the loading of the Google map fragment.
 */
public class MapFragment extends Fragment {

    /**
     * Loads and initializes the view for the map view.
     * @param inflater - inflater to create views associated with the underlying
     *                 activity
     * @param container - the parent view that holds the views being loaded
     * @param savedInstanceState - data that allows the program to contain
     *                           state to allow it to be reloaded on app resume
     * @return the View to be displayed by the underlying Activity
     */
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
