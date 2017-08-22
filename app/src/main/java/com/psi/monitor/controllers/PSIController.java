package com.psi.monitor.controllers;

import android.content.Context;
import com.psi.monitor.controllers.apidata.PsiByDates;
import com.psi.monitor.models.PrefHelper;

/**
 * Created by hamdhanywijaya@gmail.com on 8/16/17.
 */

public class PSIController extends BaseController<PsiByDates.Response> {

    private Context context;
    private String date;

    public PSIController(Context context, String date) {
        this.context = context;
        this.date = date;
    }
    public PSIController(String date){
        this.date = date;
    }

    @Override
    public void onAPIsuccess() {
        if (context != null){
            PrefHelper.getInstance(context).setPsiData(data.toString());
        }
    }

    @Override
    public void onAPIFailed(String errorMessage) {

    }

    @Override
    public void execute() {
        getService().psiByDates(date, "3kkVzCyHlj7mbPmwRhyYcK9RdGx4ny4h").enqueue(callback);
    }
}
