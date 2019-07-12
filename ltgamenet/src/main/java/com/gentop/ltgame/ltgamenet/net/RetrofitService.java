package com.gentop.ltgame.ltgamenet.net;



import com.gentop.ltgame.ltgamenet.model.WeChatModel;
import com.gentop.ltgame.ltgamenet.model.bean.AliPlayBean;
import com.gentop.ltgame.ltgamenet.model.bean.WeChatBean;
import com.gentop.ltgame.ltgamesdkcore.base.BaseEntry;
import com.gentop.ltgame.ltgamesdkcore.model.ResultModel;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    /**
     * 阿里
     */
    @POST("/")
    Observable<AliPlayBean> aliPlay(@QueryMap WeakHashMap<String, String> map);

    /**
     * 微信
     */
    @POST("/")
    Observable<WeChatBean> weChatPlay(@QueryMap WeakHashMap<String, String> map);

    /**
     * 微信登录
     */
    @POST
    Observable<WeChatModel> getWeChatInfo(@Query("appid") String appid, @Query("secret") String secret,
                                          @Query("code") String code, @Query("grant_type") String grant_type);


    /**
     * 获取验证码
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @GET("/api/auth/send-code")
    Observable<BaseEntry<ResultModel>> getAuthenCode(@Header("LT-AppID") String LTAppID,
                                                     @Header("LT-Token") String LTToken,
                                                     @Header("LT-T") int LTTime,
                                                     @Body Map<String, Object> map);


    /**
     * 注册
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/register-phone")
    Observable<BaseEntry<ResultModel>> register(@Header("LT-AppID") String LTAppID,
                                                @Header("LT-Token") String LTToken,
                                                @Header("LT-T") int LTTime,
                                                @Body Map<String, Object> map);

    /**
     * 获取设备信息
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/other/join")
    Observable<BaseEntry<ResultModel>> getDeviceInfo(@Header("LT-AppID") String LTAppID,
                                                     @Header("LT-Token") String LTToken,
                                                     @Header("LT-T") int LTTime,
                                                     @Body Map<String, Object> map);

    /**
     * 登录
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/login-phone")
    Observable<BaseEntry<ResultModel>> login(@Header("LT-AppID") String LTAppID,
                                             @Header("LT-Token") String LTToken,
                                             @Header("LT-T") int LTTime,
                                             @Body Map<String, Object> map);

    /**
     * 更改密码
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/reset-password")
    Observable<BaseEntry<ResultModel>> updatePassword(@Header("LT-AppID") String LTAppID,
                                                      @Header("LT-Token") String LTToken,
                                                      @Header("LT-T") int LTTime,
                                                      @Body Map<String, Object> map);

    /**
     * google登录
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/login-google")
    Observable<BaseEntry<ResultModel>> googleLogin(@Header("LT-AppID") String LTAppID,
                                                   @Header("LT-Token") String LTToken,
                                                   @Header("LT-T") int LTTime,
                                                   @Body Map<String, Object> map);

    /**
     * facebook登录
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/login-facebook")
    Observable<BaseEntry<ResultModel>> faceBookLogin(@Header("LT-AppID") String LTAppID,
                                                     @Header("LT-Token") String LTToken,
                                                     @Header("LT-T") int LTTime,
                                                     @Body Map<String, Object> map);

    /**
     * 微信登录
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/login-wechat")
    Observable<BaseEntry<ResultModel>> weChatLogin(@Header("LT-AppID") String LTAppID,
                                                   @Header("LT-Token") String LTToken,
                                                   @Header("LT-T") int LTTime,
                                                   @Body Map<String, Object> map);

    /**
     * qq登录
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/login-qq")
    Observable<BaseEntry<ResultModel>> qqLogin(@Header("LT-AppID") String LTAppID,
                                               @Header("LT-Token") String LTToken,
                                               @Header("LT-T") int LTTime,
                                               @Body Map<String, Object> map);


    /**
     * 创建订单
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/p/order")
    Observable<BaseEntry<ResultModel>> createOrder(@Header("LT-AppID") String LTAppID,
                                                   @Header("LT-Token") String LTToken,
                                                   @Header("LT-T") int LTTime,
                                                   @Body RequestBody requestBody);

    /**
     * 创建订单
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/p/order")
    Observable<BaseEntry<ResultModel>> createOrder(@Header("LT-AppID") String LTAppID,
                                                   @Header("LT-Token") String LTToken,
                                                   @Header("LT-T") int LTTime,
                                                   @Header("Authorization") String Authorization,
                                                   @Body RequestBody requestBody);

    /**
     * google
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/p/google")
    Observable<BaseEntry<ResultModel>> googlePlay(@Header("LT-AppID") String LTAppID,
                                                  @Header("LT-Token") String LTToken,
                                                  @Header("LT-T") int LTTime,
                                                  @Body RequestBody requestBody);

    /**
     * oneStore
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/p/one-store")
    Observable<BaseEntry<ResultModel>> oneStorePlay(@Header("LT-AppID") String LTAppID,
                                                    @Header("LT-Token") String LTToken,
                                                    @Header("LT-T") int LTTime,
                                                    @Body RequestBody requestBody);

    /**
     * 自动登录验证
     */
    @Headers({"Content-Type:application/json",
            "Accept:application/json"})
    @POST("/api/auth/login-check")
    Observable<BaseEntry> autoLogin(@Header("LT-AppID") String LTAppID,
                                    @Header("LT-Token") String LTToken,
                                    @Header("LT-T") int LTTime,
                                    @Body RequestBody requestBody);
}
