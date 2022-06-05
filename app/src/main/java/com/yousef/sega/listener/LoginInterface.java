package com.yousef.sega.listener;

public interface LoginInterface {
    void onSuccess();
    void onFailure(Exception e);

    void onSuccessReset();
    void onFailureReset(Exception e);
}
