package com.github.movins.evt.excute;

public abstract class ExcuteCallbackWrapper<T> implements ExcuteCallback<T> {
    public ExcuteCallbackWrapper() {
    }

    public abstract void onResult(int code, T data, Throwable error);

    public void onSuccess(T data) {
        this.onResult(200, data, null);
    }

    public void onFailed(int code) {
        this.onResult(code, null, null);
    }

    public void onException(Throwable error) {
        this.onResult(1000, null, error);
    }
}