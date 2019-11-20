package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;

public class Game extends Component implements KeyListener, Runnable{

    private static final long serialVersionUID = 1L;

    private Snake.MoveDirection lastKey; // keyboard för snake
    private Timer timer;
    private Grid grid;
    private Snake snake;
    private Screen screen;
    private static int score = 0;


    public Game(int sX, int sY) {

        screen = new Screen(sX, sY);
        grid = new Grid(sX, sY, screen);
        snake = new Snake(screen, grid);
        grid.draw();
        snake.draw();
        screen.setVisible(true);
        lastKey = Snake.MoveDirection.NONE;
        screen.addKeyListener(this);
    }

    public static void addPoint() {
        score++;
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "GAME OVER!", "Game over",
                JOptionPane.YES_NO_OPTION);
        saveHighScore();
        System.exit(ABORT);
    }

    public void saveHighScore() {
        try {
            Score.updateHighscore(JOptionPane.showInputDialog("You got into the highscore list!! Put in your name: "),score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                lastKey = Snake.MoveDirection.UP;
                break;
            case KeyEvent.VK_DOWN:
                lastKey = Snake.MoveDirection.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                lastKey = Snake.MoveDirection.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                lastKey = Snake.MoveDirection.RIGHT;
                break;
        }
    }

    public void run() {
        timer = new Timer();
        timer.schedule(new GameLoop(),1, (long)(500 * 0.5)); //game speed
    }

    class GameLoop extends TimerTask {
        public void run() {
            if (snake.move(lastKey) == false) {
                timer.cancel();
                screen.setTitle("Score: " + score);
                gameOver();
            }


            if( grid.hasApple() == false) {
                grid.spawnApple();
            }
            grid.draw();
            snake.draw();
        }
    }
}
