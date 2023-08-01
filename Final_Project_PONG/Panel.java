import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.io.*;

public class Panel extends JPanel implements Runnable {

  static final int GAME_WIDTH = 1000;
  static final int GAME_HEIGHT = 600;
  static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);  
  static final int BALL_DIAMETER = 20;
  static final int PADDLE_WIDTH = 20;
  static final int PADDLE_HEIGHT = 100;
  public int titleState = 0;
  Scanner s;
  FileWriter writer;
  Thread gameThread;
  Image image;
  Graphics graphics;
  Random randomX = new Random();
  Random randomY = new Random();
  Random randompowerup = new Random();
  Paddle paddle1;
  Paddle paddle2;
  Ball ball;
  Score score;
  Title title;
  Powerups powerup;
  fastBall fastball;
  public boolean lastPaddle;
  public boolean active = true;
  public boolean titl = true;
  int xball;
  int yball;
  int oXV;
  int oYV;
  int v;
  
  public Panel() {
    newTitle();
    this.setFocusable(true);
    this.addKeyListener(new alistener());
    this.setPreferredSize(SCREEN_SIZE);
    gameThread = new Thread(this);
    gameThread.start();
    v = -1;
  }
  
  public void newTitle() {
      title = new Title(GAME_WIDTH, GAME_HEIGHT);
  }
  
  public void newBall() {
    // random = new Random();
    if (active) {
      ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER, BALL_DIAMETER, false);
    }
  }
  
  public void newPaddles() {
    paddle1 = new Paddle(0, GAME_HEIGHT / 2 - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
    paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, GAME_HEIGHT / 2 - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
  }
  
  public void newScore() {
    score = new Score(GAME_WIDTH, GAME_HEIGHT);
  }

  public void newPowerup(int tmp) {
    int upperboundX = 800;
    int upperboundY = 400;
    int randomxint = randomX.nextInt(upperboundX);
    int randomyint = randomY.nextInt(upperboundY);
    powerup = new Powerups(tmp, randomxint, randomyint, 35, 35);
  }

  public void paint(Graphics g) {
    image = createImage(getWidth(), getHeight());
    graphics = image.getGraphics();
    draw(graphics);
    g.drawImage(image, 0, 0, this);
  }

  public void draw(Graphics g) {
    if (!titl) {
       paddle1.draw(g);
       paddle2.draw(g);
       ball.draw(g);
       score.draw(g);
       powerup.draw(g);
    } else {
       title.draw(g);
    }
  }

  public void move() {
     if (!titl) {
       paddle1.move();
       paddle2.move();
       ball.move();
     }
  }

  public void checkCollision() {
    if (!titl) {
       if (paddle1.y <= 0) {
         paddle1.y = 0;
       }
       if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
         paddle1.y = (GAME_HEIGHT - PADDLE_HEIGHT);
       }
       
       if (paddle2.y <= 0) {
         paddle2.y = 0;
       }
       if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
         paddle2.y = (GAME_HEIGHT - PADDLE_HEIGHT);
       }
       
       if (ball.y <= 0) {
         ball.setYDirection(-ball.yVelocity);
       }
       if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
         ball.setYDirection(-ball.yVelocity);
       }
       
       if (ball.intersects(paddle1)) {
         
         active = false;
         xball = ball.x;
         yball = ball.y;
         //if(fast == true){
         //  ball.fast = true;
         //  ball.draw(graphics);
         //}
                
         ball.xVelocity = -ball.xVelocity;
         oXV = ball.xVelocity;
         oYV = ball.yVelocity;
   
         ball.setXDirection(ball.xVelocity + 1);
         v = 0;
         if (ball.yVelocity > 0) {
            ball.setYDirection(ball.yVelocity + 1);
         }
         else {
            ball.setYDirection(ball.yVelocity - 1);
         }
   
         if(ball.fast == true){
           ball.fast = false;
           ball.draw(graphics);
         }
   
         if(ball.slow == true){
           ball.slow = false;
           ball.draw(graphics);
         }
   
         lastPaddle = true;
         
       }
       if (ball.intersects(paddle2)) {
         
         // ball.xVelocity = oXV;
   //       ball.yVelocity = oYV;
         ball.xVelocity = -ball.xVelocity;
         ball.setXDirection(ball.xVelocity - 1);
         v = 1;
         if (ball.yVelocity > 0) {
            ball.setYDirection(ball.yVelocity + 1);
         }
         else {
            ball.setYDirection(ball.yVelocity - 1);
         }
   
         if(ball.fast == true){
           ball.fast = false;
           ball.draw(graphics);
         }
         if(ball.slow == true){
           ball.slow = false;
           ball.draw(graphics);
         }
         lastPaddle = false;
       }
       
       if (ball.x <= 0) {
         score.scorearray[0] += 1;
         active = true;
         newPaddles();
         newBall();
         v = -1;
       }
       if (ball.x >= GAME_WIDTH) {
         score.scorearray[1] += 1;
         active = true;
         newPaddles();
         newBall();
         v = -1;
       }
   
       if(ball.intersects(powerup)){
         if(v >= 0){
            if(powerup.temp == 0){
              if(v == 0){
                ball.setXDirection(ball.xVelocity + 3);
                if (ball.yVelocity > 0) {
                  ball.setYDirection(ball.yVelocity + 3);
                }
                else {
                    ball.setYDirection(ball.yVelocity - 3);
                }
              }
              if(v == 1){
                ball.setXDirection(ball.xVelocity - 3);
                if (ball.yVelocity > 0) {
                  ball.setYDirection(ball.yVelocity + 3);
              }
              else {
                  ball.setYDirection(ball.yVelocity - 3);
              }
              }
              ball.fast = true;
              ball.draw(graphics);
              newPowerup(randompowerup.nextInt(2));
            }
            else if(powerup.temp == 1){
              if(v == 0){
                if(ball.xVelocity > 3){
                  ball.setXDirection(ball.xVelocity - 3);
                }
                else{
                  ball.setXDirection(2);
                }
                if (ball.yVelocity > 0) {
                  ball.setYDirection(ball.yVelocity - 3);
                }
                else {
                    ball.setYDirection(ball.yVelocity + 3);
                }
              }
              if (v == 1) {
                if(ball.xVelocity < -3){
                  ball.setXDirection(ball.xVelocity + 3);
                }
                else{
                  ball.setXDirection(-2);
                }
                if (ball.yVelocity > 0) {
                  ball.setYDirection(ball.yVelocity - 3);
                }
                else {
                    ball.setYDirection(ball.yVelocity + 3);
                }
              }
              ball.slow = true;
              ball.draw(graphics);
              newPowerup(randompowerup.nextInt(2));
            }
          }
       }
     }
  }

  public void run() {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000/amountOfTicks;
    double delta = 0;
    while (true) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      if (delta >= 1) {
         if (!titl) {
           move();
           checkCollision();
           repaint();
           delta--;
         } else {
            repaint();
            delta--;
         }
      }
    }
  }

  public class alistener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      if (!titl) {
         paddle1.keyPressed(e);
         paddle2.keyPressed(e);
      } else {
         titleKeyPressed(e);
      }
      exitKeyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
      if (!titl) {
         paddle1.keyReleased(e);
         paddle2.keyReleased(e);
      } else {
         titleKeyPressed(e);
      }
      exitKeyReleased(e);
    }
  }
  
  public void titleKeyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_N && titl == true) {
         newPaddles();
         newBall();
         score = new Score(GAME_WIDTH, GAME_HEIGHT);
         newPowerup(randompowerup.nextInt(2));
         titl = false;
      }  
      if (e.getKeyCode() == KeyEvent.VK_L && titl == true) {
         newPaddles();
         newBall();
         score = new Score(GAME_WIDTH, GAME_HEIGHT);
         newPowerup(randompowerup.nextInt(2));
         titl = false;
         try {
            File file = new File("scores.txt");
            s = new Scanner(file);
            int i = 0;
            while (s.hasNext()) {
               score.scorearray[i++] = s.nextInt();
            }
         }
         catch (FileNotFoundException ex) {
         
         }
      }
  }

  public void titleKeyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_N && titl == true) {
         newPaddles();
         newBall();
         score = new Score(GAME_WIDTH, GAME_HEIGHT);
         newPowerup(randompowerup.nextInt(2));
         titl = false;
      }  
      if (e.getKeyCode() == KeyEvent.VK_L && titl == true) {
         newPaddles();
         newBall();
         score = new Score(GAME_WIDTH, GAME_HEIGHT);
         newPowerup(randompowerup.nextInt(2));
         titl = false;
         try {
            File file = new File("scores.txt");
            s = new Scanner(file);
            int i = 0;
            while (s.hasNext()) {
               score.scorearray[i++] = s.nextInt();
            }
         }
         catch (FileNotFoundException ex) {
         
         }
      }
  }
  
  public void exitKeyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_E && titl == false) {
            File scores = new File("scores.txt");
            try {
               writer = new FileWriter(scores);
               writer.write(String.valueOf(score.scorearray[0]) + " " + String.valueOf(score.scorearray[1]));
               writer.flush();
               writer.close();
            }
            catch (IOException ex) {
            
            }
            System.exit(0);
         } else if (e.getKeyCode() == KeyEvent.VK_E && titl == true) {
              System.exit(0);
         }
  }

  public void exitKeyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_E && titl == false) {
         File scores = new File("scores.txt");
         try {
            writer = new FileWriter(scores);
            writer.write(String.valueOf(score.scorearray[0]) + " " + String.valueOf(score.scorearray[1]));
            writer.flush();
            writer.close();
         }
         catch (IOException ex) {
         
         }
         System.exit(0);
      } else if (e.getKeyCode() == KeyEvent.VK_E && titl == true) {
         System.exit(0);  
      } 
  }
  
}