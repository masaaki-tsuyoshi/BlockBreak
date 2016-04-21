package com.example.blockbreak;

public class Bar {
    //表示座標
    public float x;
    public float y;
    //バーのサイズ
    public final float half_size = 100;

    //コンストラクタ
    public Bar( int _x, int _y ){
        //初期座標をセット
        x = (float)_x;
        y = (float)_y;
    }
    // 右移動
    public void right(float touch_x){
        x  = touch_x - half_size;
    }
    // 左移動
    public void left(float touch_x){
        x =  touch_x - half_size;
    }
}
