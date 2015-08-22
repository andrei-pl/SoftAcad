package com.mentoracademy.lecture16;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        //ANIMATE CAMERA
       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.64653915, 23.37826252), 14.0f));

        //MOVE CAMERA
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.64653915, 23.37826252), 14.0f));

        // ADD MARKERS
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.64653915, 23.37826252))
                .title("MentorMate Academy Sofia"));
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(42.13591336, 24.74219799))
                .title("MentorMate Academy Plovdiv")
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .alpha(0.7f)
                .flat(true));

        // CHANGE MAP TYPE
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        GoogleMapOptions options = new GoogleMapOptions();
        // MORE OPTIONS CUSTOMIZATION
        options.compassEnabled(true)
            .rotateGesturesEnabled(true)
            .tiltGesturesEnabled(true);

        // DRAWING ON THE MAP
        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(42.64653915, 23.37826252))
                .radius(1000); // In meters

        // Get back the mutable Circle
        Circle circle = mMap.addCircle(circleOptions);

        // Instantiates a new Polygon object and adds points to define a rectangle
        PolygonOptions rectOptions = new PolygonOptions()
                .add(new LatLng(42.64653915, 23.37826252),
                        new LatLng(42.65653915, 23.37826252),
                        new LatLng(42.65653915, 23.38826252),
                        new LatLng(42.64653915, 23.38826252),
                        new LatLng(42.64653915, 23.37826252));

        // Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);

        /* STREET VIEW
        // Displays an image of the Swiss Alps
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
        */
    }
}
