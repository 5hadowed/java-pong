import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class fastBall extends Ballball {
   
   int speed;
   
   public fastBall (int x, int y, int width, int height, int xspeed, int yspeed) {
      super(x, y, width, height);
      this.speed = speed;
   }
   
   public void draw(Graphics g) {
      g.setColor(Color.red);
      g.fillOval(x, y, width, height);
   }
}