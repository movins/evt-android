package com.github.movins.evt.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;

/**
 * 功能：数据基类
 * Created by movinliao.
 */

public class Parceldata<T extends Parceldata> implements Dataable<T>, Parcelable {
    private boolean empty = true;
    private int ___updateId = 0;

    public Parceldata() {
    }

    protected Parceldata(Parcel in) {
        empty = in.readByte() != 0;
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (empty ? 1 : 0));
    }

    public static final Creator<Parceldata> CREATOR = new Creator<Parceldata>() {
        @Override
        public Parceldata createFromParcel(Parcel in) {
            Parceldata result = new Parceldata();
            result.readFromParcel(in);

            return result;
        }
        @Override
        public Parceldata[] newArray(int size) {
            return new Parceldata[size];
        }
    };

    public boolean equal(T obj) {
        boolean result = false;
        Parcel parcel = null;
        try {
            parcel = Parcel.obtain();
            parcel.writeParcelable(obj, 0);
            parcel.setDataPosition(0);
            String hash1 = new String(parcel.marshall(), "UTF-8");

            parcel.writeParcelable(this, 0);
            parcel.setDataPosition(0);
            String hash2 = new String(parcel.marshall(), "UTF-8");

            result = hash1.equals(hash2);
        } catch (UnsupportedEncodingException e) {
            result = false;
        } finally {
            parcel.recycle();
        }
        return result;
    }

    public boolean assign(T obj) {
        boolean result = false;
        Parcel parcel = null;
        try {
            parcel = Parcel.obtain();
            parcel.writeParcelable(obj, 0);
            parcel.setDataPosition(0);

            readFromParcel(parcel);

            result = true;
        } finally {
            parcel.recycle();
        }
        return result;
    }

    public boolean clear() {
        boolean result = false;
        try {
            Class cls = this.getClass();
            T value = (T)cls.newInstance();
            result = assign(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public T clone () {
        T result = null;
        Parcel parcel = null;
        try {
            parcel = Parcel.obtain();
            parcel.writeParcelable(this, 0);
            parcel.setDataPosition(0);

            result = parcel.readParcelable(this.getClass().getClassLoader());
        } finally {
            parcel.recycle();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public int getUpdateId() {
        return ___updateId;
    }

    @Override
    public void needUpdate() {
        ++___updateId;
    }

    @Override
    public boolean getEmpty() {
        return empty;
    }
    @Override
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

}
