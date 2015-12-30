package xyz.iseeyou.sayhi.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import jp.co.recruit_lifestyle.android.widget.ColoringLoadingView;
import xyz.iseeyou.sayhi.R;

/**
 * Created by bici on 15/1/16.
 */
public class LoadingDialog {
    private AlertDialog materialDialog;
    private ColoringLoadingView loadingView;
    private Context context;

    public LoadingDialog(Context context){
        this.context = context;
        init();
    }

    private void init(){
        View view = LayoutInflater.from(context).inflate(R.layout.my_progress_bar,null);
        loadingView = (ColoringLoadingView)view.findViewById(R.id.loadingView);
        materialDialog = new AlertDialog.Builder(context, R.style.transparent_dialog)
                .setView(view)
                .create();
        loadingView.setCharacter(ColoringLoadingView.Character.CAT);
        loadingView.setColoringColor(0xFFFF1744);
    }

    public void show(){
        int i = (int)(Math.random() * 11);
        ColoringLoadingView.Character character = ColoringLoadingView.Character.CAT;
        switch (i){
            case 0:
                character = ColoringLoadingView.Character.NINJA;
                break;
            case 1:
                character = ColoringLoadingView.Character.BUTTERFLY;
                break;
            case 2:
                character = ColoringLoadingView.Character.AK;
                break;
            case 3:
                character = ColoringLoadingView.Character.HAIR_STYLE;
                break;
            case 4:
                character = ColoringLoadingView.Character.TOOTH;
                break;
            case 5:
                character = ColoringLoadingView.Character.STORM;
                break;
            case 6:
                character = ColoringLoadingView.Character.DOGEZA;
                break;
            case 7:
                character = ColoringLoadingView.Character.CAT;
                break;
            case 8:
                character = ColoringLoadingView.Character.VIOLIN;
                break;
            case 9:
                character = ColoringLoadingView.Character.CUCUMBER;
                break;
            case 10:
                character = ColoringLoadingView.Character.NINJA_STAR;
                break;
        }
        loadingView.setCharacter(character);
        loadingView.startDrawAnimation();
        if(materialDialog != null){
            materialDialog.show();
        }
    }

    public void dismiss(){
        if(materialDialog != null){
            materialDialog.cancel();
        }
    }
}
