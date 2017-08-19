package com.psi.monitor.controllers.apidata;

import com.google.gson.Gson;
import com.psi.monitor.controllers.apidata.entities.PsiByDate;

/**
 * Created by hamdhanywijaya@gmail.com on 8/16/17.
 */

public class PsiByDates {

    public static class Response extends PsiByDate {

        public static Response fromJson(String s) {
            return new Gson().fromJson(s, Response.class);
        }
        public String toString() {
            return new Gson().toJson(this);
        }

    }

}
