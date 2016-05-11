package com.example.blockbreak;

import android.graphics.Rect;

public class Bar {
    //表示座標
    public float x;
    public float y;
    //バーのサイズ
    public final float halfBar = 100;
    public final int height = 10;


    //コンストラクタ
    public Bar( int _x, int _y ){
        //初期座標をセット
        x = (float)_x;
        y = (float)_y;
    }
    // 右移動
    public void right(float touch_x){
        x  = touch_x;//x座標に,タッチされた座標-バーの半分の長さ
    }
    // 左移動
    public void left(float touch_x){
        x =  touch_x;//x座標に,タッチされた座標-バーの半分の長さ
    }



}
