package hue.com.mob201_ps08729.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import hue.com.mob201_ps08729.MapFragment;
import hue.com.mob201_ps08729.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CS1Fragment extends Fragment implements OnMapReadyCallback {
GoogleMap map;

    public CS1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cs1, container, false);

        SupportMapFragment mapFragment= (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
         return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map= googleMap;
        Marker marker;
        UiSettings uisetting = map.getUiSettings();

        uisetting.setCompassEnabled(true);
        uisetting.setZoomControlsEnabled(true);
        uisetting.setScrollGesturesEnabled(true);
        uisetting.setTiltGesturesEnabled(true);
        uisetting.setMyLocationButtonEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng CS1= new LatLng(10.7908587,106.6799722);
       map.moveCamera(CameraUpdateFactory.newLatLngZoom(CS1,13));
        marker=  map.addMarker(new MarkerOptions().title("TPHCM").snippet("CS1").position(CS1));
        marker.showInfoWindow();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(CS1, 15));
    }
}
