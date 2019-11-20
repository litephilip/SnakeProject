package com.company;

import java.awt.*;
import java.util.Random;

public class Grid {

    private int x;
    private int y;
    private int countApple;
    private Type[][] fieldArray;
    private Screen sc;
    private Random rand;

    public enum Type {
        WALL,
        APPLE,
        FIELD
    }

    public Grid(int sX, int sY, Screen s) {
        fieldArray = new Type[sX][sY];
        this.sc = s;
        x = sX;
        y = sY;
        for(int i = 0; i < sX; ++i) {
            for(int j = 0; j < sY; ++j) {
                if (i == 0 || j == 0 || i == sX - 1 || j == sY - 1) {
                    fieldArray[i][j] = Type.WALL;
                }
                else {
                    fieldArray[i][j] = Type.FIELD;
                }
            }
        }
        countApple = 0; //positionera äpple vid start = 0.
        rand = new Random(); //Slumpar ut äpple
    }

    public void spawnApple()
    {
        while(true) {
            int i = rand.nextInt(x); //Antal från storlek 0 > 1
            int j = rand.nextInt(y);

            if(fieldArray[i][j] == Type.FIELD) {
                fieldArray[i][j] = Type.APPLE;
                ++countApple;
                break;
            }
        }
    }

    public boolean eatApple(int px, int py) {

        if (fieldArray[px][py] == Type.APPLE) {
            --countApple;                       // Värde för nytt äpple när Snake ätit upp
            fieldArray[px][py] = Type.FIELD;
            return true;
        }
        return false;
    }

    public boolean hasApple() {
        return countApple > 0;
    }

    public Type getTypeAt(int px, int py) {
        return fieldArray[px][py];
    }

    public int getSizeX() {
        return x;
    }

    public int getSizeY() {
        return y;
    }

    public void draw() {
        for(int i = 0; i < x; ++i) {

            for(int j = 0; j < y; ++j) {
                if (fieldArray[i][j] == Type.FIELD) { //spelplan
                    sc.draw(i, j, Color.black);
                }
                else if(fieldArray[i][j] == Type.WALL) { // Vägg
                    sc.draw(i, j, Color.darkGray);
                }
                else {
                    sc.draw(i, j, Color.red); //Äpple
                }
            }
        }
    }
}
