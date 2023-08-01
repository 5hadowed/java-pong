import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Ballball {

   boolean fast;
   boolean slow;

   Ball (int x, int y, int width, int height, boolean fast) {
      super(x, y, width, height);
   }
   
   public void draw(Graphics g) {
      if (fast == true && slow == false) {
         g.setColor(Color.red);
      } 
      else if(slow == true && fast == false){
         g.setColor(new Color(0, 145, 255));
      }
      else {
         g.setColor(Color.white);
      }
      g.fillOval(x, y, width, height);
   }
}