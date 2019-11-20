package com.company;

import javax.swing.*;
import java.awt.*;


public class Screen extends JFrame {


    private Font scoreFont = new Font("Sansserif", Font.PLAIN, 10);
    private int resolution;



    public Screen(int screenX, int screenY) {
        super();
        setSize(screenX*25,screenY*25);
        setResizable(false);
        setTitle("The Return of the snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(screenX, screenY));
        setFont(scoreFont);


        int numComponents = screenX * screenY;

        for(int i = 0; i < numComponents; ++i) {
            add(new JPanel());
        }
        resolution = screenX;
    }



   public void draw(int posX, int posY, Color clr) {
       this.getRootPane().getContentPane().
               getComponent(posY + posX * resolution).setBackground(clr);
       setFont(scoreFont);
   }
}

