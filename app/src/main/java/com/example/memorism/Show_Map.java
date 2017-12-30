package com.example.memorism;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.memorism.memory.MemoryContent;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Show_Map extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        addAllLocation(googleMap);
    }

    public void addAllLocation(GoogleMap googleMap)
    {
        LatLng currentPosition = new LatLng(0.0,0.0);
        if(MemoryContent.ITEMS.size() > 0)
        {
            for(MemoryContent.MemoryItem currentItem : MemoryContent.ITEMS)
            {
               currentPosition = new LatLng(currentItem.getLatitude(),currentItem.getLongitude()) ;
                googleMap.addMarker(new MarkerOptions().position(currentPosition).title(currentItem.getTitle()));
            }
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        }
    }
}
