package com.psi.monitor.views.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.psi.monitor.R;
import com.psi.monitor.controllers.apidata.PsiByDates;
import com.psi.monitor.controllers.apidata.entities.RegionMetadatum;
import com.psi.monitor.models.PrefHelper;

public class PSIMapsFragment extends BaseFragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private Bitmap markerIcon;
    private PsiByDates.Response data;

    @Override
    public void initView(View view) {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.pollutan_icon);
        Bitmap b = bitmapdraw.getBitmap();
        markerIcon = Bitmap.createScaledBitmap(b, 50, 50, false);
        data = new PsiByDates.Response().fromJson(PrefHelper.getInstance(context).getPsiData());
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
        for (RegionMetadatum region : data.getRegionMetadata()){
            googleMap.addMarker(markerBuilder(region.getLabelLocation().getLatitude(), region.getLabelLocation().getLongitude(), region.getName()));
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.3402161, 103.8461785), 10.5f));
        googleMap.setOnMarkerClickListener(onMarkerClickListener);
    }

    private MarkerOptions markerBuilder(double lat, double lon, String title){
        LatLng location = new LatLng(lat, lon);
        return new MarkerOptions().position(location).title(title).icon(BitmapDescriptorFactory.fromBitmap(markerIcon));
    }

    private GoogleMap.OnMarkerClickListener onMarkerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {
            showDetail(marker.getTitle());
            return true;
        }
    };

    private void showDetail(String title){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Readings:");
        alertDialog.setMessage(getDetailData(title));
        alertDialog.show();
    }

    private String getDetailData(String title){
        String detail = "";

        if (title.equals("west")){
            detail += "o3_sub_index: " + data.getItems().get(0).getReadings().getO3SubIndex().getWest() +"\n";
            detail += "pm10_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm10TwentyFourHourly().getWest() +"\n";
            detail += "pm10_sub_index: " + data.getItems().get(0).getReadings().getPm10SubIndex().getWest() +"\n";
            detail += "co_sub_index: " + data.getItems().get(0).getReadings().getCoSubIndex().getWest() +"\n";
            detail += "pm25_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm25TwentyFourHourly().getWest() +"\n";
            detail += "so2_sub_index: " + data.getItems().get(0).getReadings().getSo2SubIndex().getWest() +"\n";
            detail += "co_eight_hour_max: " + data.getItems().get(0).getReadings().getCoEightHourMax().getWest() +"\n";
            detail += "so2_twenty_four_hourly: " + data.getItems().get(0).getReadings().getSo2TwentyFourHourly().getWest() +"\n";
            detail += "pm25_sub_index: " + data.getItems().get(0).getReadings().getPm25SubIndex().getWest() +"\n";
            detail += "psi_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPsiTwentyFourHourly().getWest() +"\n";
            detail += "o3_eight_hour_max: " + data.getItems().get(0).getReadings().getO3EightHourMax().getWest() +"\n";
        } else if (title.equals("east")){
            detail += "o3_sub_index: " + data.getItems().get(0).getReadings().getO3SubIndex().getEast() +"\n";
            detail += "pm10_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm10TwentyFourHourly().getEast() +"\n";
            detail += "pm10_sub_index: " + data.getItems().get(0).getReadings().getPm10SubIndex().getEast() +"\n";
            detail += "co_sub_index: " + data.getItems().get(0).getReadings().getCoSubIndex().getEast() +"\n";
            detail += "pm25_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm25TwentyFourHourly().getEast() +"\n";
            detail += "so2_sub_index: " + data.getItems().get(0).getReadings().getSo2SubIndex().getEast() +"\n";
            detail += "co_eight_hour_max: " + data.getItems().get(0).getReadings().getCoEightHourMax().getEast() +"\n";
            detail += "so2_twenty_four_hourly: " + data.getItems().get(0).getReadings().getSo2TwentyFourHourly().getEast() +"\n";
            detail += "pm25_sub_index: " + data.getItems().get(0).getReadings().getPm25SubIndex().getEast() +"\n";
            detail += "psi_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPsiTwentyFourHourly().getEast() +"\n";
            detail += "o3_eight_hour_max: " + data.getItems().get(0).getReadings().getO3EightHourMax().getEast() +"\n";
        } else if (title.equals("north")){
            detail += "o3_sub_index: " + data.getItems().get(0).getReadings().getO3SubIndex().getNorth() +"\n";
            detail += "pm10_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm10TwentyFourHourly().getNorth() +"\n";
            detail += "pm10_sub_index: " + data.getItems().get(0).getReadings().getPm10SubIndex().getNorth() +"\n";
            detail += "co_sub_index: " + data.getItems().get(0).getReadings().getCoSubIndex().getNorth() +"\n";
            detail += "pm25_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm25TwentyFourHourly().getNorth() +"\n";
            detail += "so2_sub_index: " + data.getItems().get(0).getReadings().getSo2SubIndex().getNorth() +"\n";
            detail += "co_eight_hour_max: " + data.getItems().get(0).getReadings().getCoEightHourMax().getNorth() +"\n";
            detail += "so2_twenty_four_hourly: " + data.getItems().get(0).getReadings().getSo2TwentyFourHourly().getNorth() +"\n";
            detail += "pm25_sub_index: " + data.getItems().get(0).getReadings().getPm25SubIndex().getNorth() +"\n";
            detail += "psi_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPsiTwentyFourHourly().getNorth() +"\n";
            detail += "o3_eight_hour_max: " + data.getItems().get(0).getReadings().getO3EightHourMax().getNorth() +"\n";
        } else if (title.equals("south")){
            detail += "o3_sub_index: " + data.getItems().get(0).getReadings().getO3SubIndex().getSouth() +"\n";
            detail += "pm10_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm10TwentyFourHourly().getSouth() +"\n";
            detail += "pm10_sub_index: " + data.getItems().get(0).getReadings().getPm10SubIndex().getSouth() +"\n";
            detail += "co_sub_index: " + data.getItems().get(0).getReadings().getCoSubIndex().getSouth() +"\n";
            detail += "pm25_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm25TwentyFourHourly().getSouth() +"\n";
            detail += "so2_sub_index: " + data.getItems().get(0).getReadings().getSo2SubIndex().getSouth() +"\n";
            detail += "co_eight_hour_max: " + data.getItems().get(0).getReadings().getCoEightHourMax().getSouth() +"\n";
            detail += "so2_twenty_four_hourly: " + data.getItems().get(0).getReadings().getSo2TwentyFourHourly().getSouth() +"\n";
            detail += "pm25_sub_index: " + data.getItems().get(0).getReadings().getPm25SubIndex().getSouth() +"\n";
            detail += "psi_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPsiTwentyFourHourly().getSouth() +"\n";
            detail += "o3_eight_hour_max: " + data.getItems().get(0).getReadings().getO3EightHourMax().getSouth() +"\n";
        } else if (title.equals("central")){
            detail += "o3_sub_index: " + data.getItems().get(0).getReadings().getO3SubIndex().getCentral() +"\n";
            detail += "pm10_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm10TwentyFourHourly().getCentral() +"\n";
            detail += "pm10_sub_index: " + data.getItems().get(0).getReadings().getPm10SubIndex().getCentral() +"\n";
            detail += "co_sub_index: " + data.getItems().get(0).getReadings().getCoSubIndex().getCentral() +"\n";
            detail += "pm25_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPm25TwentyFourHourly().getCentral() +"\n";
            detail += "so2_sub_index: " + data.getItems().get(0).getReadings().getSo2SubIndex().getCentral() +"\n";
            detail += "co_eight_hour_max: " + data.getItems().get(0).getReadings().getCoEightHourMax().getCentral() +"\n";
            detail += "so2_twenty_four_hourly: " + data.getItems().get(0).getReadings().getSo2TwentyFourHourly().getCentral() +"\n";
            detail += "pm25_sub_index: " + data.getItems().get(0).getReadings().getPm25SubIndex().getCentral() +"\n";
            detail += "psi_twenty_four_hourly: " + data.getItems().get(0).getReadings().getPsiTwentyFourHourly().getCentral() +"\n";
            detail += "o3_eight_hour_max: " + data.getItems().get(0).getReadings().getO3EightHourMax().getCentral() +"\n";
        }
        return detail;
    }
}
