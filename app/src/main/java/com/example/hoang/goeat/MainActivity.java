package com.example.hoang.goeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    List<DataItem> dataItemList = SampleDataProvider.dataItemList;
    List<String> itemNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
//        menu.getItem(0).setIcon(R.drawable.ict);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);

            RecyclerView recycleView = (RecyclerView) findViewById(R.id.rvItems);
            recycleView.setAdapter(adapter);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//set map zoom level 15 to UC
        LatLng center = new LatLng(-35.296533, 149.126101);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 13));
//Map loaded set center
        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                moveCameraToCenter();
            }
            private  void moveCameraToCenter(){
//build newLatLngBounds
                LatLng n = new LatLng(-35.281680, 149.109449);
                LatLng s = new LatLng(-35.282241, 149.146528);
                LatLng e = new LatLng(-35.332532, 149.108591);
                LatLng w = new LatLng(-35.330711, 149.145327);
                int padding = 30;
                LatLngBounds.Builder b = new LatLngBounds.Builder();
                b.include(n);
                b.include(s);
                b.include(e);
                b.include(w);
                LatLngBounds bounds = b.build();
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,padding));
            }
        });


//****************************************Bicicletta*************************************************************************
        MarkerOptions Bicicletta_marker =  new MarkerOptions()
            .position(new LatLng(-35.284644, 149.123831))
            .draggable(false)
            .title("Bicicletta Restaurant")
            .snippet("Food is delicious, services is so so ")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.biciclettalogo))
            .flat(true);
        final Marker Bicicletta = mMap.addMarker((Bicicletta_marker));
//*****************************************************Lilotang ***********************************************************
        MarkerOptions Lilotang_marker =  new MarkerOptions()
            .position(new LatLng(-35.311897, 149.133865))
            .draggable(false)
            .title("Lilotang Restaurant")
            .snippet("My review.....")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.lilotanglogo))
            .flat(true);
        final Marker Lilotang = mMap.addMarker((Lilotang_marker));
//*******************************************Onred**********************************************************************
        MarkerOptions Onred_marker =  new MarkerOptions()
            .position(new LatLng(-35.328537, 149.116769))
            .draggable(false)
            .title("Onred Restaurant")
            .snippet("definitely comeback here soon...")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.onredlogo))
            .flat(true);
        final Marker Onred = mMap.addMarker((Onred_marker));
//**************************************************************Parlour  *************************************
        MarkerOptions Parlour_marker =  new MarkerOptions()
            .position( new LatLng(-35.284932, 149.123907))
            .draggable(false)
            .title("Parlour Restaurant ")
            .snippet("fancy ....")
            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.parlourlogo))
            .flat(true);
        final Marker Parlour = mMap.addMarker((Parlour_marker));
////////////////////////////////////////////infoWindows//////////////////////////////////////////////////////////
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter(){
            @Override
            public View getInfoWindow(Marker marker){return null;}
            @Override
            public View getInfoContents(Marker marker){
                View infoWindow = getLayoutInflater().inflate(R.layout.infowindow_with_image, null);
                TextView title = (TextView) infoWindow.findViewById(R.id.title);
                TextView snippet = (TextView) infoWindow.findViewById(R.id.snippet);
                ImageView image = (ImageView) infoWindow.findViewById(R.id.info_image);
                title.setText(marker.getTitle());
                snippet.setText(marker.getSnippet());
                if (marker.getId().equals(Bicicletta.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.biciclettarestaurant, getTheme()));
                }if (marker.getId().equals(Lilotang.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.lilotangrestaurant, getTheme()));
                }if (marker.getId().equals(Onred.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.onredrestaurant, getTheme()));
                }if (marker.getId().equals(Parlour.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.parlourrestaurant, getTheme()));
                }

                return infoWindow;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getApplicationContext(), "Opening page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);

                if ( marker.getId().equals(Parlour.getId())) {
                    intent.putExtra("url", "http://www.parlour.net.au/");
                }if ( marker.getId().equals(Onred.getId())) {
                    intent.putExtra("url", "http://www.onred.com.au/");
                }if ( marker.getId().equals(Lilotang.getId())) {
                    intent.putExtra("url", "http://chairmangroup.com.au/lilotang/");
                }if ( marker.getId().equals(Bicicletta.getId())) {
                    intent.putExtra("url", "http://www.bicicletta.com.au/");
                }
                startActivity(intent);
            }
        });
    }
}