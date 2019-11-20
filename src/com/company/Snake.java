package com.company;

import java.awt.*;
import java.util.LinkedList;;

public class Snake {

    private Position current;
    private Grid grid;
    private Screen sc;
    private LinkedList<Position> posList;
    private int lengthOfTail;
    private Score score;

    public enum MoveDirection {

        UP, DOWN, LEFT, RIGHT, NONE
    }

    public static class Position {
        public int x;
        public int y;
    }

    public Snake(Screen sc, Grid g) {

        this.sc = sc;
        this.grid = g;
        current = new Position();
        current.x = g.getSizeX() / 2;
        current.y = g.getSizeY() / 2;

        posList = new LinkedList<Position>();
        lengthOfTail = 1;
    }

    public void draw() {

        for(Position position : posList) {
            sc.draw(position.x, position.y, Color.GREEN);
            sc.draw(current.x, current.y, Color.YELLOW); //huvud
        }
    }

    public int getLengthOfTail() {
        return lengthOfTail;
    }

    public boolean move(MoveDirection direction) {
        Position last = new Position();
        last.x = current.x; last.y = current.y;
        posList.push(last);

        switch(direction) {
            case UP:
                current.x--;
                break;
            case DOWN:
                current.x++;
                break;
            case LEFT:
                current.y--;
                break;
            case RIGHT:
                current.y++;
                break;
            case NONE:
                return true;
        }

        Grid.Type type = grid.getTypeAt(current.x,current.y);
        if (type == Grid.Type.WALL ) {
            return false;
        }

        for(Position position : posList) {
            if(current.x == position.x && current.y == position.y){
                return false;
            }
        }

        if(type == Grid.Type.APPLE) {
            lengthOfTail++;
            grid.eatApple(current.x, current.y); // Eats apple
            Game.addPoint();
        }

        while(posList.size() > lengthOfTail) {
            posList.removeLast();

        }
        return true;
    }
}