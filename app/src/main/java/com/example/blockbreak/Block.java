package com.example.blockbreak;

import android.graphics.RectF;

public class Block {

    float x; // ブロックのｘ座標
    float y; // ブロックのｙ座標
    public final static int WIDTH = 40;
    public final static int HEIGHT = 10;
    public final static int MARGIN = 5; //ブロックの間のマージン
    final int NUM_BLOCK = 8;

    //コンストラクタ
    public Block (int _x,int _y){
        this.x = (float)_x;
        this.y = (float)_y;
    }


//    public boolean collideWieth2(int view_w, int view_h, Ball _ball,Block[] _block,  Block[] _block2, Block[] _block3, Block[] _block4, Block[] _block5, Block[] _block6, Block[] _block7, Block[] _block8, Block[] _block9, Block[] _block10) {
//
//        RectF rectCheck = new RectF();//判定用
//        RectF rect_ball = new RectF(_ball.x - _ball.size, _ball.y - _ball.size, _ball.x + _ball.size, _ball.y + _ball.size);//当たり判定用のrect ball
//
//
//        for (int i = 0; i < NUM_BLOCK; i++) {
//            _block[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10);//blockのx座標とy座標　マージン分横にずらしている
//            _block2[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT * 2 + MARGIN));
//            _block3[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT  * 2 + MARGIN) * 2);
//            _block4[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT  * 2 + MARGIN) * 3);
//            _block5[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT  * 2 + MARGIN) * 4);
//            _block6[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT * 2 + MARGIN) * 5);
//            _block7[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT  * 2 + MARGIN) * 6);
//            _block8[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT  * 2 + MARGIN) * 7);
//            _block9[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT  * 2 + MARGIN) * 8);
//            _block10[i] = new Block(view_w / NUM_BLOCK + (WIDTH * 2 + MARGIN) * i, view_h / 10 + (HEIGHT * 2 + MARGIN) * 9);
//
//        }
//
//        RectF rect_block = null;
//        for (int i = 0; i < NUM_BLOCK; i++) {
//
//            rect_block = new RectF(_block[i].x - WIDTH, _block[i].y - HEIGHT, _block[i].x + WIDTH, _block[i].y + HEIGHT);
//            RectF rect_block2 = new RectF(_block2[i].x - WIDTH, _block2[i].y - HEIGHT, _block2[i].x + WIDTH, _block2[i].y + HEIGHT);
//            RectF rect_block3 = new RectF(_block3[i].x - WIDTH, _block3[i].y - HEIGHT, _block3[i].x + WIDTH, _block3[i].y + HEIGHT);
//            RectF rect_block4 = new RectF(_block4[i].x - WIDTH, _block4[i].y - HEIGHT, _block4[i].x + WIDTH, _block4[i].y + HEIGHT);
//            RectF rect_block5 = new RectF(_block5[i].x - WIDTH, _block5[i].y - HEIGHT, _block5[i].x + WIDTH, _block5[i].y + HEIGHT);
//            RectF rect_block6 = new RectF(_block6[i].x - WIDTH, _block6[i].y - HEIGHT, _block6[i].x + WIDTH, _block6[i].y + HEIGHT);
//            RectF rect_block7 = new RectF(_block7[i].x - WIDTH, _block7[i].y - HEIGHT, _block7[i].x + WIDTH, _block7[i].y + HEIGHT);
//            RectF rect_block8 = new RectF(_block8[i].x - WIDTH, _block8[i].y - HEIGHT, _block8[i].x + WIDTH, _block8[i].y + HEIGHT);
//            RectF rect_block9 = new RectF(_block9[i].x - WIDTH, _block9[i].y - HEIGHT, _block9[i].x + WIDTH, _block9[i].y + HEIGHT);
//            RectF rect_block10 = new RectF(_block10[i].x - WIDTH, _block10[i].y - HEIGHT, _block10[i].x + WIDTH, _block10[i].y + HEIGHT);
//
//
//            if (rectCheck.intersects(rect_ball, rect_block)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block2)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block3)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block4)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block5)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block6)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block7)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block8)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block9)) {
//                return true;
//            } else if (rectCheck.intersects(rect_ball, rect_block10)) {
//                return true;
//            } else {
//                return false;
//            }
//
//        }
//
//
//    }


}
