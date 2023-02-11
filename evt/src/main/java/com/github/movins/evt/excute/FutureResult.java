package com.github.movins.evt.excute;

public class FutureResult<T> implements ExcuteFuture<T> {
    protected ExcuteCallback<T> callback;

    @Override
    public void setCallback(ExcuteCallback<T> callback) {
        this.callback = callback;
    }

    public void success(T data) {
        if (this.callback != null) {
            this.callback.onSuccess(data);
        }
    }

    public void failed(int code) {
        if (this.callback != null) {
            this.callback.onFailed(code);
        }
    }

    public void exception(Throwable error) {
        if (this.callback != null) {
            this.callback.onException(error);
        }
    }
}