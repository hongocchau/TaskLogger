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


public class ActivityC extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "ActivityC";
    public static int intentFlagsCA;
    public static int intentFlagsCB;
    public static int intentFlagsCD;

    AlertDialog menuDialog;
    View menuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

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
        menuView = View.inflate(this, R.layout.menu_c, null);
        menuDialog.setView(menuView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "intentFlagsCA = " + intentFlagsCA + ", intentFlagsCB = " + intentFlagsCB + ", intentFlagsCD = " + intentFlagsCD);

        if (menuDialog != null) {
            final CheckBox ca1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_ca);
            final CheckBox ca2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_ca);
            final CheckBox ca3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_ca);
            final CheckBox ca4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_ca);
            final CheckBox ca5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_ca);

            final CheckBox cb1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_cb);
            final CheckBox cb2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_cb);
            final CheckBox cb3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_cb);
            final CheckBox cb4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_cb);
            final CheckBox cb5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_cb);

            final CheckBox cd1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_cd);
            final CheckBox cd2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_cd);
            final CheckBox cd3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_cd);
            final CheckBox cd4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_cd);
            final CheckBox cd5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_cd);

            refreshCheckBoxes(intentFlagsCA, ca1, ca2, ca3, ca4, ca5);
            refreshCheckBoxes(intentFlagsCB, cb1, cb2, cb3, cb4, cb5);
            refreshCheckBoxes(intentFlagsCD, cd1, cd2, cd3, cd4, cd5);

            menuDialog.show();

            menuDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    intentFlagsCA = refreshflags(ca1.isChecked(), ca2.isChecked(), ca3.isChecked(), ca4.isChecked(), ca5.isChecked());
                    intentFlagsCB = refreshflags(cb1.isChecked(), cb2.isChecked(), cb3.isChecked(), cb4.isChecked(), cb5.isChecked());
                    intentFlagsCD = refreshflags(cd1.isChecked(), cd2.isChecked(), cd3.isChecked(), cd4.isChecked(), cd5.isChecked());
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
            intent.setFlags(intentFlagsCA);
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            // A -> B
            Intent intent = new Intent(this, ActivityC.class);
            intent.setFlags(intentFlagsCB);
            startActivity(intent);
        } else if (view.getId() == R.id.btn3) {
            // A -> C
            Intent intent = new Intent(this, ActivityC.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn4) {
            // A -> D
            Intent intent = new Intent(this, ActivityD.class);
            intent.setFlags(intentFlagsCD);
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
