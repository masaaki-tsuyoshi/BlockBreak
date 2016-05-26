package com.example.blockbreak;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //カスタムビュークラスのインスタンスをコンテントにセット
        setContentView(new CustomView(this));

    }




    class CustomView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

        //スレッドクラス
        Thread mainLoop;
        Canvas canvas;

        int view_w, view_h;    // SurfaceViewの幅と高さ

        float touchX,touchY;
        int touchAction;//x座標、y座標,アクション種別の取得
        boolean flag;

        GameControl game;


        @Override
        public void onWindowFocusChanged(boolean hasFocus) {
            {
                super.onWindowFocusChanged(hasFocus);

                Point point = new Point(0, 0);
                point.set(getWidth(), getHeight());
                width = getWidth();//720
                height = getHeight();//1230

                Log.d("size", "width: "+width);
                Log.d("size", "height: "+height);


            }


        }



        //コンストラクタ
        public CustomView(Context context) {
            super(context);
            // SurfaceView描画に用いるコールバックを登録する。
            getHolder().addCallback(this);


            //TODO:コンテンツ領域が取れてないのでとる。
            WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
            // ディスプレイのインスタンス生成
            Display disp = wm.getDefaultDisplay();
            Point size = new Point();
            disp.getSize(size);

            view_w = size.x;
            view_h = size.y;

            Log.d("view", "width: "+view_w);
            Log.d("view", "height: "+view_h);

            game = new GameControl(view_w,view_h);

            touchX = game.bar.x;//タッチしてないとき0なので


        }


        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

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

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            touchX =event.getRawX();
            touchY=event.getRawY();
            touchAction=event.getAction();


            //720 1280   bar.y = view_w - 250　1080 タッチできないようにする範囲の上ライン
            float upperLine = game.bar.y - game.bar.height - GameControl.MARGIN;//1065


            switch (touchAction) {
                case MotionEvent.ACTION_DOWN:


                  if(!game.ball.isLive && touchX >= 170 && touchX <= 550 && touchY >= 800 && touchY <= 1170) {//Restartテキストの範囲の概算
                      game.gameRestart(game.ball);

                  }

                    flag = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    //上側だけタッチできないように
                    if(touchX > 0 && touchX < view_w && touchY > 0 && touchY < upperLine ) flag = false;  //upperLineより上はタッチできない
                    else if (touchX > game.bar.halfBar && touchX < view_w - game.bar.halfBar)flag = true; //barが画面内なら

                    break;
                case MotionEvent.ACTION_UP:
                    flag =false;
                    break;
            }

            return true;
        }






        @Override
        public void run(){

            while (true) {

                if (width >0 || height > 0) canvas = getHolder().lockCanvas();
                if (canvas != null)
                {
                    game.gameStart(canvas);


                    //バーのスライド移動
                    if(flag) game.bar.move(touchX);
                    //Ballクラスのボールを移動
                    game.ball.move();
                    //ボールとバーの当たり判定
                    game.collisionDetection();

                    game.gameOver(canvas);



                    getHolder().unlockCanvasAndPost(canvas);

                }
            }

        }


    }





}

