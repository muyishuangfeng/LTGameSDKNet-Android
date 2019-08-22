package com.sdk.ltgame.ltnet.net;


import android.app.Activity;

import com.sdk.ltgame.ltnet.net.retrofit.RetrofitClient;

public class Api extends RetrofitClient {


    private Api(String baseUrl) {
        super(baseUrl);
    }

    /**
     * 单例
     *
     * @param BaseUrl url
     */
    public static RetrofitService getInstance(Activity activity,String BaseUrl) {
        return new Api(BaseUrl).getRetrofit(activity).create(RetrofitService.class);
    }


}
