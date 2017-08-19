package com.psi.monitor.controllers;

import com.psi.monitor.controllers.apidata.PsiByDates;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface HttpService {

    @GET(EndPoint.psi)
    Call<PsiByDates.Response> psiByDates(
            @Query("date") String date,
            @Header("api-key") String apikey
    );

    @GET("v1/environment/psi")
    Call<PsiByDates.Response> psiByDateTimes(
            @Query("date_time") String date,
            @Header("api-key") String apikey
    );

}
