package com.psi.monitor.controllers;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public interface OnCallAPI {
    public void onAPIsuccess();
    public void onAPIFailed(String errorMessage);
    public void execute();
}
