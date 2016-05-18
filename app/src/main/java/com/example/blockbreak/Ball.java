package com.example.blockbreak;


import android.graphics.Point;

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

    public int getX(){
        return (int)x;
    }
    public int getY(){
        return (int)y;
    }

    public void move() {
        // 円の座標を移動させる
        //circleX += circleVxの部分
        x = x + (float) vx;
        y = y + (float) vy;

        // 壁に当たった時の処理、速度を入れ替える
        if (x > view_w - size) {
            //画面右
            vx = -vx;
            x = (view_w - size);
        } else if (x < 0) {
            //画面左
            vx = -vx;
            x = 0;
        }
        if (y < 0) {
            //画面上
            vy = -vy;
            y = 0;
        } else if (y > view_h) {
            //画面下
            isLive = false;
        }
    }
}