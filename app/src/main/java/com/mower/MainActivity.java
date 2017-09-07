package com.mower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        World world = new World();
        world.addMower(1,1,Direction.NORTH, "MRMRMRMRM");
        world.displayLand();
        world.displayLand2();
        world.play();
    }
}
