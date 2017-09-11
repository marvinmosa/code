package com.mower;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TemplateActivity {
    private TextView mLog, mOutput;
    private RadioButton mType1, mType2;
    private World mWorld;
    private EditText mInput;
    private Button mSend, mRun, mReset;

    public static final int TYPE_1 = 1000;
    public static final int TYPE_2 = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInput = (EditText) findViewById(R.id.input);
        mLog = (TextView) findViewById(R.id.logs);
        mOutput = (TextView) findViewById(R.id.output);
        mSend = (Button) findViewById(R.id.send);
        mRun = (Button) findViewById(R.id.run);
        mReset = (Button) findViewById(R.id.reset);
        mType1 = (RadioButton) findViewById(R.id.type_part_1);
        mType2 = (RadioButton) findViewById(R.id.type_part_2);
        mType1.setChecked(true);

        mWorld = new World(this, 5, 7);
        mWorld.addMower("Mower 1", 1, 1, Direction.NORTH, "MRMRMRMRM");
        mWorld.addMower("Mower 2", 0, 2, Direction.NORTH, "M");
        mWorld.addMower("Mower 3", 2, 3, Direction.NORTH, "M");

        mWorld.run();

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mInput.getText().toString();
                input = input.replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("\\s+", " ");
                if (input.compareTo("") == 0) return;
                String[] commands = input.split(" ");
                switch (commands.length) {
                    case 1:
                        if (commands[0].matches(".*\\d+.*")) {
                            Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("Marvin", "false");
                        }
                        break;
                    case 2:
                        break;
                    case 4:
                }

            }
        });

        mRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWorld.run(1);
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    public void addLog(String log) {
        mLog.setText(mLog.getText() + log + "\n");
    }

    public void showOutput(String output) {
        mOutput.setText(output);
    }

    public void reset() {
        mWorld = null;
        mLog.setText("");
        mOutput.setText("");
        mInput.setText(null);
    }

    public void run() {
//        if (mType1.isSelected()) {
//            mWorld.run();
//        } else if (mType2.isSelected()) {
//            mWorld.run(1);
//        }
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
}
