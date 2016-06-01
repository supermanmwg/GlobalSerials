package com.globalserials;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by mwg on 16-6-1.
 */
public class Utils {

    public static final void saveObject(Context context, String path, Object saveObject) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File dir = context.getFilesDir();
        File f = new File(dir, path);

        try {
            fos = new FileOutputStream(f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(saveObject);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static final Object restoreObject(Context context, String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        Object object = null;

        File dir = context.getFilesDir();
        File f = new File(dir, path);
        if (!f.exists()) {
            return null;
        }

        try {
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();

            return object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return object;
    }
}
