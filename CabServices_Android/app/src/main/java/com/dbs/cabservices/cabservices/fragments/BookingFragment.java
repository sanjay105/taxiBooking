package com.dbs.cabservices.cabservices.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.Request;
import com.dbs.cabservices.cabservices.Constants;
import com.dbs.cabservices.cabservices.LocationActivity;
import com.dbs.cabservices.cabservices.R;
import com.dbs.cabservices.cabservices.models.Cab;
import com.dbs.cabservices.cabservices.wsUtils.IResponseCallBack;
import com.dbs.cabservices.cabservices.wsUtils.WSHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    @Bind(R.id.btn_confirmbooking)
    Button cbTerms;
    SupportMapFragment mapFragment;
    String lat, lon;
    Location mLastLocation;

    private GoogleMap googleMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    GoogleApiClient mGoogleApiClient;
    List<Cab> cabs;
    private double currentLatitude;
    private double currentLongitude;




    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_booking3, container, false);
        ButterKnife.bind(this, view);



        //Log.v(BookingFragment.class.getSimpleName(),"check data"+String.valueOf(cabs.size()));


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        Log.d("OnmapREady fired", "fired");


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                googleMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            googleMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @OnClick(R.id.btn_confirmbooking)
    public void confirmBooking() {
       Intent intent= new Intent(getContext(), LocationActivity.class);
       intent.putExtra("selected",cabs.get(0));
        getActivity().startActivity(intent);
    }


    private List<Cab> getCars() {

        final List<Cab> cabs = new ArrayList<>();
        WSHelper.makeStringReq(getActivity(), Constants.GET_CABS, 1, new IResponseCallBack() {
            @Override
            public void onSuccess(int requestCode, String response) {

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Cab cab = new Cab();

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        cab.setId(jsonObject.getString("id"));
                        cab.setLon(jsonObject.getString("lon"));
                        cab.setLat(jsonObject.getString("lat"));
                        cab.setTaxiNo(jsonObject.getString("taxiNo"));
                        cab.setTaxiType(jsonObject.getString("taxiType"));
                        jsonObject.getString("Eco");
                        cab.setCarBrand(jsonObject.getString("carBrand"));
                        cabs.add(cab);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.v(BookingFragment.class.getSimpleName(),"check data"+String.valueOf(cabs.size()));

            }

            @Override
            public void onError(int requestCode, String error) {

            }
        }, Request.Method.GET);
        return cabs;
    };


    private List<Cab> getnearbyCars() {

         cabs = new ArrayList<>();

        Cab cab = new Cab();

        cab.setId("0");
        cab.setLon("17.4494674");
        cab.setLat("78.3768204");
        cab.setTaxiNo("SUL4194W");
        cab.setTaxiType("Eco");

        cab.setCarBrand("Kia");

        Cab cab1 = new Cab();

        cab1.setId("1");
        cab1.setLon("17.4508189");
        cab1.setLat("78.317237");
        cab1.setTaxiNo("SUL4194W");
        cab1.setTaxiType("Eco");

        cab1.setCarBrand("Kia");

        Cab cab2 = new Cab();

        cab2.setId("2");
        cab2.setLon("17.4474117");
        cab2.setLat("78.3768004");
        cab2.setTaxiNo("SBY3632X");
        cab2.setTaxiType("Standard");

        cab2.setCarBrand("Toyota");
        Cab cab3 = new Cab();

        cab3.setId("3");
        cab3.setLon("17.4947934");
        cab3.setLat("78.3996441");
        cab3.setTaxiNo("STR4843Y");
        cab3.setTaxiType("Standard");

        cab3.setCarBrand("Toyota");
        Cab cab4 = new Cab();

        cab4.setId("4");
        cab4.setLon("17.4264979");
        cab4.setLat("78.4511322");
        cab4.setTaxiNo("SIW1357B");
        cab4.setTaxiType("Eco");

        cab4.setCarBrand("Kia");


        Cab cab5 = new Cab();

        cab5.setId("5");
        cab5.setLon("17.4436497");
        cab5.setLat("78.4458259");
        cab5.setTaxiNo("SXM4417V");
        cab5.setTaxiType("Standard");

        cab5.setCarBrand("Toyota");



        cabs.add(cab);
        cabs.add(cab1);
        cabs.add(cab2);
        cabs.add(cab3);
        cabs.add(cab4);
        cabs.add(cab5);

        return cabs;
    };

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        List<Cab> cabs=getnearbyCars();



        latlngs.add(new LatLng(17.4436497, 33.43434));

        for(Cab cab:cabs) {
            options.position(new LatLng(Double.parseDouble(cab.getLon()), Double.parseDouble(cab.getLat())));
            options.title("someTitle");
            options.snippet("someDesc");
            googleMap.addMarker(options);
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(options.getPosition());
        LatLngBounds bounds = builder.build();

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());
            options.position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)));
            options.title("someTitle");
            options.snippet("someDesc");
            googleMap.addMarker(options);

        }
         googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,12));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,12));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
