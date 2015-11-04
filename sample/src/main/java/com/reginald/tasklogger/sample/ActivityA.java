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

public class ActivityA extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "ActivityA";
    public static int intentFlagsAB;
    public static int intentFlagsAC;
    public static int intentFlagsAD;

    AlertDialog menuDialog;
    View menuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Button testBtn1 = (Button) findViewById(R.id.btn1);
        testBtn1.setOnClickListener(this);
        Button testBtn2 = (Button) findViewById(R.id.btn2);
        testBtn2.setOnClickListener(this);
        Button testBtn3 = (Button) findViewById(R.id.btn3);
        testBtn3.setOnClickListener(this);
        Button testBtn4 = (Button) findViewById(R.id.btn4);
        testBtn4.setOnClickListener(this);

        testBtn1.setText("to A");
        testBtn2.setText("to B");
        testBtn3.setText("to C");
        testBtn4.setText("to D");

        menuDialog = new AlertDialog.Builder(this).create();
        menuView = View.inflate(this, R.layout.menu_a, null);
        menuDialog.setView(menuView);


    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "intentFlagsAB = " + intentFlagsAB + ", intentFlagsAC = " + intentFlagsAC + ", intentFlagsAD = " + intentFlagsAD);

        if (menuDialog != null) {
            final CheckBox ab1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_ab);
            final CheckBox ab2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_ab);
            final CheckBox ab3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_ab);
            final CheckBox ab4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_ab);
            final CheckBox ab5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_ab);

            final CheckBox ac1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_ac);
            final CheckBox ac2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_ac);
            final CheckBox ac3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_ac);
            final CheckBox ac4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_ac);
            final CheckBox ac5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_ac);

            final CheckBox ad1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_ad);
            final CheckBox ad2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_ad);
            final CheckBox ad3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_ad);
            final CheckBox ad4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_ad);
            final CheckBox ad5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_ad);

            refreshCheckBoxes(intentFlagsAB, ab1, ab2, ab3, ab4, ab5);
            refreshCheckBoxes(intentFlagsAC, ac1, ac2, ac3, ac4, ac5);
            refreshCheckBoxes(intentFlagsAD, ad1, ad2, ad3, ad4, ad5);

            menuDialog.show();

            menuDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    intentFlagsAB = refreshflags(ab1.isChecked(), ab2.isChecked(), ab3.isChecked(), ab4.isChecked(), ab5.isChecked());
                    intentFlagsAC = refreshflags(ac1.isChecked(), ac2.isChecked(), ac3.isChecked(), ac4.isChecked(), ac5.isChecked());
                    intentFlagsAD = refreshflags(ad1.isChecked(), ad2.isChecked(), ad3.isChecked(), ad4.isChecked(), ad5.isChecked());
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
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            // A -> B
            Intent intent = new Intent(this, ActivityB.class);
            intent.setFlags(intentFlagsAB);
            startActivity(intent);
        } else if (view.getId() == R.id.btn3) {
            // A -> C
            Intent intent = new Intent(this, ActivityC.class);
            intent.setFlags(intentFlagsAC);
            startActivity(intent);
        } else if (view.getId() == R.id.btn4) {
            // A -> D
            Intent intent = new Intent(this, ActivityD.class);
            intent.setFlags(intentFlagsAD);
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
