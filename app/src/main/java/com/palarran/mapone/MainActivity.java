package com.palarran.mapone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    //Defining Google Map objects variables
    GoogleMap mMap;
    boolean mapReady=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calling up the map fragment from activity_main.xml
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Google map object that will change the map fragment in the .xml
    @Override
    //onMapReady CallBack
    public void onMapReady(GoogleMap map) {

        mapReady=true;
        //Loading local instance map from Callback
        mMap = map;

        //Creating a new LatLng. i.e. Location Map starts up at
        LatLng fiji = new LatLng(-17.7721, 177.1925);

        //Setting camera position with builder and setting target at the LatLong and
        //setting camera starting height(zoom level) as well
        CameraPosition target = CameraPosition.builder().target(fiji).zoom(15).build();

        //Setting position to the target
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));

    }
}
