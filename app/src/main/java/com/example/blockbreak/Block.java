package com.example.blockbreak;

public class Block {

    float x; // ブロックのｘ座標
    float y; // ブロックのｙ座標
    int blockWidth =30;
    int blockHeight=20; // ブロックの幅、高さ
    int margin = 5; //ブロックの間のマージン
    int i; //カウンター

    //コンストラクタ
    public Block (int _x,int _y){
        this.x = (float)_x;
        this.y = (float)_y;


    }

}
