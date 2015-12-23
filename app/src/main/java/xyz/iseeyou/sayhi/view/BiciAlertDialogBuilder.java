package xyz.iseeyou.sayhi.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;

import xyz.iseeyou.sayhi.util.DensityUtil;

public class BiciAlertDialogBuilder extends AlertDialog.Builder {
    public BiciAlertDialogBuilder(Context context) {
        super(context);
    }

    @Override
    public AlertDialog show() {
        AlertDialog dialog = super.show();
        setButtonColor(dialog);
        return dialog;
    }

    public static void setButtonColor(AlertDialog alertDialog){
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)negativeButton.getLayoutParams();
        layoutParams.setMargins(0, 0, DensityUtil.dp2px(16), 0);
        if(negativeButton != null){
            negativeButton.setTextColor(Color.parseColor("#ff808080"));
        }
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if(positiveButton != null){
            positiveButton.setTextColor(Color.parseColor("#ff007ac2"));
        }
    }
}
