package hue.com.mob201_ps08729;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import hue.com.mob201_ps08729.database.dphelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView ngvtrangchu;
    TextView txtTenNhanVien;
    Toolbar toolbar;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dphelper dphelper= new dphelper(this);
        dphelper.open();

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        drawerLayout= findViewById(R.id.drwlayout);
        ngvtrangchu=findViewById(R.id.ngvtrangchu);
        View view= ngvtrangchu.getHeaderView(0);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.mo, R.string.dong){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        ngvtrangchu.setItemIconTintList(null);
        ngvtrangchu.setNavigationItemSelectedListener(this);
        fragmentManager=getSupportFragmentManager();
        FragmentTransaction nw= fragmentManager.beginTransaction();
        News1Fragment newsFragment1 = new News1Fragment();
        nw.replace(R.id.content,newsFragment1);
        nw.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        int id = menuItem.getItemId();
        switch (id){
            case R.id.Course:
                FragmentTransaction kh= fragmentManager.beginTransaction();
                KhoaHocFragment khoaHocFragment = new KhoaHocFragment();
                kh.replace(R.id.content,khoaHocFragment);
                kh.commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                break;

            case R.id.New:
                FragmentTransaction nw= fragmentManager.beginTransaction();
                News1Fragment newsFragment1 = new News1Fragment();
                nw.replace(R.id.content,newsFragment1);
                nw.commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                break;


            case R.id.Maps:
                FragmentTransaction map= fragmentManager.beginTransaction();
                MapFragment mapFragment = new MapFragment();
                map.replace(R.id.content,mapFragment);
                map.commit();
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                break;

            case R.id.thoat:
                finish();
                break;
        }

        return true;
    }
}
