package com.reginald.tasklogger.sample;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class ActivityD extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "ActivityD";
    public static int intentFlagsDA;
    public static int intentFlagsDB;
    public static int intentFlagsDC;

    AlertDialog menuDialog;
    View menuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);

        Button testBtn1 = (Button) findViewById(R.id.btn1);
        testBtn1.setOnClickListener(this);
        Button testBtn2 = (Button) findViewById(R.id.btn2);
        testBtn2.setOnClickListener(this);
        Button testBtn3 = (Button) findViewById(R.id.btn3);
        testBtn2.setOnClickListener(this);
        Button testBtn4 = (Button) findViewById(R.id.btn4);
        testBtn2.setOnClickListener(this);

        testBtn1.setText("to A");
        testBtn2.setText("to B");
        testBtn3.setText("to C");
        testBtn4.setText("to D");

        menuDialog = new AlertDialog.Builder(this).create();
        menuView = View.inflate(this, R.layout.menu_d, null);
        menuDialog.setView(menuView);


    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "intentFlagsDA = " + intentFlagsDA + ", intentFlagsDB = " + intentFlagsDB + ", intentFlagsDC = " + intentFlagsDC);

        if (menuDialog != null) {
            final CheckBox da1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_da);
            final CheckBox da2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_da);
            final CheckBox da3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_da);
            final CheckBox da4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_da);
            final CheckBox da5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_da);

            final CheckBox db1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_db);
            final CheckBox db2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_db);
            final CheckBox db3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_db);
            final CheckBox db4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_db);
            final CheckBox db5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_db);

            final CheckBox dc1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_dc);
            final CheckBox dc2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_dc);
            final CheckBox dc3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_dc);
            final CheckBox dc4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_dc);
            final CheckBox dc5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_dc);

            refreshCheckBoxes(intentFlagsDA, da1, da2, da3, da4, da5);
            refreshCheckBoxes(intentFlagsDB, db1, db2, db3, db4, db5);
            refreshCheckBoxes(intentFlagsDC, dc1, dc2, dc3, dc4, dc5);

            menuDialog.show();

            menuDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    intentFlagsDA = refreshflags(da1.isChecked(), da2.isChecked(), da3.isChecked(), da4.isChecked(), da5.isChecked());
                    intentFlagsDB = refreshflags(db1.isChecked(), db2.isChecked(), db3.isChecked(), db4.isChecked(), db5.isChecked());
                    intentFlagsDC = refreshflags(dc1.isChecked(), dc2.isChecked(), dc3.isChecked(), dc4.isChecked(), dc5.isChecked());
                }
            });
        }
    }

    public void refreshCheckBoxes(int flags, CheckBox cb1, CheckBox cb2, CheckBox cb3, CheckBox cb4, CheckBox cb5) {
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        cb5.setChecked(false);

        if ((flags & Intent.FLAG_ACTIVITY_NEW_TASK) != 0)
            cb1.setChecked(true);
        if ((flags & Intent.FLAG_ACTIVITY_MULTIPLE_TASK) != 0)
            cb2.setChecked(true);
        if ((flags & Intent.FLAG_ACTIVITY_CLEAR_TOP) != 0)
            cb3.setChecked(true);
        if ((flags & Intent.FLAG_ACTIVITY_CLEAR_TASK) != 0)
            cb4.setChecked(true);
        if ((flags & Intent.FLAG_ACTIVITY_SINGLE_TOP) != 0)
            cb5.setChecked(true);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn1) {
            // A -> A
            Intent intent = new Intent(this, ActivityA.class);
            intent.setFlags(intentFlagsDA);
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            // A -> B
            Intent intent = new Intent(this, ActivityD.class);
            intent.setFlags(intentFlagsDB);
            startActivity(intent);
        } else if (view.getId() == R.id.btn3) {
            // A -> C
            Intent intent = new Intent(this, ActivityC.class);
            intent.setFlags(intentFlagsDC);
            startActivity(intent);
        } else if (view.getId() == R.id.btn4) {
            // A -> D
            Intent intent = new Intent(this, ActivityD.class);
            startActivity(intent);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        ComponentName componentName = intent.getComponent();
        intent.getFlags();
        if (componentName != null) {
            Log.d(TAG, "startActivity " + this.getClass().getSimpleName() + " -> " + componentName.getClassName());
        }
        super.startActivity(intent);
    }

    public int refreshflags(boolean isNewTask, boolean isMultiTask, boolean isClearTop, boolean isClearTask, boolean isSingleTop) {
        int flags = 0;
        if (isNewTask)
            flags |= Intent.FLAG_ACTIVITY_NEW_TASK;
        if (isMultiTask)
            flags |= Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
        if (isClearTop)
            flags |= Intent.FLAG_ACTIVITY_CLEAR_TOP;
        if (isClearTask)
            flags |= Intent.FLAG_ACTIVITY_CLEAR_TASK;
        if (isSingleTop)
            flags |= Intent.FLAG_ACTIVITY_SINGLE_TOP;

        return flags;
    }


}
