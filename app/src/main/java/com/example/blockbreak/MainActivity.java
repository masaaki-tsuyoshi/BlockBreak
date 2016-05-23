package com.example.blockbreak;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

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
        // 描画用
        Paint paint,paint2,paint3,paint4,paint5,paint6,paint7;


        Block[] block,block2,block3,block4,block5,block6,block7,block8,block9,block10;
        int blockWidth = 40;
        int blockHeight = 10;

        Bar  bar;              //バー
        int view_w, view_h;    // SurfaceViewの幅と高さ
        Ball ball;
        int num; //カウンターiの最大値変数 画面サイズによって表示個数を変えるため

        final int NUM_BLOCK = 8;//ブロック個数
        int margin =5;

        float touchX,touchY;
        int touchAction;//x座標、y座標,アクション種別の取得

        boolean flag;
        Points count;//カウント用のPointsクラスのインスタンス


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

        public Point getViewSize(View View){
            Point point = new Point(0, 0);
            point.set(View.getWidth(), View.getHeight());

            return point;
        }



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
            paint7 = new Paint();

            paint.setColor(Color.RED);
            paint2.setColor(Color.CYAN);
            paint3.setColor(Color.GREEN);
            paint4.setColor(Color.RED);
            paint5.setColor(Color.YELLOW);
            paint6.setColor(Color.BLUE);

            paint6.setAntiAlias(true);
            paint6.setTextSize(40);
            paint6.setTextAlign(Paint.Align.CENTER);
            paint6.setColor(Color.RED);

            paint7.setAntiAlias(true);
            paint7.setColor(Color.WHITE);
            paint7.setTextSize(100);
            paint7.setTextAlign(Paint.Align.CENTER);


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

            //ボールを生成
            ball = new Ball(view_w/2, view_h/3 , view_w, view_h );

            // バーを生成  view_w/2画面の半分
            bar  = new Bar( view_w/2 , view_h - 250 );
            touchX = bar.x;//タッチしてないとき0なので

            count = new Points();


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
            float upperLine = bar.y-bar.height-margin;//1065


            switch (touchAction) {
                case MotionEvent.ACTION_DOWN:
                    flag = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    //上側だけタッチできないように
                    if(touchX > 0 && touchX < view_w && touchY > 0 && touchY < upperLine ) flag = false;
                    else flag = true;

                    break;
                case MotionEvent.ACTION_UP:
                    flag =false;
                    break;
            }

            return true;
        }



        public void collisionDetection(){
            if (bar.collideWidth(ball,bar)){

//                //TODO:vx vyの動きを定義
//                if ( ball.y + ball.size >= bar.y - bar.height && ball.y - ball.size <= bar.y + bar.height && ball.x + ball.size >= bar.x - bar.halfBar && ball.x - ball.size <= bar.x + bar.halfBar ) {
//                    ball.vy *= -1;
//                }
//                if ( ball.x + ball.size >= bar.x - bar.halfBar && ball.x - ball.size <= bar.x + bar.halfBar && ball.y + ball.size >= bar.y - bar.height && ball.y - ball.size <= bar.y + bar.height ) {
//                    ball.vx *= -1;
//                }
                if ( ball.x + ball.size >= bar.x - bar.halfBar && ball.x - ball.size <= bar.x + bar.halfBar ) {

                    ball.vy *= -1;
                    count.count(50);
                } else if ( ball.y + ball.size >= bar.y - bar.height && ball.y - ball.size <= bar.y + bar.height) {

                    ball.vx *= -1;
                    count.count(50);
                }

            }

//            if(collideWidth2(view_w,view_h,ball)) {
//                if ( ball.x + ball.size >= bar.x - bar.halfBar && ball.x - ball.size <= bar.x + bar.halfBar ) {
//                    ball.vy *= -1;
//                }
//                if ( ball.y + ball.size >= bar.y - bar.height && ball.y - ball.size <= bar.y + bar.height) {
//                    ball.vx *= -1;
//                }
//
//            }
        }


        @Override
        public void run(){

            while (true) {

                if (width >0 || height > 0) canvas = getHolder().lockCanvas();
                if (canvas != null)
                {
                    //背景
                    canvas.drawColor(Color.BLACK);

                    count.paintPoints(canvas);

                    canvas.drawText("残機:"+ Life.life, 630, 60, paint6);
                    //スコア表示
//                  canvas.drawText("点数:"+ count, 10, 30, paint3);
//
//                  draw(canvas);

                    //Log.d("point","x"+ count.x);
//                    Log.d("point","y"+ count.y);

                    //max720 1280 画面サイズ
                    num =(view_w - 32 + margin)/(blockWidth *2);//横ブロックの個数. 端のmargin 16*2


                    //配列の初期化

                    //配列の初期化
                    block = new Block[num];
                    block2 = new Block[num];
                    block3 = new Block[num];
                    block4 = new Block[num];
                    block5 = new Block[num];
                    block6 = new Block[num];
                    block7 = new Block[num];
                    block8 = new Block[num];
                    block9 = new Block[num];
                    block10 = new Block[num];


                    for(int i = 0;i< num ;i++ )
                    {
                        block[i] = new Block(view_w/num + (Block.WIDTH*2+ Block.MARGIN)*i, view_h/10);//blockのx座標とy座標　マージン分横にずらしている
                        block2[i] = new Block (view_w/num + (Block.WIDTH*2+ Block.MARGIN)*i, view_h/10 + (Block.HEIGHT*2 + margin));
                        block3[i] = new Block (view_w/num + (Block.WIDTH*2+ Block.MARGIN)*i, view_h/10 + (Block.HEIGHT*2 + margin)*2);
                        block4[i] = new Block (view_w/num + (Block.WIDTH*2+ Block.MARGIN)*i, view_h/10 + (Block.HEIGHT*2 + margin)*3);
                        block5[i] = new Block (view_w/num + (Block.WIDTH*2+ Block.MARGIN)*i, view_h/10 + (Block.HEIGHT*2 + margin)*4);
                        block6[i] = new Block (view_w/num + (Block.WIDTH*2+ margin)*i, view_h/10 + (Block.HEIGHT*2 + margin)*5);
                        block7[i] = new Block (view_w/num + (Block.WIDTH*2+ margin)*i, view_h/10 + (Block.HEIGHT*2 + margin)*6);
                        block8[i] = new Block (view_w/num + (Block.WIDTH*2+ margin)*i, view_h/10 + (Block.HEIGHT*2 + margin)*7);
                        block9[i] = new Block (view_w/num+ (Block.WIDTH*2+ margin)*i, view_h/10 + (Block.HEIGHT*2 + margin)*8);
                        block10[i] = new Block (view_w/num + (Block.WIDTH*2+ margin)*i, view_h/10 + (Block.HEIGHT*2 + margin)*9);}






                    //ブロックの描画
                    for(int i = 0;i<num;i++)
                    {
//                        Log.d("block.x","blockのx座標"+block[i].x);
//                        Log.d("block.y","blockのy座標"+block[i].y);
                        canvas.drawRect(block[i].x -blockWidth,block[i].y -blockHeight, block[i].x +blockWidth, block[i].y + blockHeight, paint3);
                        canvas.drawRect(block2[i].x -blockWidth,block2[i].y -blockHeight, block2[i].x +blockWidth, block2[i].y + blockHeight, paint3);
                        canvas.drawRect(block3[i].x -blockWidth,block3[i].y -blockHeight, block3[i].x +blockWidth, block3[i].y + blockHeight, paint3);
                        canvas.drawRect(block4[i].x -blockWidth,block4[i].y -blockHeight, block4[i].x +blockWidth, block4[i].y + blockHeight, paint3);
                        canvas.drawRect(block5[i].x -blockWidth,block5[i].y -blockHeight, block5[i].x +blockWidth, block5[i].y + blockHeight, paint3);
                        canvas.drawRect(block6[i].x -blockWidth,block6[i].y -blockHeight, block6[i].x +blockWidth, block6[i].y + blockHeight, paint3);
                        canvas.drawRect(block7[i].x -blockWidth,block7[i].y -blockHeight, block7[i].x +blockWidth, block7[i].y + blockHeight, paint3);
                        canvas.drawRect(block8[i].x -blockWidth,block8[i].y -blockHeight, block8[i].x +blockWidth, block8[i].y + blockHeight, paint3);
                        canvas.drawRect(block9[i].x -blockWidth,block9[i].y -blockHeight, block9[i].x +blockWidth, block9[i].y + blockHeight, paint3);
                        canvas.drawRect(block10[i].x -blockWidth,block10[i].y -blockHeight, block10[i].x +blockWidth, block10[i].y + blockHeight, paint3);
                    }

//                    //ブロックを描画する
//                    for(int i=0; i <24 ;i+=1) {
//
//                        canvas.drawRect(block[i].x -40,block[i].y -10, block[i].x +40, block[i].y +10, paint3);
//
//                    }

                    //バーを描画する   left top right bottom
                    canvas.drawRect(bar.x - bar.halfBar , bar.y  -bar.height , bar.x + bar.halfBar , bar.y + bar.height , paint3);
                    //Ballクラスからボールを描画
                    canvas.drawCircle( ball.x, ball.y, ball.size , paint5);


                    //バーのスライド移動
                    if(flag) bar.move(touchX);

                    //Ballクラスのボールを移動
                    ball.move();

                    //ボールとバーの当たり判定
                    collisionDetection();



                    //TODO:玉とブロックの衝突判定 ブロック消失　玉反発　音がなる
                    ball.gameOver();

                    if(!ball.isLive) {
                        canvas.drawText("Game Over",view_w/2,view_h/2,paint7);
                    }





                    getHolder().unlockCanvasAndPost(canvas);

                }
            }

        }

        public void onDraw(Canvas c){

            count.paintPoints(canvas);

        }

    }





}

