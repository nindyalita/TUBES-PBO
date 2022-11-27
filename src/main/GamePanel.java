package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int orginalTileSize = 16; // original size of character or object
    final int scale = 3; // we make object with 16x16 pixel but lok in the screen in 48x48

    public final int tileSize = orginalTileSize * scale; // 48x48
    public final int maxScreenCol = 16;// max col to see in the screen
    public final int maxScreenRow = 12;// max row to see in the screen
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxScreenWidth = tileSize * maxWorldCol;
    public final int maxScreenHeight = tileSize * maxScreenRow;

    // FPS (frame per second)
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(); // instansisasi keyhandler

    // actually object in game is static but it like move because in 60 s the object
    // is actually move so it look like real, to make this we make clock on the game
    // with thread
    Thread gameThread;
    public Player player = new Player(this, keyH); // intansiasi player
    public CollisionChecker cChecker = new CollisionChecker(this);

    public GamePanel() {
        // size of jpanel or content
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // add keylistener to gamepanel
        this.setFocusable(true); // make gamePanel focus to receive key input
    }

    public void startGameThread() {
        gameThread = new Thread(this);// add jpanel to thread
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0,01666 second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            // System.out.println("running loop game");
            // long currentTime = System.nanoTime();
            // System.out.println("current time : " + currentTime);

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }
            /*
             * NOTE!
             * 1, UPDATE : update ifnoramtion such as character position
             * update();
             * 
             * 2. DRAW : draw the screen tih the updated information
             * repaint();
             */
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) { // standart method to draw object in java
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2); // make sure draw tile before player, because it like layer.

        player.draw(g2);

        g2.dispose();
    }
}
