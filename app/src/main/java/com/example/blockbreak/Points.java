package com.example.blockbreak;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Points {

    int count;//ポイント

    public void count(int _count){
        count = count + _count;

    }

    public void paintPoints(Canvas canvas) {

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setTextSize(40);
        paint.setColor(Color.RED);

        canvas.drawText("点数:"+ count, 20, 60, paint);

    }


}
