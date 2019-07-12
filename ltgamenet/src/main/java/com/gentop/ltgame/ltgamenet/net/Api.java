package com.gentop.ltgame.ltgamenet.net;


import com.gentop.ltgame.ltgamenet.net.retrofit.RetrofitClient;

public class Api extends RetrofitClient {


    private Api(String baseUrl) {
        super(baseUrl);
    }

    /**
     * 单例
     *
     * @param BaseUrl url
     */
    public static RetrofitService getInstance(String BaseUrl) {
        return new Api(BaseUrl).getRetrofit().create(RetrofitService.class);
    }


}
