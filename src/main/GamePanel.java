package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int orginalTileSize = 16; // original size of character or object
    final int scale = 3; // we make object with 16x16 pixel but lok in the screen in 48x48

    final int tileSize = orginalTileSize * scale; // 48x48
    final int maxScreenCol = 16;// max col to see in the screen
    final int maxScreenRow = 12;// max row to see in the screen
    final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    KeyHandler keyH = new KeyHandler();

    // actually object in game is static but it like move because in 60 s the object
    // is actually move so it look like real, to make this we make clock on the game
    // with thread
    Thread gameThread;

    public GamePanel() {
        // size of jpanel or content
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // add keylistener to gamepanel
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);// add jpanel to thread
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            // System.out.println("running loop game");

            // 1, UPDATE : update ifnoramtion such as character position
            update();
            // 2. DRAW : draw the screen tih the updated information
            repaint();
        }
    }

    public void update() {

    }

    public void paintComponent(Graphics g) { // standart method to draw object in java
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose();
    }
}
