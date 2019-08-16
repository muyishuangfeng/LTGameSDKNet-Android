package com.sdk.ltgame.ltnet.impl;

public interface OnAutoCheckLoginListener {

    void onCheckedSuccess(String result);

    void onCheckedFailed(String failed);
}
