package com.github.movins.evt.excute;

import android.os.Looper;

public class ExcuteUtils {
    private static final long kTimeMax = 30000L;

    public static <T> ExcuteResult<T> excute(ExcuteFuture<T> future, long time) {
        if (time < 0L || time > kTimeMax) {
            time = kTimeMax;
        }

        ExcuteTask task = new ExcuteTask();
        if (Looper.myLooper() != null) {
            task.create(1003);
            return task.getResult();
        } else {
            future.setCallback(new ExcuteWrapper(task));
            ExcuteWaitWrapper waiter = new ExcuteWaitWrapper(task);
            try {
                waiter.excute(time);
            } catch (ExcuteException exception) {
                task.create(1002);
            } catch (Throwable error) {
                task.create(1004);
            }

            return task.getResult();
        }
    }
}