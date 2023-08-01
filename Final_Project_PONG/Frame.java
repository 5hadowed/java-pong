import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Frame extends JFrame{ 
 
  Panel p;
  
  Frame() {
    p = new Panel();
    this.add(p);
    this.setTitle("Pong");
    this.setResizable(false);
    this.setBackground(Color.black);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    this.pack();
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }
}