package com.example.blockbreak;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    // スレッドクラス
    Thread mainLoop = null;
    // 描画用
    Paint paint = null;
    Paint paint2 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    class CustomView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

        // 円のX,Y座標
        private int circleX = 50;
        private int circleY = 50;
        private int circle2X = 30;
        private int circle2Y = 30;
        // 円の移動量
        private int circleVx = 15;
        private int circleVy = 15;
        private int circle2Vx = 8;
        private int circle2Vy = 15;



        //コンストラクタ
        public CustomView(Context context) {
            super(context);
            // SurfaceView描画に用いるコールバックを登録する。
            getHolder().addCallback(this);
            // 描画用の準備
            paint = new Paint();
            paint2 = new Paint();
            paint.setColor(Color.RED);
            paint2.setColor(Color.CYAN);


            // スレッド開始
            mainLoop = new Thread(this);
            mainLoop.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // TODO 今回は何もしない。
        }


        // SurfaceView生成時に呼び出されるメソッド。
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // 背景
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            holder.unlockCanvasAndPost(canvas);
        }


        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO 今回は何もしない。
        }


        @Override
        public void run(){

            while (true) {
                Canvas canvas = getHolder().lockCanvas();
                if (canvas != null)
                {
                    canvas.drawColor(Color.BLACK);
                    // 円1を描画する
                    canvas.drawCircle(circleX, circleY, 60, paint);
                    //円2を描画する
                    canvas.drawCircle(circle2X,circle2Y,60,paint2);


                    getHolder().unlockCanvasAndPost(canvas);
                    // 円の座標を移動させる
                    circleX += circleVx;
                    circleY += circleVy;
                    circle2X += circle2Vx;
                    circle2Y += circle2Vy;
                    // 画面の領域を超えた？
                    if (circleX < 0 || getWidth() < circleX)  circleVx *= -1;
                    if (circleY < 0 || getHeight() < circleY) circleVy *= -1;
                    if (circle2X < 0 || getWidth() < circle2X)  circle2Vx *= -1;
                    if (circle2Y < 0 || getHeight() < circle2Y) circle2Vy *= -1;

                }
            }





        }
    }





}

