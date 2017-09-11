package com.mower;

import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends TemplateActivity {
    private TextView mLog, mOutput;
    private RadioButton mType1, mType2;
    private World mWorld;
    private EditText mInput;
    private Button mSend, mRun, mReset;
    private int mMowerSize;

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

        mMowerSize = 0;
        mType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        mType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mInput.getText().toString();
                input = input.replaceAll("^\\s+", "").replaceAll("\\s+$", "").replaceAll("\\s+", " ");
                if (input.compareTo("") == 0) return;
                String[] commands = input.split(" ");
                switch (commands.length) {
                    case 1:
                        if (mType2.isChecked()) showToast("Invalid Input");
                        if (commands[0].matches(".*\\d+.*")) {
                            showToast("Invalid Input");
                            return;
                        }
                        if (mWorld == null) {
                            showToast("Create a matrix first!");
                            return;
                        }
                        if(mWorld.getMowers().size() == 0 ) {
                            showToast("Add some mowers first!");
                            return;
                        }
                        //GO

                        mWorld.getMowers().get(mWorld.getMowers().size() - 1).setInstruction(new Instruction(commands[0]));
                        showToast("updated " +mWorld.getMowers().get(mWorld.getMowers().size() - 1).getName() + "\'s Instruction Set");
                        mInput.setText(null);
                        break;
                    case 2:
                        if (mType2.isChecked()) {
                            showToast("Invalid Input");
                            return;
                        } else {
                            if (!commands[0].matches("\\d+") || !commands[1].matches("\\d+")) {
                                showToast("Invalid Input");
                                return;
                            }
                            if (Integer.parseInt(commands[0]) == 0 || Integer.parseInt(commands[1]) == 0) {
                                showToast("Assigned inputs cannot be zero");
                                return;
                            }

                            int width = Integer.parseInt(commands[0]);
                            int height = Integer.parseInt(commands[1]);

                            mWorld = new World(MainActivity.this, width, height);
                            showToast("Command Accepted\nCreated a new Matrix");

                            mLog.setText("");
                            mOutput.setText("");
                            mInput.setText(null);
                        }
                        break;
                    case 3:
                        if (mType2.isChecked()) {
                            if (!commands[0].matches("\\d+") || !commands[1].matches("\\d+") || !commands[2].matches("\\d+")) {
                                showToast("Invalid Input");
                                return;
                            }
                            if (Integer.parseInt(commands[0]) == 0 || Integer.parseInt(commands[1]) == 0 || Integer.parseInt(commands[2]) == 0) {
                                showToast("Assigned inputs cannot be zero");
                                return;
                            }

                            int mowerSize = Integer.parseInt(commands[2]);
                            int width = Integer.parseInt(commands[0]);
                            int height = Integer.parseInt(commands[1]);

                            if (height > width) {
                                if (mowerSize > width) {
                                    showToast("The Input should be less than the width " + width);
                                    return;
                                }
                            } else if (height < width) {
                                if (mowerSize > height) {
                                    showToast("The Input should be less than the height " + height);
                                    return;
                                }
                            } else {
                                if (mowerSize > width) {
                                    showToast("The Input should be less than the width/height " + width);
                                    return;
                                }
                            }

                            mWorld = new World(MainActivity.this, width, height);
                            mMowerSize = Integer.parseInt(commands[2]);
                            showToast("Command Accepted\nPlease click the Run button");
                        } else {
                            if (mWorld == null) {
                                showToast("Create a matrix first!");
                                return;
                            }

                            if (!commands[0].matches("\\d+") || !commands[1].matches("\\d+") || commands[2].matches("\\d+")) {
                                showToast("Invalid Input");
                                return;
                            }

                            if (commands[2].compareToIgnoreCase("M") == 0 || commands[2].compareToIgnoreCase("L") == 0 || commands[2].compareToIgnoreCase("R") == 0) {
                                showToast("Invalid Direction");
                                return;
                            }

                            int x = Integer.parseInt(commands[0]);
                            int y = Integer.parseInt(commands[1]);

                            if (x > (mWorld.getWidth() - 1)) {
                                showToast("Invalid x-coordinate");
                                return;
                            }

                            if (y > (mWorld.getHeight() - 1)) {
                                showToast("Invalid y-coordinate");
                                return;
                            }

                            String name = "Mower " + (mWorld.getMowers().size() + 1);
                            boolean isValid = true;
                            for (Mower mower : mWorld.getMowers()) {
                                if (mower.getName().compareToIgnoreCase(name) == 0) continue;
                                if (mower.getY() == y && mower.getX() == x)
                                    isValid = false;
                            }
                            if(!isValid) {
                                showToast("Invalid Coordinates");
                                return;
                            }

                            mWorld.addMower(name, x, y, Utilities.directionConverter(commands[2]), "");
                        }
                        break;
                    default:
                        showToast("Invalid Input");
                        break;
                }
            }
        });

        mRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWorld == null) {
                    showToast("Create a matrix first!");
                    return;
                }
                if (mType1.isChecked() && mWorld.getMowers().size() == 0) {
                    showToast("Add some mowers first!");
                    return;
                }
                if (mType1.isChecked()) {
                    mWorld.run();
                } else if (mType2.isChecked()) {
                    mWorld.run(mMowerSize);
                }
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
        mMowerSize = 0;
        mLog.setText("");
        mOutput.setText("");
        mInput.setText(null);
    }
}
