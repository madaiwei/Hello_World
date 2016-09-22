package com.mdw.dialogfrombottom;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn = (Button) findViewById(R.id.btn);
    }

    /*public void showDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("我是消息")
                .create();
        Window window = alertDialog.getWindow();
        window.setWindowAnimations(R.style.dialog_anim);
        window.setGravity(Gravity.BOTTOM);
        alertDialog.show();
        //设置dialog的大小需要在show方法之后
        window.setLayout(300, 300);
        //或者
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = 300;
//        lp.height = 300;
//        window.setAttributes(lp);
    }*/

    public void showDialog(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);

        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(R.mipmap.ic_launcher);
        dialog.addContentView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.show();


        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        window.setGravity(Gravity.CENTER);
        window.setLayout(600, 600);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0;
        window.setAttributes(lp);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击了图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    float startY = -1;
    float endY = -1;
    float moveY = -1;
    float preY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                endY = event.getRawY();
                moveY = endY - preY;
                System.out.println("startY:" + startY + ",endY:" + endY + ",preY:" + preY + ",moveY" + moveY);
                preY = endY;

                if (moveY > 180) {
                    moveY = 180;
                }

                mBtn.setRotation(mBtn.getRotation() + moveY * 2);
                mBtn.setTranslationY(mBtn.getTranslationY() + moveY);
                break;
        }
        return super.onTouchEvent(event);
    }
}
