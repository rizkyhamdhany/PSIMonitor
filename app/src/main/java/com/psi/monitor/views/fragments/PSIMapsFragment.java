package com.psi.monitor.views.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.psi.monitor.R;

public class PSIMapsFragment extends BaseFragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private Bitmap markerIcon;

    @Override
    public void initView(View view) {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.pollutan_icon);
        Bitmap b = bitmapdraw.getBitmap();
        markerIcon = Bitmap.createScaledBitmap(b, 100, 100, false);
    }

    @Override
    public void setUICallbacks() {

    }

    @Override
    public void updateUI() {

    }

    @Override
    public String getPageTitle() {
        return null;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_psi_map;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(1.3402161, 103.8461785);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromBitmap(markerIcon)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.5f));
    }
}
