package com.github.movins.evt.excute;

public interface ExcuteFuture<T> {
    void setCallback(ExcuteCallback<T> callback);
}