import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Title {

  static int GAME_WIDTH;
  static int GAME_HEIGHT;

  Title (int GAME_WIDTH, int GAME_HEIGHT) {
    this.GAME_WIDTH = GAME_WIDTH;
    this.GAME_HEIGHT = GAME_HEIGHT;
  }
    
  public void draw(Graphics g) {
      g.setColor(Color.white);
      g.setFont(new Font(Font.DIALOG, Font.BOLD, 96));
      g.drawString("PONG!", (GAME_WIDTH / 2) - 150, 300);
      g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
      g.drawString("L: Load Game", (GAME_WIDTH / 2) - 70, 500);
      g.drawString("N: New Game", (GAME_WIDTH / 2) - 70, 540);
      g.drawString("E: Exit Game", (GAME_WIDTH / 2) - 70, 580);
  }
}