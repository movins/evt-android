package com.github.movins.evt.excute;

public interface ExcuteCallback<T> {
    void onSuccess(T data);

    void onFailed(int code);

    void onException(Throwable exception);
}