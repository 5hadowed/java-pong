import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Powerups extends Rectangle{
    
    int temp;

    public Powerups(int a, int x, int y, int width, int height){
        super(x, y, width, height);
        
        temp = a;
    }

    public void draw(Graphics g){
        if(temp == 0){
            g.setColor(Color.red);
        }
        if(temp == 1){
            g.setColor(new Color(0, 145, 255));
        }
        g.fillOval(x, y, width, height);
    }
}
