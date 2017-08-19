package com.psi.monitor.controllers.apidata.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 * Created by hamdhanywijaya@gmail.com on 8/16/17.
 */

public class PsiByDate {

    @SerializedName("region_metadata")
    @Expose
    private ArrayList<RegionMetadatum> regionMetadata = new ArrayList<>();
    @SerializedName("items")
    @Expose
    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<RegionMetadatum> getRegionMetadata() {
        return regionMetadata;
    }

    public void setRegionMetadata(ArrayList<RegionMetadatum> regionMetadata) {
        this.regionMetadata = regionMetadata;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

}
