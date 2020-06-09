package hue.com.mob201_ps08729;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import hue.com.mob201_ps08729.fragment.CS1Fragment;
import hue.com.mob201_ps08729.fragment.CS2Fragment;
import hue.com.mob201_ps08729.fragment.CS3Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    BottomNavigationView bottomMenu;
    FrameLayout frmmap;

    CS1Fragment cs1Fragment;
    CS2Fragment cs2Fragment;
    CS3Fragment cs3Fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        bottomMenu = view.findViewById(R.id.bottomngv);
        bottomMenu.inflateMenu(R.menu.menu_bottom);
        bottomMenu.setItemIconTintList(null);
        frmmap = view.findViewById(R.id.frmmap);
        getFragmentManager().beginTransaction().replace(R.id.frmmap, new CS1Fragment()).commit();
        cs1Fragment = new CS1Fragment();
        cs2Fragment = new CS2Fragment();
        cs3Fragment = new CS3Fragment();


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BottomNavigationView bottomMenu = view.findViewById(R.id.bottomngv);
        bottomMenu.setOnNavigationItemSelectedListener( navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener()  {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.cs1:
                    selectFragment = new CS1Fragment();
                    break;
                case R.id.CS2:
                    selectFragment = new CS2Fragment();
                    break;
                case R.id.CS3:
                    selectFragment = new CS3Fragment();
                    break;
            }

            getFragmentManager().beginTransaction().replace(R.id.frmmap, selectFragment).commit();
            return true;

        }
    };
}