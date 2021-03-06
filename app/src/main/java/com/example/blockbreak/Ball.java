package com.example.blockbreak;


import android.graphics.Point;
import android.graphics.RectF;

public class Ball {
    //ボールのサイズ
    int size = 15;
    // 表示座標
    float x, y;
    // 速度
    double vx, vy;
    // 画面の幅と高さ
    int view_w, view_h;
    // 弾の生死フラグ
    boolean isLive = true;

    //コンストラクタ
    public Ball(int _x, int _y, int width, int height) {
        //初期座標をセット
        x = (float) _x;
        y = (float) _y;
        //ビューの幅と高さをセット
        view_w = width;
        view_h = height;
        vx = 15;
        vy = 15;
    }

    public void move() {
        x = x + (float) vx;//x座標に移動量をたす（ループの中で）
        y = y + (float) vy;


        if (x > view_w) {//画面右
            vx = -vx;

        } else if (x < 0) {//画面左
            vx = -vx;

        }
        if (y < 0) {//画面上
            vy = -vy;
        } else if (y > view_h) {//画面下
            setPos(view_w/2, view_h/3);

            Life life = new Life();
            life.down_Count(1);

        }
    }

    public void setPos(float x,float y) {
        this.x=x;
        this.y=y;
    }




}