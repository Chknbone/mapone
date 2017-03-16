package com.palarran.mapone;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    //Defining Google Map objects variables
    GoogleMap mMap;
    boolean mapReady=false;

    //Defining map markers
    MarkerOptions shilshole;
    MarkerOptions laPlayita;
    MarkerOptions musketCove;

    static final CameraPosition START_POINT = CameraPosition.builder()
                                        .target(new LatLng(38.1254, -101.1703))
                                        .zoom(3)
                                        .bearing(359)
                                        .tilt(5)
                                        .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up map markers for Shilshole, La Playita, & Musket Cove with custom marker Icons
        shilshole = new MarkerOptions().position(new LatLng(47.6813, -122.4069))
                .title("Shilshole")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_directions_boat_black_24dp));
        laPlayita = new MarkerOptions().position(new LatLng(8.9098, -79.5250))
                .title("La Playita")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_directions_boat_black_24dp));
        musketCove = new MarkerOptions().position(new LatLng(-17.7721, 177.1925))
                .title("Musket Cove")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_directions_boat_black_24dp));

        //Calling up the map fragment from activity_main.xml
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Hooking up the Normal map view button.
    public void mapRegular(View v) {
        if (mapReady) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    //Hooking up the Satellite map view button.
    public void mapSatellite(View v) {
        if (mapReady) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
    }

    //Hooking up the Hybrid map view button.
    public void mapHybrid(View v) {
        if (mapReady) {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
    }

    //Google map object that will change the map fragment in activity_main.xml
    @Override
    //onMapReady CallBack method
    public void onMapReady(GoogleMap map) {

        //Setting mapReady to true
        mapReady=true;

        //Loading local instance map from Callback
        mMap = map;

        //Placing map markers (Shilshole, La Playita, & Musket Cove) on map
        mMap.addMarker(shilshole);
        mMap.addCircle(new CircleOptions()
                .center(shilshole)
                .radius(500)
                .strokeColor(Color.GREEN)
                .fillColor(Color.YELLOW));
        mMap.addMarker(laPlayita);
        mMap.addMarker(musketCove);
        //Set camera at starting point, high over the middle of the U.S of A.
        begin(START_POINT);
    }

    private void begin(CameraPosition target) {
        //Setting position to the target created above
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }

    //Hooking up the Seattle Button
    public void mapSeattle(View v) {
        if (mapReady) {
            //Create a new LatLng position for Seattle
            LatLng seattle = new LatLng(47.6813, -122.4069);

            //Setup the target to the new position in Seattle
            CameraPosition target = CameraPosition.builder().target(seattle).zoom(16).tilt(20).bearing(75).build();

            //Move Camera instantly to the new Seattle target
            //mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));

            //animated camera move to new location
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(target), 7000, null);
        }
    }

    //Hooking up the PanamaCity Button
    public void mapPanamaCity(View v) {
        if (mapReady) {
            //Create a new LatLng position for P. City
            LatLng panamaCity = new LatLng(8.9098, -79.5250);

            //Setup the target to the new position in P. City
            CameraPosition target = CameraPosition.builder().target(panamaCity).zoom(15).tilt(30).bearing(11).build();

            //animated camera move to new location
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(target), 7000, null);
        }
    }

    //Hooking up the Fiji Button
    public void mapFiji(View v) {
        if (mapReady) {
            //Create a new LatLng position for Fiji
            LatLng fiji = new LatLng(-17.7721, 177.1925);

            //Setup the target to the new position in Seattle
            CameraPosition target = CameraPosition.builder().target(fiji).zoom(15).tilt(25).bearing(80).build();

            //animated camera move to new location
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(target), 7000, null);


        }
    }
}
