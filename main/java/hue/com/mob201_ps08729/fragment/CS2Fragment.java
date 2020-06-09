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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import hue.com.mob201_ps08729.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CS2Fragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    public CS2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cs2, container, false);

        SupportMapFragment mapFragment= (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

    return view;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map= googleMap;
        Marker marker;
        LatLng CS2= new LatLng(10.811857,106.6777233);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CS2,13));
     marker=   map.addMarker(new MarkerOptions().title("TPHCM").snippet("CS2").position(CS2));
        marker.showInfoWindow();
   }
}
