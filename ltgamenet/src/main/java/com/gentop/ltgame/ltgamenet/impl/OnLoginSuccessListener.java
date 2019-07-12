package com.gentop.ltgame.ltgamenet.impl;


import com.gentop.ltgame.ltgamesdkcore.base.BaseEntry;

public interface OnLoginSuccessListener<T> {

    void onSuccess(BaseEntry<T> result);

    void onFailed(BaseEntry<T> failed);
}
