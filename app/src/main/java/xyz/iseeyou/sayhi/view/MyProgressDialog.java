package xyz.iseeyou.sayhi.view;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import xyz.iseeyou.sayhi.R;

/**
 * Created by bici on 15/1/16.
 */
public class MyProgressDialog {
    private AlertDialog materialDialog;
    private CircularProgress progressBar;
    private TextView messageView;
    private Context context;

    public MyProgressDialog(Context context){
        this.context = context;
        init();
    }

    private void init(){
        View view = LayoutInflater.from(context).inflate(R.layout.my_progress_bar,null);
        progressBar = (CircularProgress)view.findViewById(R.id.progress);
        messageView = (TextView)view.findViewById(R.id.messageView);
        materialDialog = new BiciAlertDialogBuilder(context)
                .setView(view)
                .create();
//        materialDialog.setCanceledOnTouchOutside(false);
    }

    public void show(){
        if(materialDialog != null){
            materialDialog.show();
        }
    }

    public void show(String msg){
        messageView.setText(msg);
        show();
    }

    public void show(@StringRes int res){
        show(context.getString(res));
    }

//    public void dismiss(@StringRes int res){
//        dismiss(context.getString(res));
//    }
//
//    public void dismiss(final String msg){
//        if(materialDialog != null){
//            messageView.post(new Runnable() {
//                @Override
//                public void run() {
//                    progressBar.stop();
//                    messageView.setText(msg);
//                }
//            });
//
//            messageView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    materialDialog.cancel();
//                }
//            },500);
//        }
//    }

    public void dismiss(){
        if(materialDialog != null){
            materialDialog.cancel();
        }
    }
}
