package com.github.movins.evt.excute;

public class AbortResult<T> extends FutureResult<T> implements AbortableFuture<T> {
    @Override
    public boolean abort() {
        this.callback = null;
        return true;
    }
}