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
        if (id == R.id.Belconnen) {
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            LatLng belconnen = new LatLng(-35.237000, 149.065);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(belconnen,13));
            return true;
        }
        if (id == R.id.Gungahlin) {
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            LatLng gungahlin = new LatLng(-35.184881, 149.134284);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gungahlin,13));
            return true;
        }
        if (id == R.id.Tuggeranong) {
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            LatLng tuggeranong  = new LatLng(-35.416431, 149.066604);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tuggeranong ,13));
            return true;
        }
        if (id == R.id.Woden) {
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            LatLng woden = new LatLng(-35.346162, 149.087235);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(woden,13));
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
        //**************************************************************chong co  *************************************
        MarkerOptions chongco_marker =  new MarkerOptions()
                .position( new LatLng(-35.237636, 149.063218))
                .draggable(false)
                .title("Chong Co Restaurant ")
                .snippet("asia cuisin ....")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.parlourlogo))
                .flat(true);
        final Marker chongco = mMap.addMarker((chongco_marker));
        //**************************************************************arirang  *************************************
        MarkerOptions arirang_marker =  new MarkerOptions()
                .position( new LatLng(-35.185474, 149.13546))
                .draggable(false)
                .title("Arirang Restaurant ")
                .snippet("Korean cuisin ....")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.arirang))
                .flat(true);
        final Marker arirang = mMap.addMarker((arirang_marker));
        //**************************************************************goldenking  *************************************
        MarkerOptions goldenking_marker =  new MarkerOptions()
                .position( new LatLng(-35.353514, 149.089254))
                .draggable(false)
                .title("Golden King Restaurant ")
                .snippet("Chinese cuisin ....")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.goldenking))
                .flat(true);
        final Marker goldenking = mMap.addMarker((goldenking_marker));
        //**************************************************************istanbul  *************************************
        MarkerOptions istanbul_marker =  new MarkerOptions()
                .position( new LatLng(-35.415583, 149.067392))
                .draggable(false)
                .title("Istanbul Restaurant ")
                .snippet("Istanbul cuisin ....")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.istanbul))
                .flat(true);
        final Marker istanbul = mMap.addMarker((istanbul_marker));
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
                }if (marker.getId().equals(chongco.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.chongco, getTheme()));
                }if (marker.getId().equals(arirang.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.arirang, getTheme()));
                }if (marker.getId().equals(goldenking.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.goldenking, getTheme()));
                }if (marker.getId().equals(istanbul.getId())){
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.istanbul, getTheme()));
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
                }if ( marker.getId().equals(chongco.getId())) {
                    intent.putExtra("url", "http://www.chongcothai.com.au/belconnen.html");
                }if ( marker.getId().equals(arirang.getId())) {
                    intent.putExtra("url", "http://www.arirang.com.au/");
                }if ( marker.getId().equals(goldenking.getId())) {
                    intent.putExtra("url", "http://www.goldenkingchineserestaurantact.com/");
                }if ( marker.getId().equals(istanbul.getId())) {
                    intent.putExtra("url", "http://www.littleistanbul.com.au/");
                }

                startActivity(intent);
            }
        });
    }
}