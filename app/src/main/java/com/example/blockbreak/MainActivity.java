package com.example.blockbreak;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //カスタムビュークラスのインスタンスをコンテントにセット
        setContentView(new CustomView(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    class CustomView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

        //スレッドクラス
        Thread mainLoop = null;
        // 描画用
        Paint paint = null;
        Paint paint2 = null;
        Paint paint3 = null;
        Paint paint4 = null;
        Paint paint5 = null;
        Paint paint6 = null;

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

        Block[] block;
        Bar  bar;              //バー
        int view_w, view_h;    // SurfaveViewの幅と高さ



        //コンストラクタ
        public CustomView(Context context) {
            super(context);
            // SurfaceView描画に用いるコールバックを登録する。
            getHolder().addCallback(this);
            // 描画用の準備
            paint = new Paint();
            paint2 = new Paint();
            paint3 = new Paint();
            paint4 = new Paint();
            paint5 = new Paint();
            paint6 = new Paint();

            paint.setColor(Color.RED);
            paint2.setColor(Color.CYAN);
            paint3.setColor(Color.GREEN);
            paint4.setColor(Color.RED);
            paint5.setColor(Color.YELLOW);
            paint6.setColor(Color.BLUE);



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
            // スレッド開始
            mainLoop = new Thread(this);
            mainLoop.start();


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
                    //背景
                    canvas.drawColor(Color.BLACK);

                    view_w = canvas.getWidth();
                    view_h = canvas.getHeight();

                    // バーを生成  view_w/2画面の半分
                    bar  = new Bar( view_w/2 , view_h - 100 );
                    //max720 1280
                    //ブロックを生成
                    block = new Block[21];
                    block[0]= new Block(view_w/10, view_h/9);
                    block[1]= new Block(view_w/10 + 100, view_h/9 );
                    block[2]= new Block(view_w/10 + 200, view_h/9);
                    block[3]= new Block(view_w/10 + 300, view_h/9);
                    block[4]= new Block(view_w/10 + 400, view_h/9);
                    block[5]= new Block(view_w/10 + 500, view_h/9);
                    block[6]= new Block(view_w/10 + 600, view_h/9);

                    block[7]= new Block(view_w/10, view_h/9 +50 );
                    block[8]= new Block(view_w/10 + 100, view_h/9 + 50);
                    block[9]= new Block(view_w/10 + 200, view_h/9 + 50 );
                    block[10]= new Block(view_w/10 + 300, view_h/9 + 50);
                    block[11]= new Block(view_w/10 + 400, view_h/9 + 50);
                    block[12]= new Block(view_w/10 + 500, view_h/9 + 50);
                    block[13]= new Block(view_w/10 + 600, view_h/9 + 50);

                    block[14]= new Block(view_w/10, view_h/9 + 100);
                    block[15]= new Block(view_w/10 + 100, view_h/9 + 100);
                    block[16]= new Block(view_w/10 + 200, view_h/9 + 100);
                    block[17]= new Block(view_w/10 + 300, view_h/9 + 100);
                    block[18]= new Block(view_w/10 + 400, view_h/9 + 100);
                    block[19]= new Block(view_w/10 + 500, view_h/9 + 100);
                    block[20]= new Block(view_w/10 + 600, view_h/9 + 100);


                    // 円1を描画する
                    canvas.drawCircle(circleX, circleY, 20, paint);
                    //円2を描画する
                    canvas.drawCircle(circle2X,circle2Y,20,paint2);
                    //バーを描画する   left top right bottom
                    canvas.drawRect( bar.x -100 , bar.y , bar.x + 100 , bar.y + 20  , paint3);
                    //ブロックを描画する
                    for(int i=0; i < 21;i+=1)
                    {
                        if(i<7) canvas.drawRect(block[i].x -40,block[i].y, block[i].x +40, block[i].y +20, paint4);
                        if(i>=7||i<14) canvas.drawRect(block[i].x -40,block[i].y, block[i].x +40, block[i].y +20, paint5);
                        if(i>=14||i<21) canvas.drawRect(block[i].x -40,block[i].y, block[i].x +40, block[i].y +20, paint6);

                    }


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

                    getHolder().unlockCanvasAndPost(canvas);

                }
            }





        }
    }





}

