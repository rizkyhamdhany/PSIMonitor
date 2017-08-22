package com.psi.monitor.models;

import android.content.Context;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public class PrefHelper extends SharedPreference {


    private static PrefHelper prefHelperInstance;
    //    private ManifestInfo manifestInfo;
    private Context context_;

    private static final String KEY_PSI_DATA = "psi_data";

    public static PrefHelper getInstance(Context context) {
        if (prefHelperInstance == null) {
            prefHelperInstance = new PrefHelper(context);
        }
        return prefHelperInstance;
    }

    public static PrefHelper getInstance() {
        if (prefHelperInstance != null) {
            return prefHelperInstance;
        }
        throw new IllegalArgumentException("Should use PrefHelper.getInstance(Context) at least once before using this method.");
    }

    private PrefHelper(Context context) {
        super(context);
        this.context_ = context;
    }

    public void setPsiData(String input) {
        put(KEY_PSI_DATA, input);
    }

    public String getPsiData() {
        return getString(KEY_PSI_DATA, "");
    }
}
