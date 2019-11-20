package com.company;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Score {


   public static void updateHighscore(String name, int score) throws IOException {
      File file  = new File("Highscore.txt");
      Scanner inFile = new Scanner(file);

      FileWriter fw = new FileWriter("Highscore.tmp");
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter outFile = new PrintWriter(bw);

      boolean done = false;

      while (inFile.hasNext()) {
         String line = inFile.nextLine();
         String strScore = line.split(";")[1];
         int intScore = Integer.parseInt(strScore);
         if (score > intScore && !done) {
            outFile.println(name + ";" + score);
            done = true;
         }
         outFile.println(line);
      }
      if(!done)
         outFile.println(name + ";" + score);
      outFile.flush();   //tömmer buffern
      inFile.close();
      outFile.close();
      file.delete();
      File newHigh = new File("Highscore.tmp");
      newHigh.renameTo(file);
   }
}
