package com.example.blockbreak;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Points {

    int count;//ポイント
    //座標
//    public float x;
//    public float y;
//
//    public Points( int _x, int _y ){
//        //初期座標をセット
//        x = (float)_x;
//        y = (float)_y;
//    }

    public void count(int _count){
        count = _count;

    }

    public void paintPoints(Canvas canvas) {

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setColor(Color.RED);

        canvas.drawText("点数:"+ count, 10, 30, paint);

    }


}
