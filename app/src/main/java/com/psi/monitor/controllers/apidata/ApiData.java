package com.psi.monitor.controllers.apidata;

/**
 * Created by ebizu-rizky on 5/21/16.
 */

public class ApiData<T> {

    public int status;
    public String message;
    public T data;
    public String tracked_at;
    public Meta meta;
    public long elapsed;

    public ApiData() {

    }

    public class Meta{
        public int total_page;
        public int total_data;
        public int current_page;
        public int next_page;
        public int size_per_page;
        public String more;
    }

}
