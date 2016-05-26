package com.example.blockbreak;


public class Life {
    public static int life = 4; //残機

    public void down_Count(int count) {
        //thisを忘れると、カウントしてくれない。
        this.life = life - count;
    }




}
