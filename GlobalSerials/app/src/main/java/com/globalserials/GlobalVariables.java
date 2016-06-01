package com.globalserials;

import android.content.Context;

import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by mwg on 16-6-1.
 */
public class GlobalVariables implements Serializable, Cloneable {
    private static final String CACHEDIR = "global variables";
    private static volatile GlobalVariables mInstance;
    private int num;
    private String name;

    private static final long serialVersionUID = 1L;

    private GlobalVariables() {
    }

    public static GlobalVariables getInstance(Context context) {
        if (mInstance == null) {
            synchronized (GlobalVariables.class) {
                Object object = Utils.restoreObject(context, CACHEDIR);
                if(object == null) {
                    object = new GlobalVariables();
                    Utils.saveObject(context, CACHEDIR, object);
                }

                mInstance = (GlobalVariables) object;
            }
        }

        return mInstance;
    }

    public void setNum(Context context, int num) {
        this.num = num;
        Utils.saveObject(context, CACHEDIR, this);
    }

    public void setName(Context context, String name) {
        this.name = name;
        Utils.saveObject(context, CACHEDIR, this);
    }

    //-----------serials------------
    public GlobalVariables readResolve() throws CloneNotSupportedException {
        mInstance = (GlobalVariables) this.clone();
        return mInstance;
    }

    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void reset(Context context) {
        this.name = null;
        this.num = 0;
        Utils.saveObject(context,CACHEDIR, this);
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }
}
