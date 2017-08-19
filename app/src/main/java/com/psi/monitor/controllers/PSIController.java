package com.psi.monitor.controllers;

import com.psi.monitor.controllers.apidata.PsiByDates;

/**
 * Created by hamdhanywijaya@gmail.com on 8/16/17.
 */

public class PSIController extends BaseController<PsiByDates.Response> {

    private String date;
    private enum Type{
        DATETIME, DATE
    }
    private Type type;

    public PSIController(String date, Type type) {
        this.type = type;
    }

    public PSIController(){

    }

    @Override
    public void onAPIsuccess() {

    }

    @Override
    public void onAPIFailed(String errorMessage) {

    }

    @Override
    public void execute() {
        if (date == null){

        }
        if (type == Type.DATETIME){
            getService().psiByDateTimes("2017-08-20", "3kkVzCyHlj7mbPmwRhyYcK9RdGx4ny4h").enqueue(callback);
        } else{
            getService().psiByDates("2017-08-20", "3kkVzCyHlj7mbPmwRhyYcK9RdGx4ny4h").enqueue(callback);
        }
    }
}
