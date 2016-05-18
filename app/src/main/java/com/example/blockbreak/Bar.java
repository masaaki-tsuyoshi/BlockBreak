package com.example.blockbreak;


import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;

public class Bar {
    //表示座標
    public float x;
    public float y;
    //バーのサイズ
    public final float halfBar = 100;
    public final int height = 100;


    //コンストラクタ

    public Bar( int _x, int _y ){
        //初期座標をセット
        x = (float)_x;
        y = (float)_y;
    }

    //barの移動メソッド
    public void move(float touch_x){
        x = touch_x;
    }



}
