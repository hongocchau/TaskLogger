package com.reginald.tasklogger.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testBtn1 = (Button) findViewById(R.id.btn1);
        testBtn1.setOnClickListener(this);
        Button testBtn2 = (Button) findViewById(R.id.btn2);
        testBtn2.setOnClickListener(this);
        Button testBtn3 = (Button) findViewById(R.id.btn3);
        testBtn3.setOnClickListener(this);
        Button testBtn4 = (Button) findViewById(R.id.btn4);
        testBtn4.setOnClickListener(this);

        testBtn1.setText("Act A");
        testBtn2.setText("Act B");
        testBtn3.setText("Act C");
        testBtn4.setText("Act D");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            //  -> A
            Intent intent = new Intent(this, ActivityA.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            //  -> B
            Intent intent = new Intent(this, ActivityB.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view.getId() == R.id.btn3) {
            //  -> B
            Intent intent = new Intent(this, ActivityC.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view.getId() == R.id.btn4) {
            //  -> B
            Intent intent = new Intent(this, ActivityD.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}
