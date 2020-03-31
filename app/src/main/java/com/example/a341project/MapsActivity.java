package com.example.a341project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a341project.Driver.DriverOrdersPage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double longitude=49.887951;
    private double latitude=-119.496010;
    private double toLongitude=49.9423;
    private double toLatitude=-119.3960;
    private GoogleApiClient googleApiClient;
    public static TextView dis;
    Button button;
    Intent lastIntent;
    String statues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        lastIntent=getIntent();
        statues= lastIntent.getStringExtra("user");
        dis=(TextView)findViewById(R.id.MapDistance);
        button=(Button)findViewById(R.id.MapbuttonBack);
        googleApiClient=new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();
    }
    @Override
    protected void onStart(){
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop(){
        googleApiClient.disconnect();
        super.onStop();
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void OnClickBack(View view){
        if(statues!=null&&statues.equals("customer")){
        finish();}//if customer;
        else {
            Intent intent=new Intent(this, DriverOrdersPage.class);
            startActivity(intent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng middle = new LatLng(49.9162439,-119.4456999 );
        LatLng Driver= new LatLng(longitude,latitude);
        LatLng User =new LatLng(toLongitude,toLatitude);
        mMap.addMarker(new MarkerOptions().position(User).draggable(true).title("Customer's Location"));
        mMap.addMarker(new MarkerOptions().position(Driver).draggable(true).title("Driver's Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(middle));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));


        if(statues!=null&&statues.equals("customer")){
            button.setText("Back");
        }//if customer
        else{
            button.setText("arrive");
        }//else driver

        getDirection();
    }

    public String getURL(double sourcelat, double sourcelog, double destlat, double destlog){
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString
                .append(Double.toString( sourcelog));
        urlString.append("&destination=");// to
        urlString
                .append(Double.toString( destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        urlString.append("&key=AIzaSyCtwPZIgjhlM2F16CH2_10PuD2bvctVdOw");
        // Toast.makeText(this,urlString.toString(), Toast.LENGTH_SHORT).show();
        return urlString.toString();
    }

    private void getDirection(){
        String url=getURL(longitude,latitude,toLongitude,toLatitude);
        final ProgressDialog loading = ProgressDialog.show(this, "Getting Route", "Please wait...", false, false);
        StringRequest stringRequest=new StringRequest(url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                drawPath(response);
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        loading.dismiss();
                    }
                });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }//get direction

    public void drawPath(String result){

        LatLng from =new LatLng(longitude,latitude);
        LatLng to =new LatLng(toLongitude,toLatitude);
        Double distance= Math.round((SphericalUtil.computeDistanceBetween(from,to)/1000)*10)/10.0;

        if(statues!=null&&statues.equals("customer")){
            dis.setText(String.valueOf("The distance between you and the driver is: "+distance+"Km"));
        }//if customer
        else{
            String Cphone=lastIntent.getStringExtra("MapCphnoe");
            String Caddress=lastIntent.getStringExtra("MapCaddress");
            dis.setText("the distance between you and the customer is: "+distance+"Km\n"+"Customer address: "+Caddress+"\n"+"Customer phone number: "+Cphone);
        }//else driver





        try {
            //Parsing json
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .addAll(list)
                    .width(20)
                    .color(Color.RED)
                    .geodesic(true)
            );


        }
        catch (Exception e) {

        }

    }

    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }

}//class
