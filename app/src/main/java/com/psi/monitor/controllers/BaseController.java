package com.psi.monitor.controllers;

import android.content.pm.PackageInfo;
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

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    private static final String API_BASE_URL = "";
    private static final String API_STAGGING_BASE_URL = "";
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
            client.addInterceptor(headerInterceptor);
            return client;
        }

        private Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
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

    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            PackageInfo pinfo;
            try {

                //ET2.setText(versionNumber);
            } catch (Exception e) {

            }
            Request.Builder builder = chain.request().newBuilder()
                    .addHeader("coordinate", "1.2,2.1")
                    .addHeader("lang", "en")
//                    .addHeader("version", VERSION_NAME)
//                    .addHeader("build", String.valueOf(VERSION_CODE))
                    .addHeader("platform", "android"+android.os.Build.VERSION.RELEASE);
            if (token != null){
                builder.addHeader("token" , token);
            }
            Request request = builder.build();
            return chain.proceed(request);
        }
    };

    protected String postData(Object data){
        Gson gson = (new GsonBuilder().disableHtmlEscaping().serializeNulls().create());
        return gson.toJson(data);
    }

}
