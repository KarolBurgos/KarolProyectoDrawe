package com.example.sistemas.karolproyectoandroid;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Marker marcador;
    public double lat = 0.0;
    public double lon = 0.0;
    private TextView titulo_lugar1,titulo_n,titulo_c,hora_c;

    public MapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mapa, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //ub();
        Intent intent = getIntent();
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        int lugar = bundle.getInt("lugar");
        String titulo = bundle.getString("titulo_l");
        String titulon = bundle.getString("titulo_n");
        String tituloc = bundle.getString("titulo_c");
        String hora = bundle.getString("hora_c");
        titulo_lugar1.setText(titulo);
        titulo_n.setText(titulon);
        titulo_c.setText(tituloc);
        hora_c.setText(hora);
        switch (lugar) {
            case 1:

                LatLng mariana = new LatLng(1.2239108,-77.2832273);
                mMap.addMarker(new MarkerOptions().position(mariana).title("Auditorio MADRE CARIDAD"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mariana, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                mMap.setMyLocationEnabled(true);

                break;
            case 2:

                LatLng cesmag = new LatLng(1.2084025,-77.2790893);
                mMap.addMarker(new MarkerOptions().position(cesmag).title("Auditorio SAN FRANCISCO"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cesmag, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);


                break;
            case 3:
                LatLng udenar = new LatLng(1.232085, -77.293245);
                mMap.addMarker(new MarkerOptions().position(udenar).title("Auditorio LUIS SANTANDER"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(udenar, 15));
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setZoomGesturesEnabled(true);
                mMap.setMapType(googleMap.MAP_TYPE_NORMAL);

                break;
        }
    }

    public void agregarmark(double lat, double lon) {
        LatLng coor = new LatLng(lat, lon);
        CameraUpdate miubicacion = CameraUpdateFactory.newLatLngZoom(coor, 16);
        if (marcador != null) marcador.remove();
        {
            marcador = mMap.addMarker(new MarkerOptions().position(coor).title("tu estas aqui"));
            mMap.animateCamera(miubicacion);
        }
    }

    public void actualizarubicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarmark(lat, lon);

        }
    }


}
