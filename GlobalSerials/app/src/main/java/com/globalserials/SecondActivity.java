package com.globalserials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by mwg on 16-6-1.
 */
public class SecondActivity extends AppCompatActivity {
    public static final String TAG = "SecondActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String name = GlobalVariables.getInstance(this).getName();
        int num = GlobalVariables.getInstance(this).getNum();
        Log.d(TAG, "name:" + name + ", num:" + num);
        setContentView(R.layout.activity_main);
    }
}
