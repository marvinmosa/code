package com.mower.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    releaseFocus(v);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public void showToast(String body) {
        Toast.makeText(this, body, Toast.LENGTH_SHORT).show();
    }
}
