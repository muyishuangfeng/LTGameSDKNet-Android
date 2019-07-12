package com.gentop.ltgame.ltgamenet.manager;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.gentop.ltgame.ltgamenet.base.Constants;
import com.gentop.ltgame.ltgamenet.net.Api;
import com.gentop.ltgame.ltgamenet.util.MD5Util;
import com.gentop.ltgame.ltgamenet.util.PreferencesUtils;
import com.gentop.ltgame.ltgamesdkcore.base.BaseEntry;
import com.gentop.ltgame.ltgamesdkcore.common.LTGameOptions;
import com.gentop.ltgame.ltgamesdkcore.common.LTGameSdk;
import com.gentop.ltgame.ltgamesdkcore.exception.LTGameError;
import com.gentop.ltgame.ltgamesdkcore.impl.OnLoginStateListener;
import com.gentop.ltgame.ltgamesdkcore.impl.OnRechargeListener;
import com.gentop.ltgame.ltgamesdkcore.model.LoginResult;
import com.gentop.ltgame.ltgamesdkcore.model.RechargeResult;
import com.gentop.ltgame.ltgamesdkcore.model.ResultModel;
import com.google.gson.Gson;

import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class LoginRealizeManager {


    /**
     * Google登录
     *
     * @param idToken   google返回的Token
     * @param mListener 接口回调
     */
    public static void googleLogin(final Context context, String idToken,
                                   final OnLoginStateListener mListener) {
        LTGameOptions options = LTGameSdk.options();
        if (!TextUtils.isEmpty(options.getLtAppId()) &&
                !TextUtils.isEmpty(options.getLtAppKey()) &&
                !TextUtils.isEmpty(options.getBaseUrl()) &&
                !TextUtils.isEmpty(options.getPackageID()) &&
                !TextUtils.isEmpty(options.getAdID())) {
            long LTTime = System.currentTimeMillis() / 1000L;
            String LTToken = MD5Util.md5Decode("POST" + options.getLtAppId() + LTTime + options.getLtAppKey());
            Map<String, Object> map = new WeakHashMap<>();
            map.put("access_token", idToken);
            map.put("platform", 2);
            map.put("adid", "");
            map.put("gps_adid", options.getAdID());
            map.put("platform_id", options.getPackageID());

            Api.getInstance(options.getBaseUrl())
                    .googleLogin(options.getLtAppId(), LTToken, (int) LTTime, map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseEntry<ResultModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseEntry<ResultModel> result) {
                            if (result != null) {
                                if (result.getCode() == 200) {
                                    if (mListener != null) {
                                        mListener.onState((Activity) context, LoginResult.successOf(result));
                                    }
                                    if (!TextUtils.isEmpty(result.getData().getApi_token())) {
                                        PreferencesUtils.putString(context, Constants.USER_API_TOKEN,
                                                result.getData().getApi_token());
                                    }
                                    if (!TextUtils.isEmpty(result.getData().getLt_uid())) {
                                        PreferencesUtils.putString(context, Constants.USER_LT_UID,
                                                result.getData().getLt_uid());
                                    }
                                    if (!TextUtils.isEmpty(result.getData().getLt_uid_token())) {
                                        PreferencesUtils.putString(context, Constants.USER_LT_UID_TOKEN,
                                                result.getData().getLt_uid_token());
                                    }
                                } else {
                                    if (mListener != null) {
                                        mListener.onState((Activity) context,
                                                LoginResult.failOf(LTGameError.make(LTGameError.CODE_COMMON_ERROR, result.getMsg())));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            if (mListener != null) {
                                mListener.onState((Activity) context,
                                        LoginResult.completeOf(LTGameError.CODE_COMPLETE));
                            }
                        }
                    });
        }
    }


    /**
     * facebook登录
     *
     * @param accessToken facebook返回的Token
     * @param mListener   接口回调
     */
    public static void facebookLogin(final Context context, String accessToken,
                                     final OnLoginStateListener mListener) {
        LTGameOptions options = LTGameSdk.options();
        if (!TextUtils.isEmpty(options.getLtAppId()) &&
                !TextUtils.isEmpty(options.getLtAppKey()) &&
                !TextUtils.isEmpty(options.getAdID()) &&
                !TextUtils.isEmpty(options.getPackageID())) {
            long LTTime = System.currentTimeMillis() / 1000L;
            String LTToken = MD5Util.md5Decode("POST" + options.getLtAppId() + LTTime + options.getLtAppKey());
            Map<String, Object> map = new WeakHashMap<>();
            map.put("access_token", accessToken);
            map.put("adid", "");
            map.put("gps_adid", options.getAdID());
            map.put("platform_id", options.getPackageID());
            Api.getInstance(options.getBaseUrl())
                    .faceBookLogin(options.getLtAppId(), LTToken, (int) LTTime, map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseEntry<ResultModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseEntry<ResultModel> result) {
                            if (result != null) {
                                if (result.getCode() == 200) {
                                    if (mListener != null) {
                                        mListener.onState((Activity) context, LoginResult.successOf(result));
                                    }
                                    if (!TextUtils.isEmpty(result.getData().getApi_token())) {
                                        PreferencesUtils.putString(context, Constants.USER_API_TOKEN,
                                                result.getData().getApi_token());
                                    }
                                    if (!TextUtils.isEmpty(result.getData().getLt_uid())) {
                                        PreferencesUtils.putString(context, Constants.USER_LT_UID,
                                                result.getData().getLt_uid());
                                    }
                                    if (!TextUtils.isEmpty(result.getData().getLt_uid_token())) {
                                        PreferencesUtils.putString(context, Constants.USER_LT_UID_TOKEN,
                                                result.getData().getLt_uid_token());
                                    }
                                } else {
                                    if (mListener != null) {
                                        mListener.onState((Activity) context,
                                                LoginResult.failOf(LTGameError.make(LTGameError.CODE_COMMON_ERROR,
                                                        result.getMsg())));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            if (mListener != null) {
                                mListener.onState((Activity) context,
                                        LoginResult.completeOf(LTGameError.CODE_COMPLETE));
                            }
                        }
                    });
        }
    }


    /**
     * 创建订单
     */
    public static void createOrder(final Activity context, String mProductID, Map<String, Object> map,
                                   final OnRechargeListener mListener) {
        LTGameOptions options = LTGameSdk.options();
        if (map != null &&
                !TextUtils.isEmpty(options.getLtAppId()) &&
                !TextUtils.isEmpty(options.getPackageID()) &&
                !TextUtils.isEmpty(options.getBaseUrl()) &&
                !TextUtils.isEmpty(options.getLtAppKey())) {
            Map<String, Object> params = new WeakHashMap<>();
            params.put("package_id", options.getPackageID());
            params.put("gid", mProductID);
            params.put("custom", map);

            long LTTime = System.currentTimeMillis() / 1000L;
            String LTToken = MD5Util.md5Decode("POST" + options.getLtAppId() + LTTime
                    + options.getLtAppKey());
            String json = new Gson().toJson(params);//要传递的json
            final RequestBody requestBody = RequestBody
                    .create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
            if (!TextUtils.isEmpty(PreferencesUtils.getString(context, Constants.USER_API_TOKEN))) {
                String authorization = "Bearer " + PreferencesUtils.getString(context, Constants.USER_API_TOKEN);
                Api.getInstance(options.getBaseUrl())
                        .createOrder(options.getLtAppId(), LTToken, (int) LTTime, authorization,
                                requestBody)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BaseEntry<ResultModel>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(BaseEntry<ResultModel> result) {
                                if (result != null) {
                                    if (result.getCode() == 200) {
                                        if (result.getData().getLt_order_id() != null) {
                                            mListener.onState(context, RechargeResult.successOf(result));
                                        }
                                    } else {
                                        mListener.onState(context, RechargeResult.failOf(result));
                                    }
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                mListener.onState(context, RechargeResult.failOf(e.getMessage()));
                            }

                            @Override
                            public void onComplete() {
                            }
                        });
            } else {
                mListener.onState(context, RechargeResult.failOf("tokenError"));
            }


        }
    }


    /**
     * google
     */
    public static void googlePlay(final Context context, String purchaseToken, String orderID,
                                  int mPayTest, final OnRechargeListener mListener) {
        LTGameOptions options = LTGameSdk.options();
        if (!TextUtils.isEmpty(options.getBaseUrl()) &&
                !TextUtils.isEmpty(purchaseToken) &&
                !TextUtils.isEmpty(orderID) &&
                !TextUtils.isEmpty(options.getLtAppId()) &&
                !TextUtils.isEmpty(options.getLtAppKey())) {
            long LTTime = System.currentTimeMillis() / 1000L;
            String LTToken = MD5Util.md5Decode("POST" + options.getLtAppId() + LTTime
                    + options.getLtAppKey());
            Map<String, Object> params = new WeakHashMap<>();
            params.put("purchase_token", purchaseToken);
            params.put("lt_order_id", orderID);
            params.put("pay_test", mPayTest);

            String json = new Gson().toJson(params);//要传递的json
            final RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
            Api.getInstance(options.getBaseUrl())
                    .googlePlay(options.getLtAppId(), LTToken, (int) LTTime, requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseEntry<ResultModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseEntry<ResultModel> result) {
                            if (result != null) {
                                mListener.onState((Activity) context, RechargeResult.successOf(result));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    /**
     * oneStore
     */
    public static void oneStorePlay(final Context context, String purchaseToken, String orderID,
                                    int mPayTest, final OnRechargeListener mListener) {
        LTGameOptions options = LTGameSdk.options();
        if (!TextUtils.isEmpty(options.getLtAppId()) &&
                !TextUtils.isEmpty(options.getLtAppKey()) &&
                !TextUtils.isEmpty(orderID) &&
                !TextUtils.isEmpty(purchaseToken) &&
                mPayTest != -1) {
            long LTTime = System.currentTimeMillis() / 1000L;
            String LTToken = MD5Util.md5Decode("POST" + options.getLtAppId() + LTTime + options.getLtAppKey());
            Map<String, Object> params = new WeakHashMap<>();
            params.put("purchase_id", purchaseToken);
            params.put("lt_order_id", orderID);
            params.put("pay_test", mPayTest);

            String json = new Gson().toJson(params);//要传递的json
            final RequestBody requestBody = RequestBody.create(okhttp3.MediaType
                    .parse("application/json; charset=utf-8"), json);
            Api.getInstance(options.getBaseUrl())
                    .oneStorePlay(options.getLtAppId(), LTToken, (int) LTTime, requestBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BaseEntry<ResultModel>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BaseEntry<ResultModel> result) {
                            if (result != null) {
                                mListener.onState((Activity) context, RechargeResult.successOf(result));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
