package com.mower;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by marvinmosa on 9/11/17.
 */

public class TemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LifeCycle", getClass().getSimpleName() + ": onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("LifeCycle", getClass().getSimpleName() + ": onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("LifeCycle", getClass().getSimpleName() + ": onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("LifeCycle", getClass().getSimpleName() + ": onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("LifeCycle", getClass().getSimpleName() + ": onStop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("LifeCycle", getClass().getSimpleName() + ": onDestroy");
        super.onDestroy();
    }
    public void releaseFocus(View view) {
        ViewParent parent = view.getParent();
        ViewGroup group = null;
        View child = null;
        while (parent != null) {
            if (parent instanceof ViewGroup) {
                group = (ViewGroup) parent;
                for (int i = 0; i < group.getChildCount(); i++) {
                    child = group.getChildAt(i);
                    if (child != view && child.isFocusable())
                        child.requestFocus();
                }
            }
            parent = parent.getParent();
        }
    }
}
