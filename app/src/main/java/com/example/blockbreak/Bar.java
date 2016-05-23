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
    public final float halfBar = 80;
    public final int height = 10;


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

    public boolean collideWidth(Ball _ball, Bar _bar) {
        RectF rectCheck = new RectF();//判定用
        RectF rect_bar = new RectF(_bar.x - _bar.halfBar , _bar.y - _bar.height , _bar.x + _bar.halfBar , _bar.y + _bar.height);//当たり判定用rect bar
        RectF rect_ball=new RectF(_ball.x - _ball.size , _ball.y - _ball.size , _ball.x + _ball.size, _ball.y + _ball.size );//当たり判定用のrect ball
        return rectCheck.intersects(rect_bar,rect_ball);//intersect=交差メソッド　ボールが交差したらtrue
    }

}
