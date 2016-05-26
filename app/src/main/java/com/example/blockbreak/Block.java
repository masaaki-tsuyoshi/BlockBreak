package com.example.blockbreak;

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


    public static boolean collideWidth2(int view_w, int view_h, Ball ball, Block[][] block) {

        int num =(view_w - 32 + MARGIN)/(WIDTH *2);

        for( int i = 0;i< num ;i++ ) {
            for (int j = 0; j< 10 ;j++ ) {

                if (ball.y + ball.size >= block[i][j].y - HEIGHT && ball.x - ball.size <= block[i][j].x + WIDTH && ball.x + ball.size >= block[i][j].x - WIDTH) {
                    ball.y *= -1;//上の反発
                    Points count = new Points();
                    count.count(50);
                }
                else if (ball.y - ball.size <= block[i][j].y + HEIGHT && ball.x - ball.size <= block[i][j].x + WIDTH && ball.x + ball.size >= block[i][j].x - WIDTH) {
                    ball.y *= -1;//下の反発
                    Points count = new Points();
                    count.count(50);
                } else if (ball.x + ball.size >= block[i][j].x - WIDTH && ball.y - ball.size <= block[i][j].y + HEIGHT && ball.y - ball.size >= block[i][j].y - HEIGHT) {
                    ball.vx *= -1;//左
                    Points count = new Points();
                    count.count(50);
                } else if (ball.x - ball.size <= block[i][j].x + WIDTH && ball.y - ball.size <= block[i][j].y + HEIGHT && ball.y - ball.size >= block[i][j].y - HEIGHT) {
                    ball.vx *= -1;//右
                    Points count = new Points();
                    count.count(50);
                }
            }

        }

        return true;

    }


}
