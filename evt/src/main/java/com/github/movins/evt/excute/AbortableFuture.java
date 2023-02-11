package com.github.movins.evt.excute;

public interface AbortableFuture<T> extends ExcuteFuture<T> {
    boolean abort();
}
