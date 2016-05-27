package com.example.blockbreak;


import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;


public class GameControl extends Activity {

    int view_w, view_h;//Canvasの画面サイズ

    Points count;//Pointsクラスのインスタンス

    Paint paint,paint2,paint3,paint4,paint5,paint6,paint7;

    Block block[][];
    int blockWidth = 40;
    int blockHeight = 10;
    public static final int MARGIN =5;
    Bar bar;
    Ball ball;
    int width = Block.WIDTH;
    int height = Block.HEIGHT;

    int num; //カウンターiの最大値変数 画面サイズによって表示個数を変えるため
    int g =1;//ゲームスタート、ゲームオーバー、リスタート case用変数

    //コンストラクタ
    public GameControl(int width, int height){

        //ビューの幅と高さをセット
        view_h = height;
        view_w = width;
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
        paint4.setColor(Color.YELLOW);

        //リスタート用
        paint5.setColor(Color.YELLOW);
        paint5.setAntiAlias(true);
        paint5.setTextSize(70);
        paint5.setTextAlign(Paint.Align.CENTER);

        //残機とスコア用
        paint6.setColor(Color.RED);
        paint6.setAntiAlias(true);
        paint6.setTextSize(40);
        paint6.setTextAlign(Paint.Align.CENTER);

        //ゲームオーバー用
        paint7.setAntiAlias(true);
        paint7.setColor(Color.WHITE);
        paint7.setTextSize(100);
        paint7.setTextAlign(Paint.Align.CENTER);


        ball = new Ball(view_w/2, view_h/3 , view_w, view_h );

        // バーを生成  view_w/2画面の半分
        bar  = new Bar( view_w/2 , view_h - 250 );
        count = new Points();
        //max720 1280 画面サイズ
        num =(view_w - 32 + MARGIN)/(blockWidth *2);//横ブロックの個数. 端のmargin 16*2


    }


    public void collisionDetection(){
        if (bar.collideWidth(ball,bar)){

            if ( ball.x + ball.size >= bar.x - bar.halfBar && ball.x - ball.size <= bar.x + bar.halfBar ) {

                ball.vy *= -1;
                count.count(50);
            } else if ( ball.y + ball.size >= bar.y - bar.height && ball.y - ball.size <= bar.y + bar.height) {

                ball.vx *= -1;

            }

        }

        for( int i = 0;i< num ;i++ ) {
                for (int j = 0; j< 10 ;j++ ) {

                    if (ball.y + ball.size >= block[i][j].y - height && ball.x - ball.size <= block[i][j].x + Block.WIDTH && ball.x + ball.size >= block[i][j].x - width) {
                        ball.vy *= -1;//上の反
                        block[i][j].isLive = false;

                    }
                    if (ball.y - ball.size <= block[i][j].y + height && ball.x - ball.size <= block[i][j].x + width && ball.x + ball.size >= block[i][j].x - width) {
                        ball.vy *= -1;//下の反発
                        block[i][j].isLive = false;

                    }
                    if (ball.x + ball.size >= block[i][j].x - width && ball.y - ball.size <= block[i][j].y + height && ball.y + ball.size >= block[i][j].y - height) {
                        ball.vx *= -1;//左
                        block[i][j].isLive = false;

                    }
                    if (ball.x - ball.size <= block[i][j].x + width && ball.y - ball.size <= block[i][j].y + height && ball.y + ball.size >= block[i][j].y - height) {
                        ball.vx *= -1;//右
                        block[i][j].isLive = false;

                    }

                }

        }




    }



    public void gameStart(Canvas canvas){

        ball.isLive =true;
        canvas.drawColor(Color.BLACK);//背景描画

        //スコア表示
        count.paintPoints(canvas);

        //残機表示
        canvas.drawText("残機:"+ Life.life, 630, 60, paint6);



        //ブロックの描画
        block = new Block[num][10];//横は画面幅に合わせる

        for(int i = 0;i< num ;i++ ){
            for(int n = 0; n<10; n++){
                for(int j =0; j <10; j++){
                        block[i][j] = new Block(view_w / num + (Block.WIDTH * 2 + Block.MARGIN) * i, view_h / 10 + (Block.HEIGHT * 2 + MARGIN) * n);
                        canvas.drawRect(block[i][j].x - blockWidth, block[i][j].y - blockHeight, block[i][j].x + blockWidth, block[i][j].y + blockHeight, paint3);

                }

            }
        }



        //バーを描画する   left top right bottom
        canvas.drawRect(bar.x - bar.halfBar , bar.y  -bar.height , bar.x + bar.halfBar , bar.y + bar.height , paint3);
        //Ballクラスからボールを描画
        canvas.drawCircle( ball.x, ball.y, ball.size , paint4);



    }

    public void gameOver(Canvas canvas) {
        //lifeが０よりも小さくなってしまったら

        if(Life.life <= 0) {
            //残機に０を代入
            Life.life=0;
            //移動を止める為、移動設定をしている変数に０を代入
            ball.vx=0;
            ball.vy=0;
            //ボールを初期位置に戻す
            ball.setPos(view_w/2,view_h/3);
            ball.isLive = false;

            canvas.drawText("Game Over",view_w/2,view_h/2,paint7);
            canvas.drawText("Restart",view_w/2,(view_h/4)*3,paint5);
            Log.d("ball", "ball.isLive" + ball.isLive);
        }

    }

    public void gameRestart(Ball ball) {
        ball.vx = 15;
        ball.vy = 15;

        Life.life = 4;
        ball.isLive = true;

    }



}
