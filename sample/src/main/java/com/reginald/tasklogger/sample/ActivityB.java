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


public class ActivityB extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "ActivityB";
    public static int intentFlagsBA;
    public static int intentFlagsBC;
    public static int intentFlagsBD;

    AlertDialog menuDialog;
    View menuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

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
        menuView = View.inflate(this, R.layout.menu_b, null);
        menuDialog.setView(menuView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "intentFlagsCA = " + intentFlagsBA + ", intentFlagsCB = " + intentFlagsBC + ", intentFlagsCD = " + intentFlagsBD);

        if (menuDialog != null) {
            final CheckBox ba1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_ba);
            final CheckBox ba2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_ba);
            final CheckBox ba3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_ba);
            final CheckBox ba4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_ba);
            final CheckBox ba5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_ba);

            final CheckBox bc1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_bc);
            final CheckBox bc2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_bc);
            final CheckBox bc3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_bc);
            final CheckBox bc4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_bc);
            final CheckBox bc5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_bc);

            final CheckBox bd1 = (CheckBox) menuView.findViewById(R.id.new_task_checkbox_bd);
            final CheckBox bd2 = (CheckBox) menuView.findViewById(R.id.multi_task_checkbox_bd);
            final CheckBox bd3 = (CheckBox) menuView.findViewById(R.id.clear_top_checkbox_bd);
            final CheckBox bd4 = (CheckBox) menuView.findViewById(R.id.clear_task_checkbox_bd);
            final CheckBox bd5 = (CheckBox) menuView.findViewById(R.id.single_top_checkbox_bd);

            refreshCheckBoxes(intentFlagsBA, ba1, ba2, ba3, ba4, ba5);
            refreshCheckBoxes(intentFlagsBC, bc1, bc2, bc3, bc4, bc5);
            refreshCheckBoxes(intentFlagsBD, bd1, bd2, bd3, bd4, bd5);

            menuDialog.show();

            menuDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    intentFlagsBA = refreshflags(ba1.isChecked(), ba2.isChecked(), ba3.isChecked(), ba4.isChecked(), ba5.isChecked());
                    intentFlagsBC = refreshflags(bc1.isChecked(), bc2.isChecked(), bc3.isChecked(), bc4.isChecked(), bc5.isChecked());
                    intentFlagsBD = refreshflags(bd1.isChecked(), bd2.isChecked(), bd3.isChecked(), bd4.isChecked(), bd5.isChecked());
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
            intent.setFlags(intentFlagsBA);
            startActivity(intent);
        } else if (view.getId() == R.id.btn2) {
            // A -> B
            Intent intent = new Intent(this, ActivityB.class);
            startActivity(intent);
        } else if (view.getId() == R.id.btn3) {
            // A -> C
            Intent intent = new Intent(this, ActivityC.class);
            intent.setFlags(intentFlagsBC);
            startActivity(intent);
        } else if (view.getId() == R.id.btn4) {
            // A -> D
            Intent intent = new Intent(this, ActivityD.class);
            intent.setFlags(intentFlagsBD);
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
