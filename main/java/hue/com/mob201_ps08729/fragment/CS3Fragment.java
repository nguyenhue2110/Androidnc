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
import com.google.android.gms.maps.model.MarkerOptions;

import hue.com.mob201_ps08729.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CS3Fragment extends Fragment implements OnMapReadyCallback {
    GoogleMap map;

    public CS3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cs3, container, false);

        SupportMapFragment mapFragment= (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map= googleMap;
        LatLng CVpmQT= new LatLng(10.818077,106.6289973);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CVpmQT,13));
        map.addMarker(new MarkerOptions().title("TPHCM").snippet("CVPMQT").position(CVpmQT));
    }
}
