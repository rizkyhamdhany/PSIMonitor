package com.psi.monitor.controllers;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.psi.monitor.controllers.apidata.ApiData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hamdhanywijaya@gmail.com on 8/10/17.
 */

public abstract class BaseController<T> implements OnCallAPI {

    protected T data;
    protected static String token, buildNo;
    protected Map<String, Object> getParams = new HashMap<>();
    protected String serverDate;

    public BaseController() {
    }

    public HttpService getService() {
        return new ServiceGenerator().service();
    }

    public HttpService getService(String baseUrl) {
        return new ServiceGenerator().service(baseUrl);
    }

    protected retrofit2.Callback<T> callback = new Callback<T>() {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                try {
                    data = response.body();
                } catch (Exception ignored) {
                    onAPIFailed(((ApiData<String>) response.body()).message);
                }
                try {
                    serverDate = response.headers().get("Date");
                } catch (Exception e){

                }

                onAPIsuccess();
            } else {
                onAPIFailed(getErrorMessage(response.errorBody().charStream()));
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            onAPIFailed("The internet connection appears to be offline");
        }
    };


    public class ServiceGenerator {

        private OkHttpClient.Builder httpClient() {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder client = new OkHttpClient.Builder();

            client.readTimeout(30, TimeUnit.SECONDS);
            client.connectTimeout(30, TimeUnit.SECONDS);
//            if (BuildConfig.DEBUGMODE == 1){
                client.addInterceptor(logging);
//            }
            return client;
        }

        private Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(EndPoint.getApiBaseUri())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient().build());

        public HttpService service() {
            return retrofit.build().create(HttpService.class);
        }

        public HttpService service(String baseUrl) {
            retrofit = new Retrofit.Builder()
                    .client(httpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl);
            return retrofit.build().create(HttpService.class);
        }
    }

    private String getErrorMessage(Reader reader) {
        String errorMessage = "";
        int intValueOfChar;
        String targetString = "";
        try {
            while ((intValueOfChar = reader.read()) != -1) {
                targetString += (char) intValueOfChar;
            }
            Log.d("Error Message", targetString);
            JSONObject jsonObject = new JSONObject(targetString);
            errorMessage = jsonObject.getString("message");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return errorMessage;
    }

    protected String postData(Object data){
        Gson gson = (new GsonBuilder().disableHtmlEscaping().serializeNulls().create());
        return gson.toJson(data);
    }

}
