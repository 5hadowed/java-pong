import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Score {

  static int GAME_WIDTH;
  static int GAME_HEIGHT;
  int[] scorearray = new int[2];

  Score(int GAME_WIDTH, int GAME_HEIGHT) {
    this.GAME_WIDTH = GAME_WIDTH;
    this.GAME_HEIGHT = GAME_HEIGHT;
  }
    
  public void draw(Graphics g) {
      g.setColor(Color.white);
      g.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
      g.drawString(String.valueOf(scorearray[1]/10) + String.valueOf(scorearray[1]%10), (GAME_WIDTH / 2) - 85, 50);
      g.drawString(String.valueOf(scorearray[0]/10) + String.valueOf(scorearray[0]%10), (GAME_WIDTH/2) + 20, 50);
      g.drawLine(GAME_WIDTH / 2, 0, (GAME_WIDTH / 2), GAME_HEIGHT);
  }
  
}